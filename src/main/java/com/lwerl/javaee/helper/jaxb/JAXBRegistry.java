package com.lwerl.javaee.helper.jaxb;

import com.lwerl.javaee.model.Employee;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;

/**
 * Created by lWeRl on 16.02.2018.
 */
@XmlRegistry
public class JAXBRegistry {
    private static final String EMPLOYEES_STR = "employees";
    public static final QName EMPLOYEES = new QName(EMPLOYEES_STR);

    @XmlElementDecl(name = EMPLOYEES_STR)
    public JAXBElement<JAXBListWrapper> createEmployeesList(JAXBListWrapper<Employee> value) {
        return new JAXBElement<>(EMPLOYEES, JAXBListWrapper.class, value);
    }
}
