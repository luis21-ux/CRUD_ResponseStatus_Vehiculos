package com.example.Vehiculo.Dominio;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "VEHICULOSs_BD")
@Data
@NoArgsConstructor   
@AllArgsConstructor 
public class Vehiculo {
	
	@Id
	private Integer placa;
	private String marca;
	private String modelo;
	private Integer año;
	private String color;
	

}
