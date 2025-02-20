/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upn.pe.dentalClinic.controller.api;

import upn.pe.dentalClinic.model.PatientModel;
import upn.pe.dentalClinic.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hugoroca
 */
@RestController
@RequestMapping("/api/patient")
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("")
    public ResponseEntity<List<PatientModel>> getAllPatient() {
        List<PatientModel> lstPatient = patientService.getAllPatient();
        return ResponseEntity.ok(lstPatient);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PatientModel> getPatientById(@PathVariable Integer id) {
        Optional<PatientModel> patient = patientService.getPatientById(id);
        return patient.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping("/search")
    public ResponseEntity<List<PatientModel>> searchPatients(@RequestParam(defaultValue = "") String query) {
        List<PatientModel> patients;
        
        patients = patientService.searchPatients(query);

        return ResponseEntity.ok(patients);
    }

    @PostMapping("")
    public ResponseEntity<PatientModel> createPatient(@RequestBody PatientModel patient) {
        PatientModel newPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(newPatient);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePatient(@PathVariable Integer id, @RequestBody PatientModel patientDetails) {
        Optional<PatientModel> patientOptional = patientService.getPatientById(id);
        if (!patientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PatientModel patient = patientOptional.get();
        patient.setDocumentType(patientDetails.getDocumentType());
        patient.setDocumentNumber(patientDetails.getDocumentNumber());
        patient.setFirstName(patientDetails.getFirstName());
        patient.setLastName(patientDetails.getLastName());
        patient.setPhone(patientDetails.getPhone());
        patient.setEmail(patientDetails.getEmail());
        patient.setBirthDate(patientDetails.getBirthDate());

        PatientModel updatedPatient = patientService.savePatient(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePatient(@PathVariable Integer id) {
        Optional<PatientModel> patientOptional = patientService.getPatientById(id);
        if (!patientOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        patientService.deletePatient(id);
        return ResponseEntity.noContent().build();
    }
}
