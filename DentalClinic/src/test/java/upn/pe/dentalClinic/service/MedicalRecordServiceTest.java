package upn.pe.dentalClinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upn.pe.dentalClinic.model.MedicalRecordModel;
import upn.pe.dentalClinic.repository.IMedicalRecordRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class MedicalRecordServiceTest {

    @Mock
    private IMedicalRecordRepository medicalRecordRepository;

    @InjectMocks
    private MedicalRecordService medicalRecordService;

    private MedicalRecordModel medicalRecord1;
    private MedicalRecordModel medicalRecord2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        medicalRecord1 = new MedicalRecordModel();
        medicalRecord1.setId(1);
        medicalRecord1.setDiagnosis("Caries dental");
        medicalRecord1.setTreatment("Empaste");
        medicalRecord1.setConsultationDate(LocalDateTime.now());

        medicalRecord2 = new MedicalRecordModel();
        medicalRecord2.setId(2);
        medicalRecord2.setDiagnosis("Gingivitis");
        medicalRecord2.setTreatment("Limpieza dental");
        medicalRecord2.setConsultationDate(LocalDateTime.now().plusDays(1));
    }

    @Test
    void getAllMedicalRecords_ShouldReturnAllRecords() {
        // Arrange
        List<MedicalRecordModel> expectedRecords = Arrays.asList(medicalRecord1, medicalRecord2);
        when(medicalRecordRepository.findAll()).thenReturn(expectedRecords);

        // Act
        List<MedicalRecordModel> result = medicalRecordService.getAllMedicalRecords();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedRecords, result);
        verify(medicalRecordRepository, times(1)).findAll();
    }

    @Test
    void getAllMedicalRecords_WhenEmpty_ShouldReturnEmptyList() {
        // Arrange
        when(medicalRecordRepository.findAll()).thenReturn(Arrays.asList());

        // Act
        List<MedicalRecordModel> result = medicalRecordService.getAllMedicalRecords();

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(medicalRecordRepository, times(1)).findAll();
    }
} 