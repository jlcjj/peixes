package br.com.axiosprime.pisces.cliente.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.axiosprime.pisces.cliente.model.entity.Cliente;

public interface ClienteRepository extends JpaRepository<Cliente, Integer>{

}
