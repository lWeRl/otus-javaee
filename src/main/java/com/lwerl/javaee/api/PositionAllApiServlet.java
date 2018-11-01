package com.lwerl.javaee.api;

import com.google.gson.Gson;
import com.lwerl.javaee.dao.PositionDAO;
import com.lwerl.javaee.model.Position;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet(urlPatterns = "/api/positions")
public class PositionAllApiServlet extends HttpServlet {

    @Inject
    private PositionDAO dao;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append(new Gson().toJson(dao.getAll()));
        response.setContentType("application/json");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Position model = new Gson().fromJson(req.getReader(), Position.class);
        dao.save(model);
        resp.getWriter().append(new Gson().toJson(model));
        resp.setContentType("application/json");
    }
}
