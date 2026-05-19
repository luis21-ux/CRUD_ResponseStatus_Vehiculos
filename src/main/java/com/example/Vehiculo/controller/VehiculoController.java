package com.example.Vehiculo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.example.Vehiculo.Dominio.Vehiculo;
import com.example.Vehiculo.service.VehiculoService;

@RestController
@RequestMapping("vehiculo")
public class VehiculoController {
	
	@Autowired
	private VehiculoService service;
	
	
	
	@GetMapping("listar")
	public ResponseEntity<List<Vehiculo>>listar(){
		List<Vehiculo>lista = service.listar();
		
		if (lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		}
	}
	
	@PostMapping("guardar")
	public ResponseEntity<?> guardar(@RequestBody Vehiculo v){
		
		Vehiculo aux = service.buscar(v.getPlaca());
		
		if(aux== null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(service.guardar(v));
			
		}else {
			
			return ResponseEntity.status(HttpStatus.CONFLICT)
					.body("Error: Esa placa ya existe, intenta con otra");
		}
	}
	
	@GetMapping("buscar/{placa}")
	public ResponseEntity buscar(@PathVariable int placa) {
		
		Vehiculo aux = service.buscar(placa);
		
		if(aux == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		}
	}
	
	
	@PutMapping("editar")
	public ResponseEntity<Vehiculo>editar(@RequestBody Vehiculo v){
		return ResponseEntity.status(HttpStatus.OK).body(service.editar(v));
	}
	
	@DeleteMapping("eliminar/{placa}")
	public ResponseEntity<String>eliminar(@PathVariable int placa){
		service.eliminar(placa);
		return ResponseEntity.status(HttpStatus.OK).body("Mensaje: Eliminacion exitosa");
	}
	
	@GetMapping("buscar-modelo/{modelo}")
	public ResponseEntity<List<Vehiculo>> buscarPorModelo(@PathVariable String modelo){
		
		List<Vehiculo> aux = service.buscarPorModelo(modelo);
		
		if(aux==null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(aux);
		}
	
	}
	
	@GetMapping("buscar_a")
	public ResponseEntity<List<Vehiculo>> buscarPorAño(@RequestParam int a){
		List<Vehiculo> lista = (List<Vehiculo>) service.buscarPorAño(a);
		
		if(lista.isEmpty()) {
			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();	
			
		}else {
			return ResponseEntity.status(HttpStatus.OK).body(lista);
		}
		
	}
	

}
