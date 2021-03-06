package com.southwind.entity;

import lombok.Data;

@Data
public class Menu {
    private long id;
    private String name;
    private String author;
    private double price;
    private String flavor;
    private Type type;
}