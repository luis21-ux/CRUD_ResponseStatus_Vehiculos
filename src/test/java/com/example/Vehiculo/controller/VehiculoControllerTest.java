package com.example.Vehiculo.controller;


import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.example.Vehiculo.Dominio.Vehiculo;
import com.example.Vehiculo.service.VehiculoService;

import tools.jackson.databind.ObjectMapper;


@WebMvcTest(VehiculoController.class)
class VehiculoControllerTest {
	
	@Autowired
	private MockMvc mockMvc;
	
	@MockitoBean
	private VehiculoService service;
	
	private static final ObjectMapper mapper = new ObjectMapper();
	
	@Test
	void testListar() throws Exception {
		Vehiculo v1 = new Vehiculo(123, "VW", "Jetta", 2015, "Rojo");
		Vehiculo v2 = new Vehiculo(124, "VW", "Golf", 2018, "Negro");

		when(service.listar()).thenReturn(Arrays.asList(v1,v2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/listar"))
		.andExpect(status().isOk())
		.andExpect(jsonPath("$.length()").value(2));
	}
	
	@Test
	void testGuardar() throws Exception{
		
		Vehiculo v1 = new Vehiculo (124, "VW", "Golf", 2018, "Negro");
		
		when(service.guardar(Mockito.any(Vehiculo.class))).thenReturn(v1);
		
		mockMvc.perform(MockMvcRequestBuilders.post("/vehiculo/guardar")
				.contentType(MediaType.APPLICATION_JSON)
				.content(mapper.writeValueAsString(v1)))
		.andExpect(jsonPath("$.marca").value("VW"));	
		
	}
	
	@Test
	void testBuscar() throws Exception{
		Vehiculo v1 = new Vehiculo(123, "VW", "Jetta", 2015, "Rojo");
		
		when(service.buscar(123)).thenReturn(v1);

		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar/123"))
			.andExpect(status().isOk())
			.andExpect(jsonPath("$.marca").value("VW"));
		
	}
	
	@Test
	void testBuscarNoExiste() throws Exception{
		when(service.buscar(99)).thenReturn(null);
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar/99"))
			.andExpect(status().isNotFound());
	}
	
	@Test
	void testEliminar() throws Exception{
		mockMvc.perform(MockMvcRequestBuilders.delete("/vehiculo/eliminar/123"))
			.andExpect(status().isOk());
		
		Mockito.verify(service, Mockito.times(1)).eliminar(123);
	}
	
	@Test
	void testBuscarPorModelo() throws Exception{
		Vehiculo v1 = new Vehiculo(123, "VW", "Jetta", 2015, "Rojo");

		when(service.buscarPorModelo("Jetta")).thenReturn(Arrays.asList(v1));

		mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar-modelo/Jetta"))
		    .andExpect(status().isOk())
		    .andExpect(jsonPath("$[0].marca").value("VW"));
	}
	
	@Test
	void testBuscarPorAño() throws Exception{
		Vehiculo v1 = new Vehiculo(123, "VW", "Jetta", 2015, "Rojo");
		Vehiculo v2 = new Vehiculo(124, "VW", "Golf", 2015, "Negro");
		
		when(service.buscarPorAño(2015)).thenReturn(Arrays.asList(v1,v2));
		
		mockMvc.perform(MockMvcRequestBuilders.get("/vehiculo/buscar_a/2015"))
	    .andExpect(status().isOk())
	    .andExpect(jsonPath("$[0].año").value(2015))
	    .andExpect(jsonPath("$[1].año").value(2015));
	}
	
	

}
