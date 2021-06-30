package com.interview.r360.controller;

import com.interview.r360.dto.Patient;
import com.interview.r360.dto.PatientDao;
import com.interview.r360.service.BMIService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/patient")
public class PatientController {

    @Autowired
    BMIService bmiService;


    public Map<String , Patient> patientData = new HashMap<>();

    @PostMapping(value = "/save",consumes = MediaType.APPLICATION_JSON_VALUE,produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> insertData(@RequestBody PatientDao patientDao){
        String response = "";
        if(patientDao != null && patientDao.getPatients().size() >0){
           patientDao.getPatients().stream().forEach(pd -> {
               pd.setBmi(bmiService.calculateBMI(pd));
               patientData.putIfAbsent(pd.getName(),pd);
           });
           response = "Saved patient count is  "+patientDao.getPatients().size();
        }else{
            response ="Payload is having Empty data";
        }
        return ResponseEntity.ok(response);
    }



    @GetMapping(value = "/details/{name}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Map<String,Object>> getPatientData(@PathVariable("name") String name){
        Map<String,Object> result = new HashMap<>();

        if(patientData.size()>0){
         Optional<Map.Entry<String, Patient>> detail =   patientData.entrySet().stream().filter(k -> k.getKey().equalsIgnoreCase(name)).findAny();
         if(detail.isPresent()) {
             Patient patient = detail.get().getValue();
             result.put("details", patient);
         }else{
             result.put("error","Patient not found");
         }

        }else{
            result.put("error","patient data is not available");
        }

       return ResponseEntity.ok(result);

    }


}
