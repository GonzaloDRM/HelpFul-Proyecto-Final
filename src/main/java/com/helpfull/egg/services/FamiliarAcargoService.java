package com.helpfull.egg.services;

import org.springframework.stereotype.Service;

import com.helpfull.egg.entities.FamiliarAcargo;

@Service
public class FamiliarAcargoService {
	
	public FamiliarAcargo crearFamiliarAcargo(String nombreFamiliarAcargo, String apellidoFamiliarAcargo, Integer edadFamiliarAcargo, String telefonoFamiliarAcargo,
			String direccionFamiliarAcargo) throws Error{
		
		validarFamiliarAcargo(nombreFamiliarAcargo, apellidoFamiliarAcargo, edadFamiliarAcargo, telefonoFamiliarAcargo, direccionFamiliarAcargo);
		
		FamiliarAcargo familiarAcargo = new FamiliarAcargo();
		
		familiarAcargo.setNombre(nombreFamiliarAcargo);
		familiarAcargo.setApellido(apellidoFamiliarAcargo);
		familiarAcargo.setEdad(edadFamiliarAcargo);
		familiarAcargo.setTelefono(telefonoFamiliarAcargo);
		familiarAcargo.setDireccion(direccionFamiliarAcargo);
		
		return familiarAcargo;
		
	}
	
	public void validarFamiliarAcargo(String nombre, String apellido, Integer edad, String telefono,
			String direccion) throws Error {

		if (nombre.isEmpty() || nombre == null) {
			throw new Error("nombre invalido");
		}

		if (apellido.isEmpty() || apellido == null) {
			throw new Error("apellido invalido");
		}

		if (edad <= 0 || edad == null) {
			throw new Error("edad invalida");
		}

		if (telefono.isEmpty() || telefono == null) {
			throw new Error("telefono invalido");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new Error("direccion invalida");
		}
	}
}
