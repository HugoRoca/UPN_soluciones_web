/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.controller.api;

import upn.pe.dentalClinic.model.MedicalRecordModel;
import upn.pe.dentalClinic.service.MedicalRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 *
 * @author hugoroca
 */
@RestController
@RequestMapping("/api/medical-record")
public class MedicalRecordController {
    @Autowired
    private MedicalRecordService medicalRecordService;
    
    @GetMapping("")
    public ResponseEntity<List<MedicalRecordModel>> getAllMedicalRecords() {
        List<MedicalRecordModel> history = medicalRecordService.getAllMedicalRecords();
        return ResponseEntity.ok(history);
    }
}
