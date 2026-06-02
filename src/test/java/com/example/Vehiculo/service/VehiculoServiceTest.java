package com.example.Vehiculo.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.data.domain.Sort;

import com.example.Vehiculo.Dominio.Vehiculo;
import com.example.Vehiculo.dao.IVehiculoDao;


class VehiculoServiceTest {
	@Mock
	private IVehiculoDao dao;
	
	@InjectMocks
	private VehiculoService service;
	

	@BeforeEach
	void setUp() throws Exception {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	void testListar() {
		List<Vehiculo> lista = Arrays.asList(
				new Vehiculo ( 123, "VW", "Jetta", 2015,"Rojo"),
				new Vehiculo ( 124, "Toyota", "Corolla", 2016,"Verde"),
				new Vehiculo ( 125, "Mazda", "Mazda3", 2020,"Blanco")
				);
		
		when(dao.findAll()).thenReturn(lista);
		
		List<Vehiculo> resultado = service.listar();
		
		//assertiones
		assertEquals(3, resultado.size());
		
		//Verificamos que el mock Dao llamo al menos una vez al metodo findAll
		verify(dao).findAll();
		
	}
	
	@Test
	void testGuardar() {
		Vehiculo vehiculo = new Vehiculo( 125, "JEEP", "JT", 2019,"Negro");
		
		when(dao.save(vehiculo)).thenReturn(vehiculo);
		
		Vehiculo guardado = service.guardar(vehiculo);
		
		assertNotNull(guardado);
		assertEquals("JEEP",guardado.getMarca());
		
		verify(dao).save(vehiculo);
	}
	
	@Test
	void testBuscar() {
		Vehiculo v1 = new Vehiculo(125, "JEEP", "JT", 2019,"Negro");
		
		when(dao.findById(125)).thenReturn(Optional.of(v1));
		
		Vehiculo buscado = service.buscar(125);
		
		assertNotNull(buscado);
		
		verify(dao).findById(125);
	}
	
	@Test
	void testEliminar(){
		service.eliminar(125);
		
		verify(dao).deleteById(125);
	}
	

	@Test 
	void testBuscarPorModelo() {
		Vehiculo v1 = new Vehiculo(123, "VW", "Jetta", 2015, "Rojo");
		Vehiculo v2 = new Vehiculo(124, "VW", "Golf", 2018, "Negro");

		when(dao.findByModeloIgnoreCase("VW")).thenReturn(Arrays.asList(v1, v2));
		
		List<Vehiculo> resultado = service.buscarPorModelo("VW");

		assertEquals(2, resultado.size());
		assertEquals("VW", resultado.get(0).getMarca()); 
		
		verify(dao).findByModeloIgnoreCase("VW");
	
	}
	
	@Test
	void testBuscarPorAño() {
		Vehiculo v1 = new Vehiculo ( 123, "VW", "Jetta", 2015,"Rojo");
		Vehiculo v2 = new Vehiculo ( 124, "Toyota", "Corolla", 2016,"Verde");
		Vehiculo v3 = new Vehiculo ( 125, "Mazda", "Mazda3", 2015,"Blanco");
		
		when(dao.findByAño(2015)).thenReturn(Arrays.asList(v1,v3));
		
		List<Vehiculo> resultado = service.buscarPorAño(2015);
		
		assertEquals(2,resultado.size());
		assertEquals(2015,resultado.get(1).getAño());
		
		verify(dao).findByAño(2015);
	}
	
	
	
	

}
