/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;

import java.util.Date;
/**
 *
 * @author hugoroca
 */
@Entity
@Table(name = "medical_record")
@Getter
@Setter
public class MedicalRecordModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "record_id")
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "patient_id", nullable = false, foreignKey = @ForeignKey(name = "fk_medicalrecord_patient"))
    private PatientModel patient;

    @ManyToOne
    @JoinColumn(name = "doctor_id", foreignKey = @ForeignKey(name = "fk_medicalrecord_doctor"))
    private DoctorModel doctor;

    @Column(name = "consultation_date", columnDefinition = "DATETIME DEFAULT CURRENT_TIMESTAMP")
    private LocalDateTime consultationDate;

    @Column(name = "diagnosis", columnDefinition = "TEXT")
    private String diagnosis;

    @Column(name = "treatment", columnDefinition = "TEXT")
    private String treatment;
}
