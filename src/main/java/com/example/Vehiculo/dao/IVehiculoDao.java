package com.example.Vehiculo.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Vehiculo.Dominio.Vehiculo;

public interface IVehiculoDao extends JpaRepository<Vehiculo , Integer >{
	
	List<Vehiculo> findByModeloIgnoreCase(String modelo);
	
	
	List<Vehiculo> findByAño(int año);

}
