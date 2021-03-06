package com.lwerl.javaee.servlet.filters;

import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;
import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by lWeRl on 08.02.2018.
 */
@WebFilter(filterName = "AgentFilter", urlPatterns = "/*")
public class AgentFilter extends HttpFilter {

    private final int IE_MIN_VERSION = 10;
    private final int CHROME_MIN_VERSION = 50;
    private final int FIREFOX_MIN_VERSION = 45;
    private final int OPERA_MIN_VERSION = 38;

    @Override
    public void doFilter(HttpServletRequest req, HttpServletResponse resp, FilterChain chain) throws ServletException, IOException {

        if (!hasCookie(req) && !isResource(req) && !isActualBrowser(req)) {
            req.getRequestDispatcher("/pages/outofdate.jsp").forward(req, resp);
        } else {
            chain.doFilter(req, resp);
            resp.addCookie(new Cookie("browser", "actual"));
        }
    }


    private boolean isResource(HttpServletRequest req) {
        String query = req.getQueryString() == null
                ? ""
                : req.getQueryString();
        return query.matches(".*\\.css$|.*\\.js$");
    }

    private boolean isActualBrowser(HttpServletRequest req) {
        boolean result = true;
        String agent = Objects.toString(req.getHeader("User-Agent"), "");
        Pattern regexp = Pattern.compile("(msie|chrome|firefox|opera)(\\s|/|\\\\)(\\d*)\\.\\d", Pattern.CASE_INSENSITIVE);
        Matcher matcher = regexp.matcher(agent);
        if (matcher.find()) {

            String browser = matcher.group(1);
            String version = matcher.group(3);

            switch (browser.toLowerCase()) {
                case ("msie"): {
                    result = (Integer.parseInt(version) >= IE_MIN_VERSION);
                    break;
                }
                case ("chrome"): {
                    result = (Integer.parseInt(version) >= CHROME_MIN_VERSION);
                    break;
                }
                case ("firefox"): {
                    result = (Integer.parseInt(version) >= FIREFOX_MIN_VERSION);
                    break;
                }
                case ("opera"): {
                    result = (Integer.parseInt(version) >= OPERA_MIN_VERSION);
                    break;
                }
            }

            if (!result) {
                req.setAttribute("BrowserName", browser);
                req.setAttribute("BrowserVersion", Integer.parseInt(version));
            }

        }

        return result;
    }

    private boolean hasCookie(HttpServletRequest req) {
        return req.getCookies() != null && Arrays.stream(req.getCookies()).anyMatch(c -> "browser".equals(c.getName()));
    }


    @Override
    public void destroy() {
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

}
