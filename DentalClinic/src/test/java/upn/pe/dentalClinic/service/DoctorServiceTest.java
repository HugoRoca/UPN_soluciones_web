package upn.pe.dentalClinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upn.pe.dentalClinic.model.DoctorModel;
import upn.pe.dentalClinic.repository.IDoctorRepository;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DoctorServiceTest {

    @Mock
    private IDoctorRepository doctorRepository;

    @InjectMocks
    private DoctorService doctorService;

    private DoctorModel doctor1;
    private DoctorModel doctor2;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        doctor1 = new DoctorModel();
        doctor1.setDoctorId(1);
        doctor1.setFirstName("John");
        doctor1.setLastName("Doe");
        doctor1.setSpecialty("Dentist");
        doctor1.setPhone("123456789");
        doctor1.setEmail("john.doe@example.com");

        doctor2 = new DoctorModel();
        doctor2.setDoctorId(2);
        doctor2.setFirstName("Jane");
        doctor2.setLastName("Smith");
        doctor2.setSpecialty("Orthodontist");
        doctor2.setPhone("987654321");
        doctor2.setEmail("jane.smith@example.com");
    }

    @Test
    void getAllDoctor_ShouldReturnAllDoctors() {
        // Arrange
        List<DoctorModel> expectedDoctors = Arrays.asList(doctor1, doctor2);
        when(doctorRepository.findAll()).thenReturn(expectedDoctors);

        // Act
        List<DoctorModel> result = doctorService.getAllDoctor();

        // Assert
        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals(expectedDoctors, result);
        verify(doctorRepository, times(1)).findAll();
    }

    @Test
    void getDoctorById_WhenExists_ShouldReturnDoctor() {
        // Arrange
        when(doctorRepository.findById(1)).thenReturn(Optional.of(doctor1));

        // Act
        Optional<DoctorModel> result = doctorService.getDoctorById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(doctor1.getDoctorId(), result.get().getDoctorId());
        assertEquals(doctor1.getFirstName(), result.get().getFirstName());
        verify(doctorRepository, times(1)).findById(1);
    }

    @Test
    void getDoctorById_WhenNotExists_ShouldReturnEmpty() {
        // Arrange
        when(doctorRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<DoctorModel> result = doctorService.getDoctorById(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(doctorRepository, times(1)).findById(999);
    }

    @Test
    void saveDoctor_ShouldReturnSavedDoctor() {
        // Arrange
        when(doctorRepository.save(any(DoctorModel.class))).thenReturn(doctor1);

        // Act
        DoctorModel result = doctorService.saveDoctor(doctor1);

        // Assert
        assertNotNull(result);
        assertEquals(doctor1.getDoctorId(), result.getDoctorId());
        assertEquals(doctor1.getFirstName(), result.getFirstName());
        verify(doctorRepository, times(1)).save(doctor1);
    }

    @Test
    void deleteDoctor_ShouldDeleteDoctor() {
        // Arrange
        doNothing().when(doctorRepository).deleteById(1);

        // Act
        doctorService.deleteDoctor(1);

        // Assert
        verify(doctorRepository, times(1)).deleteById(1);
    }

    @Test
    void searchDoctor_ShouldReturnMatchingDoctors() {
        // Arrange
        String searchQuery = "John";
        List<DoctorModel> expectedDoctors = Arrays.asList(doctor1);
        when(doctorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery))
            .thenReturn(expectedDoctors);

        // Act
        List<DoctorModel> result = doctorService.searchDoctor(searchQuery);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(doctor1.getFirstName(), result.get(0).getFirstName());
        verify(doctorRepository, times(1))
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery);
    }

    @Test
    void searchDoctor_WithNoMatches_ShouldReturnEmptyList() {
        // Arrange
        String searchQuery = "Nonexistent";
        when(doctorRepository.findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery))
            .thenReturn(Arrays.asList());

        // Act
        List<DoctorModel> result = doctorService.searchDoctor(searchQuery);

        // Assert
        assertNotNull(result);
        assertTrue(result.isEmpty());
        verify(doctorRepository, times(1))
            .findByFirstNameContainingIgnoreCaseOrLastNameContainingIgnoreCase(searchQuery, searchQuery);
    }
} 