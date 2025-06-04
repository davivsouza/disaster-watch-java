package org.acme.service;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.transaction.Transactional;
import jakarta.ws.rs.NotFoundException;
import org.acme.entity.UsuarioEntity;
import org.acme.repository.UsuarioRepository;

import java.util.List;

@ApplicationScoped
public class UsuarioService {

    @Inject
    UsuarioRepository usuarioRepository;

    public List<UsuarioEntity> listAll() {
        return usuarioRepository.listAll();
    }

    public UsuarioEntity findById(Long id) {
        return usuarioRepository.findByIdOptional(id)
                .orElseThrow(() -> new NotFoundException("Usuário não encontrado com id: " + id));
    }

    public UsuarioEntity findByEmail(String email) {
        UsuarioEntity user = usuarioRepository.findByEmail(email);
        if (user == null) {
            throw new NotFoundException("Usuário não encontrado com email: " + email);
        }
        return user;
    }

    @Transactional
    public UsuarioEntity create(UsuarioEntity usuario) {
        // Add any validation or hashing logic here if needed
        // Ex: usuario.senha = hashPassword(usuario.senha);
        usuarioRepository.persist(usuario);
        return usuario;
    }

    @Transactional
    public UsuarioEntity update(Long id, UsuarioEntity usuarioData) {
        UsuarioEntity entity = findById(id);
        entity.nome = usuarioData.nome;
        entity.email = usuarioData.email;
        if (usuarioData.senha != null && !usuarioData.senha.isEmpty()) {
             // Add hashing if implementing: entity.senha = hashPassword(usuarioData.senha);
            entity.senha = usuarioData.senha;
        }
        return entity;
    }

    @Transactional
    public void delete(Long id) {
        UsuarioEntity entity = findById(id);
        usuarioRepository.delete(entity);
    }
}