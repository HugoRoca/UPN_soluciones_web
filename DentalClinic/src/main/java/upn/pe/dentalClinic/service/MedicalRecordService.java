/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.model.MedicalRecordModel;
import upn.pe.dentalClinic.repository.IMedicalRecordRepository;
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
public class MedicalRecordService {
    @Autowired
    private IMedicalRecordRepository medicalRecordRepository;
    
    public List<MedicalRecordModel> getAllMedicalRecords() {
        return medicalRecordRepository.findAll();
    }
}
