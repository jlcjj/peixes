package br.com.axiosprime.pisces.cliente.rest;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

import javax.validation.Valid;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import br.com.axiosprime.pisces.cliente.model.entity.Cliente;
import br.com.axiosprime.pisces.cliente.model.entity.ServicoPrestado;
import br.com.axiosprime.pisces.cliente.model.repository.ClienteRepository;
import br.com.axiosprime.pisces.cliente.model.repository.ServicoPrestadoRepository;
import br.com.axiosprime.pisces.cliente.rest.dto.ServicoPrestadoDTO;
import br.com.axiosprime.pisces.cliente.util.BigDecimalConverter;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/api/servicos-prestados")
//@CrossOrigin("http://localhost:4200")
@RequiredArgsConstructor

public class ServicoPrestadoController {
	
	private final ClienteRepository clienteRepository;
	private final ServicoPrestadoRepository repository;
	private final BigDecimalConverter bigDecimalConverter;

/*	@Autowired
	public ServicoPrestadoController(
			ClienteRepository clienteRepository,
			ServicoPrestadoRepository repository) {
		
		this.clienteRepository = clienteRepository;
		this.repository = repository;
	}*/
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ServicoPrestado salvar(@RequestBody @Valid ServicoPrestadoDTO dto) {
		LocalDate data = LocalDate.parse(dto.getData(), DateTimeFormatter.ofPattern("dd/MM/yyyy"));
		Integer idCliente = dto.getIdCliente();
		
		Cliente cliente  =  
				clienteRepository
				.findById(idCliente)
				.orElseThrow(() -> new ResponseStatusException(
						HttpStatus.BAD_REQUEST, "Cliente Inexistente!"));
		
		
		ServicoPrestado servicosPrestado = new ServicoPrestado();
		servicosPrestado.setDescricao(dto.getDescricao());
		servicosPrestado.setData(data);
		servicosPrestado.setCliente(cliente);
		servicosPrestado.setValor(bigDecimalConverter.converter(dto.getPreco()));
		
		return repository.save(servicosPrestado);
	}
	
	@GetMapping
	public List<ServicoPrestado> pesquisar(
			@RequestParam(value = "nome", required = false, defaultValue = "") String nome,
			@RequestParam(value = "mes", required = false) Integer mes
	){
		
		return repository.findByNomeClienteAndMes("%" + nome + "%", mes);
	}
	
}
