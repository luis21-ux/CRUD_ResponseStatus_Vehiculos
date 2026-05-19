package com.example.Vehiculo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.example.Vehiculo.Dominio.Vehiculo;
import com.example.Vehiculo.dao.IVehiculoDao;

@Service
public class VehiculoService implements IVehiculoService{

	
	@Autowired 
	private IVehiculoDao dao;

	@Override
	public Vehiculo guardar(Vehiculo ve) {
		
		return dao.save(ve);
	}

	@Override
	public Vehiculo editar(Vehiculo ve) {
		
		return dao.save(ve);
	}

	@Override
	public Vehiculo buscar(int ve) {
		
		return dao.findById(ve).orElse(null);
	}

	@Override
	public void eliminar(int ve) {
		dao.deleteById(ve);
		
	}

	@Override
	public List<Vehiculo> listar() {
		
		return dao.findAll();
	}
	
	public List<Vehiculo> buscarPorModelo (String modelo) {
		
		return dao.findByModeloIgnoreCase(modelo);
		
	}
	
	public List<Vehiculo> buscarPorAño (int año) {
		return (List<Vehiculo>) dao.findByAño(año);
	}
}
	
	
	
