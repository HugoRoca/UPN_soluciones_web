/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.controller.api;

import upn.pe.dentalClinic.model.PersonModel;
import upn.pe.dentalClinic.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hugoroca
 */
@RestController
@RequestMapping("/api/person")
public class PersonController {
    @Autowired
    private PersonService personService;
    
    @GetMapping("")
    public ResponseEntity<List<PersonModel>> getAllPerson() {
        List<PersonModel> history = personService.getAllPerson();
        return ResponseEntity.ok(history);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<PersonModel> getPersonById(@PathVariable Integer id) {
        Optional<PersonModel> person = personService.getPersonById(id);
        return person.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping("")
    public ResponseEntity<PersonModel> createPerson(@RequestBody PersonModel person) {
        PersonModel newPerson = personService.savePerson(person);
        return ResponseEntity.ok(newPerson);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updatePerson(@PathVariable Integer id, @RequestBody PersonModel personDetails) {
        Optional<PersonModel> personOptional = personService.getPersonById(id);
        if (!personOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        PersonModel person = personOptional.get();
        person.setDocumentType(personDetails.getDocumentType());
        person.setDocumentNumber(personDetails.getDocumentNumber());
        person.setName(personDetails.getName());
        person.setLastName(personDetails.getLastName());
        person.setEmail(personDetails.getEmail());
        person.setPhone(personDetails.getPhone());
        person.setAddress(personDetails.getAddress());

        PersonModel updatedPerson = personService.savePerson(person);
        return ResponseEntity.ok(updatedPerson);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePerson(@PathVariable Integer id) {
        Optional<PersonModel> personOptional = personService.getPersonById(id);
        if (!personOptional.isPresent()) {
            return ResponseEntity.notFound().build();
        }
        personService.deletePerson(id);
        return ResponseEntity.noContent().build();
    }
}
