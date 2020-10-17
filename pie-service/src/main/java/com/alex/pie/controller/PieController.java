package com.alex.pie.controller;

import com.alex.pie.domain.Measurement;
import org.springframework.web.bind.annotation.*;

@RestController
public class PieController {
    
    @RequestMapping(method = RequestMethod.POST,value = "/measurements")
    public void createMeasurement(@RequestBody Measurement measurement) {
        System.out.println("#############################################################" + measurement);

    }
}
