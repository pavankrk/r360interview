package com.interview.r360.service;

import com.interview.r360.dto.Patient;
import org.springframework.stereotype.Service;

@Service
public class BMIServiceImpl implements BMIService{

    @Override
    public double calculateBMI(Patient patient) {
        //Converting height cm to meters
        double height = patient.getHeight()*0.01;
        double bmi = patient.getWeight() /(height*height);
        return bmi;
    }
}
