package com.lwerl.javaee.servlet;

import jdk.nashorn.api.scripting.NashornScriptEngineFactory;

import javax.script.ScriptEngine;
import javax.script.ScriptException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lWeRl on 29.01.2018.
 */
@WebServlet("/script/execute")
public class ScriptServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        try {
        NashornScriptEngineFactory factory = new NashornScriptEngineFactory();
        ScriptEngine engine = factory.getScriptEngine( "-scripting" );
        String scriptBody = req.getParameter("script");
            resp.getWriter().print(engine.eval(scriptBody));
        } catch (ScriptException e) {
            resp.getWriter().print(e.getMessage());
        }
    }
}
