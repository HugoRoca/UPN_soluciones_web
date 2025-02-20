/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package upn.pe.dentalClinic.repository;

import java.util.List;
import upn.pe.dentalClinic.model.DoctorModel;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 * @author hugoroca
 */
public interface IDoctorRepository extends JpaRepository<DoctorModel, Integer> {
    
    List<DoctorModel> findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(String firstName, String lastName);
}
