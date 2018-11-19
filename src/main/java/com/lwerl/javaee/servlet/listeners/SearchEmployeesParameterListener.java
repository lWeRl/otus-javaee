package com.lwerl.javaee.servlet.listeners;

import com.lwerl.javaee.model.Employee;

import javax.servlet.ServletRequestAttributeEvent;
import javax.servlet.ServletRequestAttributeListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

import static com.lwerl.javaee.api.EmployeesAllApiServlet.EMPLOYEE_LIST_ATTRIBUTE_NAME;
import static com.lwerl.javaee.api.EmployeesAllApiServlet.EMPLOYEE_QUERY_ATTRIBUTE_NAME;

@WebListener
@SuppressWarnings("unchecked")
public class SearchEmployeesParameterListener implements ServletRequestAttributeListener {
    private ConcurrentMap<String, List<Employee>> cache = new ConcurrentHashMap<>();

    @Override
    public void attributeAdded(ServletRequestAttributeEvent srae) {
        if (filter(srae)) {

            HttpServletRequest request = (HttpServletRequest) srae.getServletRequest();

            if (srae.getName().equals(EMPLOYEE_QUERY_ATTRIBUTE_NAME)) {
                String key = (String) srae.getValue();
                List<Employee> employees = cache.get(key);
                if (employees != null) {
                    request.setAttribute(EMPLOYEE_LIST_ATTRIBUTE_NAME, employees);
                }
            }

            if (srae.getName().equals(EMPLOYEE_LIST_ATTRIBUTE_NAME)) {
                cache.putIfAbsent(
                        Objects.toString(request.getQueryString(), ""),
                        (List<Employee>) srae.getValue()
                );
            }
        }


    }

    private boolean filter(ServletRequestAttributeEvent srae) {
        return
                srae.getServletRequest() instanceof HttpServletRequest
                        && ((HttpServletRequest) srae.getServletRequest()).getRequestURI().startsWith("/api/employees")
                        && ((HttpServletRequest) srae.getServletRequest()).getMethod().equalsIgnoreCase("GET");

    }
}
