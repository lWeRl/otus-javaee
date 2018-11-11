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
import java.util.List;


@WebServlet(urlPatterns = "/api/employees")
@SuppressWarnings("unchecked")
public class EmployeesAllApiServlet extends HttpServlet {

    public static final String EMPLOYEE_QUERY_ATTRIBUTE_NAME = "employee query attribute";
    public static final String EMPLOYEE_LIST_ATTRIBUTE_NAME = "employee list attribute";

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        request.setAttribute(
                EMPLOYEE_QUERY_ATTRIBUTE_NAME,
                request.getQueryString() == null ? "" : request.getQueryString()
        );
        List<Employee> all = (List<Employee>) request.getAttribute(EMPLOYEE_LIST_ATTRIBUTE_NAME);
        if (all == null) {
            String search = request.getParameter("search");
            all = employeeDAO.getAll(search);
            request.setAttribute(EMPLOYEE_LIST_ATTRIBUTE_NAME, all);
        }
        response.getWriter().append(new Gson().toJson(all));
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
