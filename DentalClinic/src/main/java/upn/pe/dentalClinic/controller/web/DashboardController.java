/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.controller.web;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author hugoroca
 */
@Controller
@Slf4j
public class DashboardController {
    @GetMapping("/dashboard")
    public String init() {
        log.info("Start project");
        return "dashboard";
    }
    
    @GetMapping("/medicalhistory")
    public String medicalHistory() {
        return "medicalhistory";
    }
    
    @GetMapping("/person")
    public String person() {
        return "person";
    }
}
