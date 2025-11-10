package com.es.lsapp.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Clazz {
    private Integer id;
    private String name;
    private Student student;
}
