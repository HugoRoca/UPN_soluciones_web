/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upn.pe.dentalClinic.controller.api;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import upn.pe.dentalClinic.model.AppointmentModel;
import upn.pe.dentalClinic.service.AppointmentService;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author hugoroca
 */
@RestController
@RequestMapping("/api/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    @GetMapping("")
    public ResponseEntity<List<AppointmentModel>> getAllAppointments() {
        return ResponseEntity.ok(appointmentService.getAllAppointments());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AppointmentModel> getAppointmentById(@PathVariable Integer id) {
        Optional<AppointmentModel> appointment = appointmentService.getAppointmentById(id);
        return appointment.map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<AppointmentModel> createAppointment(@RequestBody AppointmentModel appointment) {
        return ResponseEntity.ok(appointmentService.createAppointment(appointment));
    }

    @PutMapping("/{id}")
    public ResponseEntity<AppointmentModel> updateAppointment(@PathVariable Integer id, @RequestBody AppointmentModel appointment) {
        return ResponseEntity.ok(appointmentService.updateAppointment(id, appointment));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAppointment(@PathVariable Integer id) {
        appointmentService.deleteAppointment(id);
        return ResponseEntity.noContent().build();
    }
}
