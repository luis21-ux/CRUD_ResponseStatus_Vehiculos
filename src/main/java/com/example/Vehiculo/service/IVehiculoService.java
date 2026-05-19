package com.example.Vehiculo.service;

import java.util.List;

import com.example.Vehiculo.Dominio.Vehiculo;

public interface IVehiculoService {

	
	Vehiculo guardar (Vehiculo ve);
	Vehiculo editar (Vehiculo ve);
	Vehiculo buscar (int ve);
	
	void eliminar (int ve);
	
	List<Vehiculo>listar();
}

