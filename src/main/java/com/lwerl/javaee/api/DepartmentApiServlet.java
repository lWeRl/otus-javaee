package com.lwerl.javaee.api;

import com.google.gson.Gson;
import com.lwerl.javaee.dao.DepartmentDAO;
import com.lwerl.javaee.dao.PositionDAO;
import com.lwerl.javaee.model.Department;
import com.lwerl.javaee.model.Position;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/api/departments/*")
public class DepartmentApiServlet extends HttpServlet {

    @Inject
    private DepartmentDAO dao;

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department model = new Gson().fromJson(req.getReader(), Department.class);
        model.setId(getId(req));
        dao.update(model);
        resp.getWriter().append(new Gson().toJson(model));
        resp.setContentType("application/json");
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Department model = new Department();
        model.setId(getId(req));
        dao.delete(model);
        resp.setStatus(200);
    }

    private Long getId(HttpServletRequest req) {
        String id = req.getQueryString().replaceFirst("/api/department/", "");
        return Long.parseLong(id);
    }
}
