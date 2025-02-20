/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upn.pe.dentalClinic.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import upn.pe.dentalClinic.model.AppointmentModel;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import upn.pe.dentalClinic.repository.IAppointmentRepository;

/**
 *
 * @author hugoroca
 */
@Service
public class AppointmentService {

    @Autowired
    private IAppointmentRepository appointmentRepository;

    public List<AppointmentModel> getAllAppointments() {
        return appointmentRepository.findAll();
    }

    public Optional<AppointmentModel> getAppointmentById(Integer id) {
        return appointmentRepository.findById(id);
    }

    @Transactional
    public AppointmentModel createAppointment(AppointmentModel appointment) {
        return appointmentRepository.save(appointment);
    }

    @Transactional
    public AppointmentModel updateAppointment(Integer id, AppointmentModel newAppointment) {
        return appointmentRepository.findById(id)
                .map(existingAppointment -> {
                    existingAppointment.setAppointmentDate(newAppointment.getAppointmentDate());
                    existingAppointment.setStatus(newAppointment.getStatus());
                    return appointmentRepository.save(existingAppointment);
                })
                .orElseThrow(() -> new RuntimeException("Cita no encontrada con ID: " + id));
    }

    @Transactional
    public void deleteAppointment(Integer id) {
        if (!appointmentRepository.existsById(id)) {
            throw new RuntimeException("Cita no encontrada con ID: " + id);
        }
        appointmentRepository.deleteById(id);
    }
}
