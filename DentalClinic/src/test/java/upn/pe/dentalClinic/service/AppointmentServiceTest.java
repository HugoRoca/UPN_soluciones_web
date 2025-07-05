package upn.pe.dentalClinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upn.pe.dentalClinic.model.AppointmentModel;
import upn.pe.dentalClinic.repository.IAppointmentRepository;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AppointmentServiceTest {

    @Mock
    private IAppointmentRepository appointmentRepository;

    @InjectMocks
    private AppointmentService appointmentService;

    private AppointmentModel appointment;
    private AppointmentModel updatedAppointment;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        
        // Setup test data
        appointment = new AppointmentModel();
        appointment.setAppointmentId(1);
        appointment.setSubject("Test Appointment");
        appointment.setAppointmentDate(LocalDateTime.now());
        appointment.setStatus(AppointmentModel.Status.PENDIENTE);
        appointment.setType("ESTETICO");
        
        updatedAppointment = new AppointmentModel();
        updatedAppointment.setAppointmentId(1);
        updatedAppointment.setSubject("Updated Appointment");
        updatedAppointment.setAppointmentDate(LocalDateTime.now().plusDays(1));
        updatedAppointment.setStatus(AppointmentModel.Status.CONFIRMADO);
        updatedAppointment.setType("SALUD");
    }

    @Test
    void getAllAppointments_ShouldReturnAllAppointments() {
        // Arrange
        List<AppointmentModel> expectedAppointments = Arrays.asList(appointment);
        when(appointmentRepository.findAll()).thenReturn(expectedAppointments);

        // Act
        List<AppointmentModel> result = appointmentService.getAllAppointments();

        // Assert
        assertNotNull(result);
        assertEquals(expectedAppointments.size(), result.size());
        verify(appointmentRepository, times(1)).findAll();
    }

    @Test
    void getAppointmentById_WhenExists_ShouldReturnAppointment() {
        // Arrange
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));

        // Act
        Optional<AppointmentModel> result = appointmentService.getAppointmentById(1);

        // Assert
        assertTrue(result.isPresent());
        assertEquals(appointment.getAppointmentId(), result.get().getAppointmentId());
        verify(appointmentRepository, times(1)).findById(1);
    }

    @Test
    void getAppointmentById_WhenNotExists_ShouldReturnEmpty() {
        // Arrange
        when(appointmentRepository.findById(999)).thenReturn(Optional.empty());

        // Act
        Optional<AppointmentModel> result = appointmentService.getAppointmentById(999);

        // Assert
        assertTrue(result.isEmpty());
        verify(appointmentRepository, times(1)).findById(999);
    }

    @Test
    void createAppointment_ShouldReturnSavedAppointment() {
        // Arrange
        when(appointmentRepository.save(any(AppointmentModel.class))).thenReturn(appointment);

        // Act
        AppointmentModel result = appointmentService.createAppointment(appointment);

        // Assert
        assertNotNull(result);
        assertEquals(appointment.getAppointmentId(), result.getAppointmentId());
        verify(appointmentRepository, times(1)).save(appointment);
    }

    @Test
    void updateAppointment_WhenExists_ShouldUpdateAndReturnAppointment() {
        // Arrange
        when(appointmentRepository.findById(1)).thenReturn(Optional.of(appointment));
        when(appointmentRepository.save(any(AppointmentModel.class))).thenReturn(updatedAppointment);

        // Act
        AppointmentModel result = appointmentService.updateAppointment(1, updatedAppointment);

        // Assert
        assertNotNull(result);
        assertEquals(updatedAppointment.getSubject(), result.getSubject());
        verify(appointmentRepository, times(1)).findById(1);
        verify(appointmentRepository, times(1)).save(any(AppointmentModel.class));
    }

    @Test
    void updateAppointment_WhenNotExists_ShouldThrowException() {
        // Arrange
        when(appointmentRepository.findById(999)).thenReturn(Optional.empty());

        // Act & Assert
        assertThrows(RuntimeException.class, () -> 
            appointmentService.updateAppointment(999, updatedAppointment)
        );
        verify(appointmentRepository, times(1)).findById(999);
        verify(appointmentRepository, never()).save(any(AppointmentModel.class));
    }

    @Test
    void deleteAppointment_WhenExists_ShouldDeleteAppointment() {
        // Arrange
        when(appointmentRepository.existsById(1)).thenReturn(true);
        doNothing().when(appointmentRepository).deleteById(1);

        // Act
        appointmentService.deleteAppointment(1);

        // Assert
        verify(appointmentRepository, times(1)).existsById(1);
        verify(appointmentRepository, times(1)).deleteById(1);
    }

    @Test
    void deleteAppointment_WhenNotExists_ShouldThrowException() {
        // Arrange
        when(appointmentRepository.existsById(999)).thenReturn(false);

        // Act & Assert
        assertThrows(RuntimeException.class, () -> 
            appointmentService.deleteAppointment(999)
        );
        verify(appointmentRepository, times(1)).existsById(999);
        verify(appointmentRepository, never()).deleteById(anyInt());
    }
} 