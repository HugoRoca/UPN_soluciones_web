/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.model;

import java.util.Date;
import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author hugoroca
 */
@Data
@Entity(name="medicalhistory")
public class MedicalHistoryModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "person_id", nullable = false)
    private PersonModel person;
    
    @Column(nullable = false)
    private String diagnosis;
    
    @Column(nullable = false)
    private Date consultation_date;
    
    @Column(nullable = false)
    private String observations;
}
