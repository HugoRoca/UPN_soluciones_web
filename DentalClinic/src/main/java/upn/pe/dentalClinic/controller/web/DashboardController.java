/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.controller.web;

import upn.pe.dentalClinic.model.DoctorModel;
import upn.pe.dentalClinic.service.DoctorService; 

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.ui.Model;

import java.util.List;

/**
 *
 * @author hugoroca
 */
@Controller
@Slf4j
public class DashboardController {
    @Autowired
    private DoctorService doctorService;
    
    @GetMapping("/dashboard")
    public String init() {
        log.info("Start project");
        return "dashboardView";
    }
    
    @GetMapping("/medicalhistory")
    public String medicalHistory() {
        return "medicalrecordView";
    }
    
    @GetMapping("/patient")
    public String patint() {
        return "patientView";
    }
    
    @GetMapping("/appointment")
    public String appointment() {
        return "appointmentView";
    }
    
    @GetMapping("/doctor")
    public String doctor(Model model) {
        List<DoctorModel> lstDoctor = doctorService.getAllDoctor();

        model.addAttribute("lstDoctor", lstDoctor);
        
        return "doctorView";
    }
}
