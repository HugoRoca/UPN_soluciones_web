/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.model.MedicalHistoryModel;
import upn.pe.dentalClinic.repository.IMedicalHistoryRepository;
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
public class MedicalHistoryService {
    
    @Autowired
    private IMedicalHistoryRepository medicalHistoryRepository;
    
    public List<MedicalHistoryModel> getAllMedicalHistories() {
        return medicalHistoryRepository.findAll();
    }

    public Optional<MedicalHistoryModel> getMedicalHistoryById(Integer id) {
        return medicalHistoryRepository.findById(id);
    }

    public MedicalHistoryModel createMedicalHistory(MedicalHistoryModel medicalHistory) {
        return medicalHistoryRepository.save(medicalHistory);
    }

    /*
    public MedicalHistoryModel updateMedicalHistory(Integer id, MedicalHistoryModel updatedMedicalHistory) {
        return medicalHistoryRepository.findById(id).map(existingHistory -> {
            existingHistory.setPaciente(updatedMedicalHistory.getPaciente());
            existingHistory.setFecha(updatedMedicalHistory.getFecha());
            existingHistory.setDiagnostico(updatedMedicalHistory.getDiagnostico());
            return medicalHistoryRepository.save(existingHistory);
        }).orElseThrow(() -> new RuntimeException("Historial médico no encontrado con ID: " + id));
    }
    */

    public void deleteMedicalHistory(Integer id) {
        if (medicalHistoryRepository.existsById(id)) {
            medicalHistoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Historial médico no encontrado con ID: " + id);
        }
    }
}
