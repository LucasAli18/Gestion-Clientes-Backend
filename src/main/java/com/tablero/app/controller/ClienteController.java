package com.tablero.app.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tablero.app.model.Cliente;
import com.tablero.app.service.ClienteService;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
@RequestMapping("/clientes")
public class ClienteController {

	
	@Autowired
	private ClienteService clienteService;

	public ClienteController(ClienteService clienteService) {
		this.clienteService = clienteService;
	}
	
	
	@GetMapping
	public ResponseEntity<List<Cliente>> listarClientes(){
		return ResponseEntity.ok(clienteService.obtenerClientes());
	}
	
	@PostMapping
	public Cliente guardarCliente(@RequestBody Cliente cliente) {
		return clienteService.guardarCliente(cliente);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<Cliente> listarClientePorId(@PathVariable Long id){
		Cliente cliente = clienteService.obtenerClientePorID(id);
		return ResponseEntity.ok(cliente);
	}
	
	
	@PutMapping("/{id}")
	public ResponseEntity<Cliente> actualizarCliente(@PathVariable Long id, @RequestBody Cliente clienteRequest){
		Cliente cliente = clienteService.obtenerClientePorID(id);
		cliente.setNombre(clienteRequest.getNombre());
		cliente.setApellido(clienteRequest.getApellido());
		cliente.setEmail(clienteRequest.getEmail());
		Cliente clienteActualizado = clienteService.guardarCliente(cliente);
		return ResponseEntity.ok(clienteActualizado);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Map<String, Boolean>> eliminarCliente(@PathVariable Long id){
		clienteService.borrarCliente(id);
		Map<String, Boolean> response = new HashMap<String, Boolean>();
		response.put("deleted",Boolean.TRUE);
		return ResponseEntity.ok(response);
	}
	
	
}
