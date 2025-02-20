/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package upn.pe.dentalClinic.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import upn.pe.dentalClinic.model.AppointmentModel;

import java.time.LocalDateTime;
import java.util.List;
import upn.pe.dentalClinic.model.DoctorModel;

/**
 *
 * @author hugoroca
 */
public interface IAppointmentRepository extends JpaRepository<AppointmentModel, Integer> {

    // verifica disponibilidad de citas
    List<AppointmentModel> findByDoctorDoctorIdAndAppointmentDate(DoctorModel doctor, LocalDateTime appointmentDate);
}
