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
        items = new ArrayList<>();
    }

    public JAXBListWrapper(List<T> items) {
        this.items = items;
    }

    @XmlAnyElement(lax=true)
    public List<T> getItems() {
        return items;
    }

    public JAXBElement<JAXBListWrapper> toElement(QName name) {
        return new JAXBElement<>(name, JAXBListWrapper.class, this);
    }
}
