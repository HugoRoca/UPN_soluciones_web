/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package upn.pe.dentalClinic.repository;

import upn.pe.dentalClinic.model.PersonModel;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

/**
 *
 * @author hugoroca
 */
public interface IPersonRepository extends JpaRepository<PersonModel, Integer> {
    
}