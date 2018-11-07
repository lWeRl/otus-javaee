package com.lwerl.javaee.xml;

import com.lwerl.javaee.model.Department;
import com.lwerl.javaee.model.Employee;
import com.lwerl.javaee.model.Position;
import lombok.Data;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@Data
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class PersistenceData {

    @XmlElement
    private List<Employee> employees;

    @XmlElement
    private List<Department> departments;

    @XmlElement
    private List<Position> positions;
}
