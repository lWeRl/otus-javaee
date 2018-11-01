package com.lwerl.javaee.api;

import com.google.gson.Gson;
import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.model.Employee;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/api/employees/*")
public class EmployeeApiServlet extends HttpServlet {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Gson().fromJson(req.getReader(), Employee.class);
        employee.setId(getId(req));
        employeeDAO.update(employee);
        resp.getWriter().append(new Gson().toJson(employee));
        resp.setContentType("application/json");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Employee();
        employee.setId(getId(req));
        employeeDAO.delete(employee);
        resp.setStatus(200);
    }

    private Long getId(HttpServletRequest req) {
        String id = req.getRequestURI().replaceFirst("/api/employees/", "");
        return Long.parseLong(id);
    }
}
