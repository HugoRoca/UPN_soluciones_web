/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.model.PatientModel;
import upn.pe.dentalClinic.repository.IPatientRepository;
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
public class PatientService {
    @Autowired
    private IPatientRepository patientRepository;
    
    public List<PatientModel> getAllPatient() {
        return patientRepository.findAll();
    }
    
    public Optional<PatientModel> getPatientById(Integer id) {
        return patientRepository.findById(id);
    }

    public PatientModel savePatient(PatientModel patient) {
        return patientRepository.save(patient);
    }

    public void deletePatient(Integer id) {
        patientRepository.deleteById(id);
    }
}
