package com.lwerl.javaee.servlet;

import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.model.Employee;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lWeRl on 12.02.2018.
 */
@WebServlet(name = "EmployeeServlet", urlPatterns = "/employee")
public class EmployeeServlet extends HttpServlet {

    @Inject
    private EmployeeDAO employeeDAO;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        employeeDAO.getAll().stream()
                .sorted((e1, e2) -> (int) (e1.getId() - e2.getId()))
                .forEach(employee -> {
                    try {
                        response.getWriter().println(employee);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });

        change();

        response.getWriter().println();
        response.getWriter().println(employeeDAO.getMaxSalaryName());
        response.getWriter().println();

        employeeDAO.getAll().stream()
                .sorted((e1, e2) -> (int) (e1.getId() - e2.getId()))
                .forEach(employee -> {
                    try {
                        response.getWriter().println(employee);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                });
    }

    private void change() {
        Employee employee;

        employee = employeeDAO.get(1L);
        employee.setFirstName("Mr. Changeable");
        employeeDAO.update(employee);

        employee = employeeDAO.get(6L);
        employee.setFirstName("Mr. Hurricane");
        employeeDAO.update(employee);

        employeeDAO.delete(employeeDAO.get(10L));
        employeeDAO.delete(employeeDAO.get(11L));
        employeeDAO.delete(employeeDAO.get(12L));
    }
}
