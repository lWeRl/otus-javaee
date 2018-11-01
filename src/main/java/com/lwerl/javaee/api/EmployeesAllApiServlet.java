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


@WebServlet(urlPatterns = "/api/employees")
public class EmployeesAllApiServlet extends HttpServlet {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append(new Gson().toJson(employeeDAO.getAll()));
        response.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee employee = new Gson().fromJson(req.getReader(), Employee.class);
        employeeDAO.save(employee);
        resp.getWriter().append(new Gson().toJson(employee));
        resp.setContentType("application/json");
    }
}
