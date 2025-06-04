package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.NotFoundException;
import org.acme.entity.UsuarioEntity;
import org.acme.repository.UsuarioRepository;

@ApplicationScoped
public class AuthService {

  @Inject
  UsuarioRepository usuarioRepository;

  public boolean validarLogin(String email, String senha) {
    UsuarioEntity usuario = usuarioRepository.findByEmail(email);

    if (usuario == null) {
      throw new NotFoundException("Usuário não encontrado com o email: " + email);
    }

    // Plain text password comparison (as in conectatrens example)
    // For production, use a secure password hashing and comparison mechanism
    return usuario.senha.equals(senha);
  }
}