package com.lwerl.javaee.helper;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

/**
 * Created by lWeRl on 28.01.2018.
 */
public class TemplateBuilder {
    private ServletContext context;
    private String html;
    private List<Consumer<String>> rules = new ArrayList<>();

    private TemplateBuilder(ServletContext context) throws ServletException {
        try {
            this.context = context;
            html = new BufferedReader(new InputStreamReader(context.getResourceAsStream("/WEB-INF/template/default.html"), "UTF-8")).lines().collect(Collectors.joining("\n"));
        } catch (UnsupportedEncodingException e) {
            throw new ServletException(e);
        }
    }

    public static TemplateBuilder of(ServletContext context) throws ServletException {
        return new TemplateBuilder(context);
    }

    private void setHtml(String html) {
        this.html = html;
    }

    public TemplateBuilder addTemplate(String key, String templatePath) throws ServletException {
        try {
            String template = new BufferedReader(new InputStreamReader(context.getResourceAsStream(templatePath), "UTF-8")).lines().collect(Collectors.joining("\n"));
            rules.add(page -> this.setHtml(page.replaceFirst("\\Q" + key + "\\E", template)));
            return this;
        }  catch (UnsupportedEncodingException e) {
        throw new ServletException(e);
    }
    }

    public TemplateBuilder addString(String key, String template) {
        rules.add(page -> {
            Pattern pattern = Pattern.compile("\\Q" + key + "\\E");
            Matcher matcher = pattern.matcher(page);
            if (matcher.find()) {
                int start = matcher.start();
                int end = matcher.end();
                String sb = page.substring(0, start) + template + page.substring(end);
                this.setHtml(sb);
            }
        });
        return this;
    }

    public String build() {
        rules.forEach(rule -> rule.accept(html));
        return html.replaceAll("\\Q{{\\E.*\\Q}}\\E", "");
    }
}
