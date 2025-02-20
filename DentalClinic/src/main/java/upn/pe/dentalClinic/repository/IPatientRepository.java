/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package upn.pe.dentalClinic.repository;

import upn.pe.dentalClinic.model.PatientModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

/**
 *
 * @author hugoroca
 */
public interface IPatientRepository extends JpaRepository<PatientModel, Integer> {

    List<PatientModel> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
