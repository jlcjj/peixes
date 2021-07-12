package br.com.axiosprime.pisces.cliente.model.entity;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.br.CPF;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Cliente {
	@Id
	@GeneratedValue(strategy  =  GenerationType.IDENTITY)
	private Integer id;
	
	@Column(nullable = false, length = 150)
	@NotEmpty(message = "{campo.nome.obrigatorio}")
	private String nome;
	
	@Column(name = "data_nascimento", nullable = true, length = 15)
	private String dataNascimento;
	
	@Column(nullable = false, length = 12)
	@NotNull(message = "{campo.cpf.invalido}")
	@CPF(message = "{campo.cpf.invalido}")
	private String cpf;
	
	@Column(nullable = true, length = 15)
	private String rg;
	
	@Column(nullable = true, length = 10)
	private String cep;
	
	@Column(nullable = true, length = 100)
	private String endereco;

	@Column(nullable = true, length = 8)
	private String numero;

	@Column(nullable = true, length = 80)
	private String complemento;

	@Column(nullable = true, length = 80)
	private String bairro;

	@Column(nullable = true, length = 80)
	private String cidade;

	@Column(nullable = true, length = 15)
	private String uf;

	@Column(name = "tel_fixo" ,nullable = true, length = 20)
	private String telFixo;

	@Column(name = "tel_celular" ,nullable = true, length = 20)
	private String telCelular;
	
	@Column(nullable = true, length = 200)
	private String observacao;

	@Column(nullable = true, length = 20)
	private String status;
	
	@Column(name = "data_cadastro" , updatable = false)
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate dataCadastro;
	
	@PrePersist
	public void prePersist() {
		setDataCadastro(LocalDate.now());
	}
}
