package com.lwerl.javaee.servlet.listeners;

import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.cache.NewsHolder;
import com.lwerl.javaee.dao.DepartmentDAO;
import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.dao.PositionDAO;
import com.lwerl.javaee.xml.PersistenceData;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by lWeRl on 25.01.2018.
 */

@WebListener
public class OnStartServletContextListener implements ServletContextListener {

    private final Logger logger = Logger.getLogger("OnStartServletContextListener");

    @Inject
    private CurrencyRatesHolder rateInfo;

    @Inject
    private NewsHolder newsHolder;

    @Inject
    private EmployeeDAO employeeDAO;
    @Inject
    private PositionDAO positionDAO;
    @Inject
    private DepartmentDAO departmentDAO;

    private ScheduledExecutorService scheduler = Executors.newSingleThreadScheduledExecutor();

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        loadProperties();
        startScheduler();
        restoreFormXML();
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        scheduler.shutdown();
        saveToXML();
    }

    private void loadProperties() {
        try (ByteArrayOutputStream bos = new ByteArrayOutputStream()) {

            Properties defaultProp = new Properties();
            defaultProp.load(getClass().getClassLoader().getResourceAsStream("app.properties"));

            System.getProperties().store(bos, null);
            Properties newSystemProp = new Properties(defaultProp);
            newSystemProp.load(new ByteArrayInputStream(bos.toByteArray()));

            System.setProperties(newSystemProp);

        } catch (IOException e) {
            logger.log(Level.WARNING, "Properties don't load", e);
        }
    }


    private void startScheduler() {
        scheduler.scheduleAtFixedRate(rateInfo::updateRates, 0, 5, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(newsHolder::updateNews, 0, 5, TimeUnit.MINUTES);
    }

    private void restoreFormXML() {
        try {
            File xmlFile = new File(getXmlFilePath());
            if (xmlFile.exists()) {
                JAXBContext jaxbContext = JAXBContext.newInstance(PersistenceData.class);
                Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
                PersistenceData data = (PersistenceData) jaxbUnmarshaller.unmarshal(xmlFile);
                populatePersistenceData(data);
            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "File not found", e);
        }
    }


    private void saveToXML() {
        try {
            File xmlFile = new File(getXmlFilePath());
            JAXBContext jaxbContext = JAXBContext.newInstance(PersistenceData.class);
            Marshaller marshaller = jaxbContext.createMarshaller();
            marshaller.marshal(collectPersistenceData(), xmlFile);

        } catch (Exception e) {
            logger.log(Level.WARNING, "File not found", e);
        }
    }

    private PersistenceData collectPersistenceData() {
        PersistenceData persistenceData = new PersistenceData();
        persistenceData.setEmployees(employeeDAO.getAll());
        persistenceData.setDepartments(departmentDAO.getAll());
        persistenceData.setPositions(positionDAO.getAll());
        return persistenceData;
    }

    private void populatePersistenceData(PersistenceData data) {
        data.getDepartments().forEach(departmentDAO::save);
        data.getPositions().forEach(positionDAO::save);
        data.getEmployees().forEach(employeeDAO::save);
    }


    private String getXmlFilePath() {
        return System.getProperty("user.home") + System.getProperty("file.separator") + System.getProperty("xml.restore.file.name");
    }
}
