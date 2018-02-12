package com.lwerl.javaee.gwt.client.helper;

import com.google.gwt.dom.client.Element;
import com.google.gwt.user.client.DOM;
import com.google.gwt.user.client.Event;

import java.util.function.Consumer;

import static com.google.gwt.user.client.Event.*;

/**
 * Created by lWeRl on 04.02.2018.
 */
public class MainContent {
    private MainContent() {}

    private static Element selectedNavElement;

    private static final String SELECTED = "selected";

    public static Element createNavElement(String text, Consumer<Event> onclick) {
        Element navElement = DOM.createDiv();
        navElement.setInnerText(text);
        navElement.setClassName("nav-element");
        Event.sinkEvents(navElement, ONCLICK);
        DOM.setEventListener(navElement, (event -> {
            if (event.getTypeInt() == ONCLICK && !navElement.hasAttribute(SELECTED)) {
                if(selectedNavElement != null) {
                    selectedNavElement.removeAttribute(SELECTED);
                }
                onclick.accept(event);
                navElement.setAttribute(SELECTED, "");
                selectedNavElement = navElement;
            }
        }));
        return navElement;
    }

}
