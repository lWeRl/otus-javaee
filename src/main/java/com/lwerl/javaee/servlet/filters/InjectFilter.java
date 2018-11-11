package com.lwerl.javaee.servlet.filters;

import com.lwerl.javaee.cache.CurrencyRatesHolder;
import com.lwerl.javaee.cache.NewsHolder;

import javax.inject.Inject;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(filterName = "InjectFilter", urlPatterns = "/*")
public class InjectFilter extends HttpFilter {

    @Inject
    private NewsHolder newsHolder;

    @Inject
    CurrencyRatesHolder currencyRatesHolder;

    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {

        req.setAttribute("newsHolder", newsHolder);
        req.setAttribute("currencyRatesHolder", currencyRatesHolder);
        super.doFilter(req, res, chain);
    }
}
