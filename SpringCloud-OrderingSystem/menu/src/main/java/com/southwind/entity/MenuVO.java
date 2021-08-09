package com.southwind.entity;

import lombok.Data;

import java.util.List;

/**
 * 返回前端ui框架需要的数据格式
 */
@Data
public class MenuVO {
    private int code;
    private String msg;
    private int count;
    private List<Menu> data;
}