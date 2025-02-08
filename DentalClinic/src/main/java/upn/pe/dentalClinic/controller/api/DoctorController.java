/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.controller.api;

import upn.pe.dentalClinic.model.DoctorModel;
import upn.pe.dentalClinic.service.DoctorService;
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
@RequestMapping("/api/dcotor")
public class DoctorController {
    @Autowired
    private DoctorService doctorService;
    
    @GetMapping("")
    public ResponseEntity<List<DoctorModel>> getAllDoctor() {
        List<DoctorModel> lstDoctor = doctorService.getAllDoctor();
        return ResponseEntity.ok(lstDoctor);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<DoctorModel> getDoctorById(@PathVariable Integer id) {
        Optional<DoctorModel> doctor = doctorService.getDoctorById(id);
        return doctor.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<DoctorModel> createDoctor(@RequestBody DoctorModel doctor) {
        DoctorModel newDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(newDoctor);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<?> updateDoctor(@PathVariable Integer id, @RequestBody DoctorModel doctorDetails) {
        Optional<DoctorModel> doctorOptional = doctorService.getDoctorById(id);
        if (!doctorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        DoctorModel doctor = doctorOptional.get();
        doctor.setFirstName(doctorDetails.getFirstName());
        doctor.setLastName(doctorDetails.getLastName());
        doctor.setSpecialty(doctorDetails.getSpecialty());
        doctor.setPhone(doctorDetails.getPhone());
        doctor.setEmail(doctorDetails.getEmail());

        DoctorModel updatedDoctor = doctorService.saveDoctor(doctor);
        return ResponseEntity.ok(updatedDoctor);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteDoctor(@PathVariable Integer id) {
        Optional<DoctorModel> doctorOptional = doctorService.getDoctorById(id);
        if (!doctorOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        doctorService.deleteDoctor(id);
        return ResponseEntity.noContent().build();
    }
}
