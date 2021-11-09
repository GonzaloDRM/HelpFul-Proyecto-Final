package com.helpfull.egg.services;

import java.time.LocalDate;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.FamiliarAcargo;
import com.helpfull.egg.entities.Foto;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Discapacidad;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Necesidad;
import com.helpfull.egg.repositories.AmigoRepository;
import com.helpfull.egg.repositories.FamiliarRepository;

@Service
public class AmigoService {

	@Autowired
	private AmigoRepository amigoRepository;
	
	@Autowired
	private FamiliarRepository familiarRepository;
	
	@Autowired
	private FotoService fotoService;
	
	@Autowired
	private FamiliarAcargoService familiarAcargoService;
	
	//las calocciones de enum se vera como lo armamos de acuerdo a la html y como lo manda al controlador.
	
	@Transactional
	public void crearAmigo(MultipartFile archivo, String nombre, String apellido, Integer edad, String telefono, String direccion,Zona zona,
			EnumSet<Interes> intereses, EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades) throws Error{
		
		//validar(nombre,apellido,Integer.toString(edad),telefono,direccion,zona,intereses,discapacidades,necesidades);
		
		//familiarAcargoService.validarFamiliarAcargo(nombreFamiliarAcargo,apellidoFamiliarAcargo,Integer.toString(edadFamiliarAcargo),telefonoFamiliarAcargo,direccionFamiliarAcargo);
		
		Amigo amigo = new Amigo();
		
		FamiliarAcargo familiarAcargo = familiarAcargoService.crearFamiliarAcargo("Juan", "Perez", 20, "03773401401", "av norte 1300");
		familiarRepository.save(familiarAcargo);
		System.out.println("--------------asd3----------------");
		//cuando se crea un amigo se setea automaticamente la fecha y hora.
		amigo.setAlta(LocalDate.now());
		
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		
		
		amigo.setEdad(edad);
		amigo.setTelefono(telefono);
		amigo.setZona(zona);
		amigo.setIntereses(intereses);
		amigo.setDiscapacidades(discapacidades);
		amigo.setNecesidades(necesidades);
		
		amigo.setFamiliarAcargo(familiarAcargo);
		
		Foto foto = fotoService.guardar(archivo);
		amigo.setFoto(foto);
		System.out.println("--------------asd5----------------");
		amigoRepository.save(amigo);
	}
	
	//las calocciones de enum se vera como lo armamos de acuerdo a la html y como lo manda al controlador.
	
	@Transactional
	public void modificarAmigo(MultipartFile archivo, String id, String nombre, String apellido, Integer edad, String telefono, String direccion,
			Zona zona, EnumSet<Interes> intereses,
			EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades, String nombreFamiliarAcargo, String apellidoFamiliarAcargo, Integer edadFamiliarAcargo, String telefonoFamiliarAcargo,
			String direccionFamiliarAcargo) throws Error {
		
		Amigo amigo = buscarAmigoID(id);
		
		//seteamos los valores modificado de amigo
		amigo.setNombre(nombre);
		amigo.setApellido(apellido);
		amigo.setEdad(edad);
		amigo.setTelefono(telefono);
		amigo.setZona(zona);
		amigo.setIntereses(intereses);
		amigo.setDiscapacidades(discapacidades);
		amigo.setNecesidades(necesidades);
		
		//seteamos los valores modificado de familiar a cargo
		amigo.getFamiliarAcargo().setNombre(nombreFamiliarAcargo);
		amigo.getFamiliarAcargo().setApellido(apellidoFamiliarAcargo);
		amigo.getFamiliarAcargo().setEdad(edadFamiliarAcargo);
		amigo.getFamiliarAcargo().setTelefono(telefonoFamiliarAcargo);
		amigo.getFamiliarAcargo().setDireccion(direccionFamiliarAcargo);
		
		//modificamos la foto
		Foto foto = fotoService.guardar(archivo);
		amigo.setFoto(foto);
		
		amigoRepository.save(amigo);
		
	}
	
	public Amigo buscarAmigoID(String id) throws Error {
		Optional<Amigo> optional = amigoRepository.findById(id);
		if(optional.isPresent()) {
			throw new Error("No se encontro el amigo");
		}
		Amigo amigo = optional.get();
		return amigo;
	}
	
	public List<Amigo> buscarAmigosZona(Zona zona) throws Error {
		List<Amigo> amigos = amigoRepository.buscarAmigosPorZona(zona.getId());
		if(amigos == null) {
			throw new Error("No se encontraron amigos por la zona.");
		}
		return amigos;
	}
	
	public Foto buscarAmigosFoto(String id) throws Error {
		Amigo amigo = amigoRepository.buscarAmigosPorFoto(id);
		if(amigo == null) {
			throw new Error("No se encontraron amigos por la zona.");
		}
		return amigo.getFoto();
	}
	
