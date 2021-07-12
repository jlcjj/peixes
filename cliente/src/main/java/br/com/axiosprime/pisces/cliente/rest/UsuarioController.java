package br.com.axiosprime.pisces.cliente.rest;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.axiosprime.pisces.cliente.model.entity.Usuario;
import br.com.axiosprime.pisces.cliente.model.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/usuarios")
//@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor
public class UsuarioController {

	private final UsuarioRepository repository;

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void salvar(@RequestBody @Valid Usuario usuario) {
		repository.save(usuario);
	}
}
