package br.com.axiosprime.pisces.cliente;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import br.com.axiosprime.pisces.cliente.model.entity.Cliente;
import br.com.axiosprime.pisces.cliente.model.repository.ClienteRepository;

@SpringBootApplication
public class ClienteApplication {

	@Bean
	public CommandLineRunner run(@Autowired ClienteRepository repository) {
		return args -> {
			Cliente cliente = Cliente.builder().cpf("83015817191").nome("JOHNNY LUIZ CRISTOVAN").build();
			repository.save(cliente);
		};
	}
	
	public static void main(String[] args) {
		SpringApplication.run(ClienteApplication.class, args);
	}

}
