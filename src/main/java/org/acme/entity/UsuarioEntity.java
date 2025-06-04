package org.acme.entity; // Ou org.acme.entity se você mudou

import jakarta.persistence.*;

@Entity
@Table(name = "DW_Usuario") // Certifique-se que o nome da tabela está correto
public class UsuarioEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // <<< ALTERAÇÃO PRINCIPAL AQUI
    @Column(name = "id_usuario")
    public Long idUsuario;

    @Column(name = "nome", nullable = false, length = 255)
    public String nome;

    @Column(name = "email", nullable = false, unique = true, length = 255)
    public String email;

    @Column(name = "senha", nullable = false, length = 255)
    public String senha;

    // Getters e Setters (opcional com Panache, mas boa prática)
    // Construtores
}