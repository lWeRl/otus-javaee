package com.lwerl.javaee.helper.jaxb;

/**
 * Created by lWeRl on 22.01.2018.
 */
import java.util.*;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAnyElement;
import javax.xml.namespace.QName;

public class JAXBListWrapper<T> {

    private List<T> items;

    public JAXBListWrapper() {
        items = new ArrayList<T>();
    }

    public JAXBListWrapper(List<T> items) {
        this.items = items;
    }

    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }

    public JAXBElement<JAXBListWrapper> toElement(String name) {
        return new JAXBElement<>(new QName(name), JAXBListWrapper.class, this);
    }
}
