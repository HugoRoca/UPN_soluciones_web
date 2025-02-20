/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.model.DoctorModel;
import upn.pe.dentalClinic.repository.IDoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 *
 * @author hugoroca
 */
@Service
public class DoctorService {
    @Autowired
    private IDoctorRepository doctorRepository;
    
    public List<DoctorModel> getAllDoctor() {
        return doctorRepository.findAll();
    }
    
    public Optional<DoctorModel> getDoctorById(Integer id) {
        return doctorRepository.findById(id);
    }

    public DoctorModel saveDoctor(DoctorModel doctor) {
        return doctorRepository.save(doctor);
    }

    public void deleteDoctor(Integer id) {
        doctorRepository.deleteById(id);
    }
    
    public List<DoctorModel> searchDoctor(String query) {
        return doctorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(query, query);
    }
}
