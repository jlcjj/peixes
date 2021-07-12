package br.com.axiosprime.pisces.cliente.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.axiosprime.pisces.cliente.model.entity.Usuario;

public interface UsuarioRepository  extends JpaRepository<Usuario, Integer>{

	Optional<Usuario> findByUsername(String username);
}
