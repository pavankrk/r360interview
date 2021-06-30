package com.interview.r360.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Patient {


    private String name;

    private long age;

    private double weight;

    private double height;

    private double bmi;

    }
