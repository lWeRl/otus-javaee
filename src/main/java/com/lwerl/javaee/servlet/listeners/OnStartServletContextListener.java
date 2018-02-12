package com.lwerl.javaee.servlet.listeners;

import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.cache.NewsHolder;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
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
        scheduler.scheduleAtFixedRate(() -> rateInfo.updateRates(), 0, 5, TimeUnit.MINUTES);
        scheduler.scheduleAtFixedRate(() -> newsHolder.updateNews(), 0, 5, TimeUnit.MINUTES);
    }

    private void restoreFormXML() {
        try {
            File xmlFile = new File(System.getProperty("user.home") + System.getProperty("file.separator") + System.getProperty("xml.restore.file.name"));
            if (xmlFile.exists()) {

            }
        } catch (Exception e) {
            logger.log(Level.WARNING, "File not found", e);
        }
    }

    private void saveToXML() {

    }
}
