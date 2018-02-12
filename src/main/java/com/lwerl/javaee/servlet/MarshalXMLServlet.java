package com.lwerl.javaee.servlet;

import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.helper.jaxb.JAXBListWrapper;
import com.lwerl.javaee.model.Department;
import com.lwerl.javaee.model.Employee;
import com.lwerl.javaee.model.Position;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
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
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

/**
 * Created by lWeRl on 21.01.2018.
 */
@WebServlet(name = "MarshalXMLServlet", urlPatterns = "/xml")
public class MarshalXMLServlet extends HttpServlet {

    private static String FILE = "employee.xml.";

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try(OutputStream outputStream = Files.newOutputStream(Paths.get(FILE))) {
            JAXBContext context = JAXBContext.newInstance(JAXBListWrapper.class, Employee.class);
            List<Employee> employees = employeeDAO.getAll();
            Marshaller marshaller = context.createMarshaller();
            marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            marshaller.marshal(new JAXBListWrapper<>(employees).toElement("employees"), outputStream);
            outputStream.flush();

            Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(FILE);
            XPath xPath = XPathFactory.newInstance().newXPath();
            String query = "/employees/employee/*";
            NodeList list = (NodeList) xPath.compile(query).evaluate(document, XPathConstants.NODESET);
            for (int i = 0; i < list.getLength(); i++) {
                Node node = list.item(i);
                if (node.hasChildNodes())
                resp.getWriter().write(node.getNodeName());
                resp.getWriter().write(":");
                resp.getWriter().write(node.getTextContent().trim());
                resp.getWriter().write("\n");
            }

        } catch (Exception e) {
            throw new ServletException(e);
        }

    }
}
