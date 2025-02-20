/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package upn.pe.dentalClinic.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

/**
 *
 * @author hugoroca
 */
@Entity
@Table(name = "appointment")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AppointmentModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "appointment_id")
    private Integer appointmentId;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false)
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorModel doctor;

    @Column(name = "appointment_date", nullable = false)
    private LocalDateTime appointmentDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status = Status.PENDIENTE;
    
    @Column(name = "subject", nullable = false)
    private String subject;
    
    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "created_at", updatable = false)
    private LocalDateTime createdAt = LocalDateTime.now();

    public enum Status {
        PENDIENTE, CONFIRMADO, CANCELADO, COMPLETADO
    }
    
    public enum Type {
        ESTETICO, SALUD
    }
}
