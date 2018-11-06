package com.lwerl.javaee.api;

import com.google.gson.Gson;
import com.lwerl.javaee.dao.EmployeeDAO;
import com.lwerl.javaee.model.Employee;
import lombok.Data;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@WebServlet(name = "LoginServlet", urlPatterns = "/api/login")
public class LoginServlet extends HttpServlet {

    @Inject
    private EmployeeDAO employeeDAO;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LoginDTO loginInfo = new Gson().fromJson(request.getReader(), LoginDTO.class);

        Employee user = null;
        if (loginInfo != null) {
            user = employeeDAO.getByCredentials(loginInfo.getLogin(), loginInfo.getPassword());
        }
        if (user != null && user.getPassword().equals(loginInfo.getPassword())) {
            HttpSession session = request.getSession(true);
            session.setAttribute("user", user);
        } else {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Employee user = null;

        if (req.getSession() != null) {
            user = (Employee) req.getSession().getAttribute("user");
        }

        new Gson().toJson(user, resp.getWriter());
    }

    @Data
    private static class LoginDTO {
        String login;
        String password;
    }
}
