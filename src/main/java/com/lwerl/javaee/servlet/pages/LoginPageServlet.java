package com.lwerl.javaee.servlet.pages;

import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.helper.TemplateBuilder;

import javax.inject.Inject;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by lWeRl on 24.01.2018.
 */
@WebServlet("/login")
public class LoginPageServlet extends HttpServlet {

    @Inject
    private CurrencyRatesHolder rateInfo;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        resp.getWriter().write(
                TemplateBuilder.of(getServletContext())
                        .addString("{{login-select}}", "selected")
                        .addString("{{right-side}}", rateInfo.getHtmlView())
                        .addTemplate("{{left-side}}", "/WEB-INF/template/login.html")
                        .build()
        );
    }
}
