/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */

package upn.pe.dentalClinic.dao;

import upn.pe.dentalClinic.model.UserModel;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

/**
 *
 * @author hugoroca
 */
@Repository
public interface UserDao extends CrudRepository<UserModel, Integer> {

}
