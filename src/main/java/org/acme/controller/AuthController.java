package org.acme.controller;

import jakarta.inject.Inject;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import org.acme.dto.LoginRequest; // Create this DTO
import org.acme.entity.UsuarioEntity;
import org.acme.service.AuthService;
import org.acme.service.UsuarioService; // To fetch user details

@Path("/api/auth")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class AuthController {

    @Inject
    AuthService authService;

    @Inject
    UsuarioService usuarioService; // To get full user details upon successful login

    @POST
    @Path("/login")
    public Response login(LoginRequest loginRequest) { // Use a DTO for login credentials
        try {
            boolean loginValido = authService.validarLogin(loginRequest.getEmail(), loginRequest.getSenha());
            if (loginValido) {
                // Fetch the user details to return (excluding password ideally)
                UsuarioEntity usuario = usuarioService.findByEmail(loginRequest.getEmail());
                // Consider creating a UserResponseDTO that doesn't include the password
                return Response.ok(usuario).build();
            } else {
                // This case might be covered by AuthService throwing an exception
                // or returning false for incorrect password (but not non-existent user)
                return Response.status(Response.Status.UNAUTHORIZED)
                        .entity("{\"erro\": \"Credenciais inválidas\"}")
                        .build();
            }
        } catch (NotFoundException e) { // Thrown by AuthService if user not found
            return Response.status(Response.Status.NOT_FOUND)
                    .entity("{\"erro\": \"" + e.getMessage() + "\"}")
                    .build();
        } catch (Exception e) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity("{\"erro\": \"Erro ao processar login: " + e.getMessage() + "\"}")
                    .build();
        }
    }
}