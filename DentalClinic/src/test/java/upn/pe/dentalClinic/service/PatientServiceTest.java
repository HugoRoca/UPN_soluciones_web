package upn.pe.dentalClinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upn.pe.dentalClinic.model.PatientModel;
import upn.pe.dentalClinic.repository.IPatientRepository;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.Calendar;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PatientServiceTest {

    @Mock
    private IPatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    private PatientModel patient1;
    private PatientModel patient2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        patient1 = new PatientModel();
        patient1.setId(1);
        patient1.setDocumentType("DNI");
        patient1.setDocumentNumber("12345678");
        patient1.setFirstName("Juan");
        patient1.setLastName("Pérez");
        patient1.setPhone("987654321");
        patient1.setEmail("juan.perez@example.com");
        
        Calendar cal = Calendar.getInstance();
        cal.set(1990, Calendar.JANUARY, 1);
        patient1.setBirthDate(cal.getTime());

        patient2 = new PatientModel();
        patient2.setId(2);
        patient2.setDocumentType("DNI");
        patient2.setDocumentNumber("87654321");
        patient2.setFirstName("María");
        patient2.setLastName("García");
        patient2.setPhone("912345678");
        patient2.setEmail("maria.garcia@example.com");
        
        cal.set(1995, Calendar.MAY, 15);
        patient2.setBirthDate(cal.getTime());
    }

    @Test
    void getAllPatient_ShouldReturnAllPatients() {
        // Arrange
        List<PatientModel> expectedPatients = Arrays.asList(patient1, patient2);
        when(patientRepository.findAll()).thenReturn(expectedPatients);

        // Act
        List<PatientModel> result = patientService.getAllPatient();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedPatients, result);
        verify(patientRepository, times(1)).findAll();
    }

    @Test
    void getPatientById_WhenExists_ShouldReturnPatient() {
        // Arrange
        when(patientRepository.findById(1)).thenReturn(Optional.of(patient1));

        // Act
        Optional<PatientModel> result = patientService.getPatientById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(patient1.getId(), result.get().getId());
        assertEquals(patient1.getFirstName(), result.get().getFirstName());
        assertEquals(patient1.getDocumentNumber(), result.get().getDocumentNumber());
        verify(patientRepository, times(1)).findById(1);
    }

    @Test
    void getPatientById_WhenNotExists_ShouldReturnEmpty() {
        // Arrange
        when(patientRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<PatientModel> result = patientService.getPatientById(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(patientRepository, times(1)).findById(999);
    }

    @Test
    void savePatient_ShouldReturnSavedPatient() {
        // Arrange
        when(patientRepository.save(any(PatientModel.class))).thenReturn(patient1);

        // Act
        PatientModel result = patientService.savePatient(patient1);

        // Assert
        assertNotNull(result);
        assertEquals(patient1.getId(), result.getId());
        assertEquals(patient1.getFirstName(), result.getFirstName());
        assertEquals(patient1.getDocumentNumber(), result.getDocumentNumber());
        verify(patientRepository, times(1)).save(patient1);
    }

    @Test
    void deletePatient_ShouldDeletePatient() {
        // Arrange
        doNothing().when(patientRepository).deleteById(1);

        // Act
        patientService.deletePatient(1);

        // Assert
        verify(patientRepository, times(1)).deleteById(1);
    }

    @Test
    void searchPatients_ShouldReturnMatchingPatients() {
        // Arrange
        String searchQuery = "Juan";
        List<PatientModel> expectedPatients = Arrays.asList(patient1);
        when(patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery))
            .thenReturn(expectedPatients);

        // Act
        List<PatientModel> result = patientService.searchPatients(searchQuery);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(patient1.getFirstName(), result.get(0).getFirstName());
        verify(patientRepository, times(1))
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery);
    }

    @Test
    void searchPatients_WithNoMatches_ShouldReturnEmptyList() {
        // Arrange
        String searchQuery = "Nonexistent";
        when(patientRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery))
            .thenReturn(Arrays.asList());

        // Act
        List<PatientModel> result = patientService.searchPatients(searchQuery);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(patientRepository, times(1))
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery);
    }
} 