package com.interview.r360.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PatientDao {

    private List<Patient> patients = new ArrayList<>();
}
