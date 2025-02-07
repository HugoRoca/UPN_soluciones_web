/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.model;

import java.util.Date;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import java.io.Serializable;
import lombok.Data;

/**
 *
 * @author hugoroca
 */
@Data
@Entity(name="person")
public class PersonModel {
@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "documenttype", nullable = false, length = 5)
    private String documentType;

    @Column(name = "documentnumber", nullable = false, length = 20)
    private String documentNumber;

    @Column(name = "name", nullable = false, length = 200)
    private String name;

    @Column(name = "lastname", nullable = false, length = 200)
    private String lastName;

    @Column(name = "email", nullable = false, length = 200)
    private String email;

    @Column(name = "phone", nullable = false, length = 200)
    private String phone;

    @Column(name = "address", nullable = false, length = 200)
    private String address;
}
