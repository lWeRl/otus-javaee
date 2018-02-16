package com.lwerl.javaee.servlet;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import com.lwerl.javaee.helper.jaxb.JAXBListWrapper;
import com.lwerl.javaee.helper.jaxb.JAXBRegistry;
import com.lwerl.javaee.model.Employee;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.lang.reflect.Type;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Created by lWeRl on 16.02.2018.
 */
@WebServlet(name = "JsonServlet", urlPatterns = "/json")
public class JsonServlet extends HttpServlet {

    public static final String JSON_FILE_PATH = "employee.json";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<Employee> listFromFile = getListFromFile();
        String json = makeJson(listFromFile);
        response.getWriter().write(json);

        response.getWriter().write("\n");

        List<Employee> oddEmployees = getOddEmployeesFromFile();
        response.getWriter().print(oddEmployees);
    }

    @SuppressWarnings("unchecked")
    private List<Employee> getListFromFile() throws IOException, ServletException {
        try(InputStream inputStream = Files.newInputStream(Paths.get(XMLServlet.XML_FILE_PATH))) {
            JAXBContext context = JAXBContext.newInstance(JAXBRegistry.class, JAXBListWrapper.class, Employee.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            return ((JAXBElement<JAXBListWrapper<Employee>>) unmarshaller.unmarshal(inputStream)).getValue().getItems();
        } catch (IOException e) {
            throw e;
        } catch (Exception e) {
            throw new ServletException(e);
        }
    }

    private String makeJson(Object source) throws IOException {
        Gson gson = new GsonBuilder()
                .setPrettyPrinting()
                .create();
        String json = gson.toJson(source);
        try(BufferedWriter out = new BufferedWriter(new FileWriter(JSON_FILE_PATH))){
            out.write(json);
        }
        return json;
    }

    private List<Employee> getOddEmployeesFromFile() throws FileNotFoundException {
        Gson gson = new GsonBuilder().create();
        Type listType = new TypeToken<ArrayList<Employee>>(){}.getType();
        List<Employee> employees = gson.fromJson(new FileReader(JSON_FILE_PATH), listType);
        return Stream.iterate(0, i -> i + 2)
                .limit(employees.size() / 2)
                .map(employees::get)
                .collect(Collectors.toList());
    }
}
