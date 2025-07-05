package upn.pe.dentalClinic.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import upn.pe.dentalClinic.dto.LoginRequest;
import upn.pe.dentalClinic.exception.InvalidCredentialsException;
import upn.pe.dentalClinic.model.UserModel;
import upn.pe.dentalClinic.repository.IUserRepository;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class AuthServiceTest {

    @Mock
    private IUserRepository userRepository;

    @InjectMocks
    private AuthService authService;

    private UserModel validUser;
    private LoginRequest validLoginRequest;
    private LoginRequest invalidPasswordRequest;
    private LoginRequest nonExistentUserRequest;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        // Setup test data
        validUser = new UserModel();
        validUser.setId(1L);
        validUser.setUsername("testuser");
        validUser.setPassword("correctpassword");
        validUser.setRol("USER");
        validUser.setName("Test");
        validUser.setLastname("User");

        validLoginRequest = new LoginRequest();
        validLoginRequest.setUsername("testuser");
        validLoginRequest.setPassword("correctpassword");

        invalidPasswordRequest = new LoginRequest();
        invalidPasswordRequest.setUsername("testuser");
        invalidPasswordRequest.setPassword("wrongpassword");

        nonExistentUserRequest = new LoginRequest();
        nonExistentUserRequest.setUsername("nonexistent");
        nonExistentUserRequest.setPassword("anypassword");
    }

    @Test
    void login_WithValidCredentials_ShouldReturnUser() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(validUser));

        // Act
        UserModel result = authService.login(validLoginRequest);

        // Assert
        assertNotNull(result);
        assertEquals(validUser.getUsername(), result.getUsername());
        assertEquals(validUser.getPassword(), result.getPassword());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void login_WithInvalidPassword_ShouldThrowException() {
        // Arrange
        when(userRepository.findByUsername("testuser")).thenReturn(Optional.of(validUser));

        // Act & Assert
        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () ->
            authService.login(invalidPasswordRequest)
        );

        assertEquals("Contraseña incorrecta", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("testuser");
    }

    @Test
    void login_WithNonExistentUser_ShouldThrowException() {
        // Arrange
        when(userRepository.findByUsername("nonexistent")).thenReturn(Optional.empty());

        // Act & Assert
        InvalidCredentialsException exception = assertThrows(InvalidCredentialsException.class, () ->
            authService.login(nonExistentUserRequest)
        );

        assertEquals("Usuario no encontrado", exception.getMessage());
        verify(userRepository, times(1)).findByUsername("nonexistent");
    }
} 