	public void validar(String nombre, String apellido, String edad, String telefono, String direccion, Zona zona,
			EnumSet<Interes> intereses, EnumSet<Discapacidad> discapacidades, EnumSet<Necesidad> necesidades)
			throws Error {

		if (nombre.isEmpty() || nombre == null) {
			throw new Error("nombre invalido");
		}

		if (apellido.isEmpty() || apellido == null) {
			throw new Error("apellido invalido");
		}

		if (Integer.parseInt(edad) <= 0 || edad.isEmpty() || edad == null) {
			throw new Error("edad invalida");
		}

		if (telefono.isEmpty() || telefono == null) {
			throw new Error("telefono invalido");
		}

		if (direccion.isEmpty() || direccion == null) {
			throw new Error("direccion invalida");
		}

		if (zona == null) {
			throw new Error("no ingresó la zona");
		}

		if (intereses == null || intereses.isEmpty()) {
			throw new Error("no marcó ningún interes");
		}

		if (necesidades == null || necesidades.isEmpty()) {
			throw new Error("no marcó ninguna necesidad");
		}

		if (discapacidades == null || discapacidades.isEmpty()) {
			throw new Error("no marcó ninguna discapacidad");
		}
	}
	
	public Integer calcularEdad(String fechaDeNacimiento) {
		String[] fecha = fechaDeNacimiento.split("-");
		LocalDate today = LocalDate.now();
		Integer currentMonth = today.getMonthValue();
		Integer currentYear = today.getYear();
		Integer currentDay = today.getDayOfMonth();
		Integer year = 0;
		if(Integer.valueOf(fecha[1]) < currentMonth) {
			//quiere decir que ya cumplio años
			year = currentYear - Integer.valueOf(fecha[0]);
		}else if (Integer.valueOf(fecha[1]) > currentMonth) {
			//quiere decir que aun no cumplio entonces restamos 1
			year = currentYear - Integer.valueOf(fecha[0]) - 1;
		}else if(Integer.valueOf(fecha[1]) == currentMonth) {
			if(Integer.valueOf(fecha[2]) <= currentDay){
				//ya cumplio o esta en el dia del cumpleaños.
				year = currentYear - Integer.valueOf(fecha[0]);
			}else if (Integer.valueOf(fecha[2]) > currentDay) {
				//todavia no cumplio
				year = currentYear - Integer.valueOf(fecha[0]) - 1;
			}
		}
		return year;
	}
	
	public EnumSet<Discapacidad> transformarArregloAEnumSetDisc(String discapacidades){
		String[] listDisc = discapacidades.split(",");
		//sete un enumset vacio para luego llenarlo con el for
		EnumSet<Discapacidad> setDiscapacidades = EnumSet.noneOf(Discapacidad.class);
		for(int i = 0 ;  i < listDisc.length; i++) {
			switch(listDisc[i]) {
				case "NINGUNA":
					setDiscapacidades.add(Discapacidad.NINGUNA);
					break;
				case "AUDITIVA":
					setDiscapacidades.add(Discapacidad.AUDITIVA);
					break;
				case "SORDOCEGUERA":
					setDiscapacidades.add(Discapacidad.SORDOCEGUERA);
					break;
				case "INTELECTUAL":
					setDiscapacidades.add(Discapacidad.INTELECTUAL);
					break;
				case "PSICOSOCIAL":
					setDiscapacidades.add(Discapacidad.PSICOSOCIAL);
					break;
				case "MULTIPLE":
					setDiscapacidades.add(Discapacidad.MULTIPLE);
					break;
			}
		}
		
		return setDiscapacidades;
	}
	
	public EnumSet<Necesidad> transformarArregloAEnumSetNec(String necesidades){
		String[] listNec = necesidades.split(",");
		//sete un enumset vacio para luego llenarlo con el for
		EnumSet<Necesidad> setNecesidades = EnumSet.noneOf(Necesidad.class);
		for(int i = 0 ;  i < listNec.length; i++) {
			switch(listNec[i]) {
				case "REMEDIOS":
					setNecesidades.add(Necesidad.REMEDIOS);
					break;
				case "CONTENCIÓN":
					setNecesidades.add(Necesidad.CONTENCIÓN);
					break;
				case "ASISTENCIA":
					setNecesidades.add(Necesidad.ASISTENCIA);
					break;
				case "MEDICO":
					setNecesidades.add(Necesidad.MEDICO);
					break;
				case "COMIDA":
					setNecesidades.add(Necesidad.COMIDA);
					break;
				case "DINERO":
					setNecesidades.add(Necesidad.DINERO);
					break;
			}
		}
		
		return setNecesidades;
	}
}
