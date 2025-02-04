/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package upn.pe.dentalClinic.service;

import upn.pe.dentalClinic.dto.LoginRequest;
import upn.pe.dentalClinic.exception.InvalidCredentialsException;
import upn.pe.dentalClinic.model.UserModel;
import upn.pe.dentalClinic.repository.IUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author hugoroca
 */
@Service
public class AuthService {
    @Autowired
    private IUserRepository userRepository;
    
    public UserModel login(LoginRequest loginRequest) {
        UserModel user = userRepository.findByUsername(loginRequest.getUsername())
                .orElseThrow(() -> new InvalidCredentialsException("Usuario no encontrado"));

        // Validar la contraseña (en un caso real, usa BCrypt para comparar contraseñas hasheadas)
        if (!user.getPassword().equals(loginRequest.getPassword())) {
            throw new InvalidCredentialsException("Contraseña incorrecta");
        }

        return user;
    }
}
