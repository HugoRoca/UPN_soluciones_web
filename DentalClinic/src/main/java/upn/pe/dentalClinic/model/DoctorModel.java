/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

/**
 *
 * @author hugoroca
 */
@Entity
@Table(name = "doctor")
@Getter
@Setter
public class DoctorModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doctor_id")
    private Integer doctorId;

    @Column(name = "first_name", nullable = false, length = 100)
    private String firstName;

    @Column(name = "last_name", nullable = false, length = 100)
    private String lastName;

    @Column(name = "specialty", length = 50)
    private String specialty;

    @Column(name = "phone", length = 15)
    private String phone;

    @Column(name = "email", unique = true, length = 100)
    private String email;
}
