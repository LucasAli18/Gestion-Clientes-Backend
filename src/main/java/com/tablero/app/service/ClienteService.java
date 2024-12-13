package com.tablero.app.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tablero.app.exception.ResourceNotFoundException;
import com.tablero.app.model.Cliente;
import com.tablero.app.repository.EmpleadoRepository;

@Service
public class ClienteService {

	@Autowired
	private EmpleadoRepository empleadoRepository;
	
	public ClienteService(EmpleadoRepository empleadoRepository) {
		this.empleadoRepository = empleadoRepository;
	}

	public List<Cliente> obtenerClientes(){
		return empleadoRepository.findAll();
	}
	
	public Cliente obtenerClientePorID(long id) {
		return empleadoRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("El cliente con ese ID no existe: " + id));
	}
	
	public Cliente guardarCliente(Cliente cliente) {
		return empleadoRepository.save(cliente);
	}
	
	public void borrarCliente(long id) {
		Cliente cliente = empleadoRepository.findById(id).orElse(null);
		empleadoRepository.delete(cliente);
		
	}
	
	
}
