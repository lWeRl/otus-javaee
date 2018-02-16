package com.lwerl.javaee.servlet;

import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.helper.jaxb.JAXBListWrapper;
import com.lwerl.javaee.helper.jaxb.JAXBRegistry;
import com.lwerl.javaee.model.Employee;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathFactory;
import java.io.IOException;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

/**
 * Created by lWeRl on 21.01.2018.
 */
@WebServlet(name = "XMLServlet", urlPatterns = "/xml")
public class XMLServlet extends HttpServlet {

    public static final String XML_FILE_PATH = "employee.xml.";

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        writeToFile();
        processFile(resp);
    }

    private void writeToFile() throws ServletException, IOException {
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(XML_FILE_PATH))) {
            JAXBContext context = JAXBContext.newInstance(JAXBListWrapper.class, Employee.class);
            List<Employee> employees = employeeDAO.getAll();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new JAXBListWrapper<>(employees).toElement(JAXBRegistry.EMPLOYEES), outputStream);
            outputStream.flush();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private void processFile(HttpServletResponse resp) throws ServletException, IOException {
        try {
            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(XML_FILE_PATH);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String query = "//employee[number(position/salary) > (sum(//position/salary) div count(//position/salary))]//*[not(*)]";
            NodeList list = (NodeList) xPath.compile(query).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                resp.getWriter().write(node.getNodeName()+ ": ");
                resp.getWriter().write(node.getTextContent().trim() + "\n");
            }
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }
}
