package com.example.utils;

import javax.xml.bind.annotation.XmlElement;
import java.util.ArrayList;

public class Category {
    private int id;
    private String name;

    @XmlElement
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @XmlElement
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
