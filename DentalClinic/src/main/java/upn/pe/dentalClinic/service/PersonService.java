/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.model.PersonModel;
import upn.pe.dentalClinic.repository.IPersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.Optional;

/**
 *
 * @author hugoroca
 */
@Service
public class PersonService {
    @Autowired
    private IPersonRepository personRepository;
    
    public List<PersonModel> getAllPerson() {
        return personRepository.findAll();
    }
    
    public Optional<PersonModel> getPersonById(Integer id) {
        return personRepository.findById(id);
    }

    public PersonModel savePerson(PersonModel person) {
        return personRepository.save(person);
    }

    public void deletePerson(Integer id) {
        personRepository.deleteById(id);
    }
}
