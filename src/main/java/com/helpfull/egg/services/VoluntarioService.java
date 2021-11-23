package com.helpfull.egg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.helpfull.egg.entities.Amigo;
import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.InteresVoluntario;
import com.helpfull.egg.enums.Rol;
import com.helpfull.egg.repositories.AmigoRepository;
import com.helpfull.egg.repositories.VoluntarioRepository;


@Service
public class VoluntarioService implements UserDetailsService{

	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@Autowired
	private AmigoRepository amigoRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Autowired
	private ZonaService zonaService;
	
	@Transactional
	public void save(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
	}
	
	public void save(String username, String password, String nombre, String apellido, String dni,
					 String telefono, LocalDate nacimiento, String email, MultipartFile foto,
					 String descripcion, String direccion, Collection<InteresVoluntario> intereses,
					 String provincia, String localidad) throws Exception {
		
		try {
			validar(username, password, nombre, apellido, dni, telefono, nacimiento, email, descripcion, direccion, intereses, provincia, localidad);
			Voluntario voluntario = new Voluntario();
			
			voluntario.setUsername(username);
			voluntario.setPassword(passwordEncoder.encode(password));
			voluntario.setNombre(nombre);
			voluntario.setApellido(apellido);
			voluntario.setDireccion(direccion);
			voluntario.setDni(Integer.parseInt(dni));
			voluntario.setTelefono(Integer.parseInt(telefono));
			voluntario.setEmail(email);
			voluntario.setNacimiento(nacimiento);
			voluntario.setFoto(foto.getBytes());
	
			Zona zona = zonaService.buscarZona(provincia, localidad);
			voluntario.setZona(zona);
			
			voluntario.setAlta(LocalDate.now());
			voluntario.setRol(Rol.ROLE_VOLUNTARIO);
			voluntario.setBaja(null);
			voluntario.setDescripcion(descripcion);
			voluntario.setIntereses(intereses);
			
			voluntarioRepository.save(voluntario);
		}catch(Error e) {
			throw e;
		}catch(Exception e) {
			throw new Error("ingreso de datos erroneos");
		} 
	}
	
	@Transactional
	public List<Voluntario> listartodos(){
		return voluntarioRepository.findAll();
	}
	
	@Transactional
	public Voluntario buscarPorId(String id) {
		return voluntarioRepository.getById(id);
	}
	
	public void modificar(String username,String password, String nombre, String apellido, String dni,
			 String telefono, LocalDate nacimiento, String email,
			 String descripcion, String direccion, Collection<InteresVoluntario> intereses,
			 String provincia, String localidad) throws Exception {
	Voluntario voluntario = buscarPorId(username);
	validar(username, password, nombre, apellido,direccion, password, nacimiento, email, apellido, direccion, intereses, direccion, email);
	voluntario.setUsername(username);
	voluntario.setPassword(voluntario.getPassword());
	voluntario.setNombre(nombre);
	voluntario.setApellido(apellido);
	voluntario.setDireccion(direccion);
	voluntario.setDni(Integer.parseInt(dni));
	voluntario.setTelefono(Integer.parseInt(telefono));
	voluntario.setEmail(email);
	voluntario.setNacimiento(nacimiento);
	voluntario.setFoto(voluntario.getFoto());

	voluntario.setAlta(voluntario.getAlta());
	voluntario.setRol(Rol.ROLE_VOLUNTARIO);
	voluntario.setBaja(null);
	voluntario.setDescripcion(voluntario.getDescripcion());
	
	voluntarioRepository.save(voluntario);
}
	
	@Transactional
	public void agregarAmigo(String username, String id) {
		Voluntario voluntario = buscarPorId(username);
		
		List<Amigo> amigos = voluntario.getAmigos();
		amigos.add(amigoRepository.getById(id));
		
		voluntario.setUsername(username);
		voluntario.setPassword(voluntario.getPassword());
		voluntario.setNombre(voluntario.getNombre());
		voluntario.setApellido(voluntario.getApellido());
		voluntario.setDireccion(voluntario.getDireccion());
		voluntario.setDni(voluntario.getDni());
		voluntario.setTelefono(voluntario.getTelefono());
		voluntario.setEmail(voluntario.getEmail());
		voluntario.setNacimiento(voluntario.getNacimiento());
		voluntario.setFoto(voluntario.getFoto());

		voluntario.setAlta(voluntario.getAlta());
		voluntario.setRol(voluntario.getRol());
		voluntario.setBaja(null);
		voluntario.setDescripcion(voluntario.getDescripcion());
		
		voluntario.setAmigos(amigos);
		
		voluntarioRepository.save(voluntario);		
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Voluntario> voluntario = voluntarioRepository.findByUsername(username);
		
		List<GrantedAuthority> permisos = new ArrayList<>();
		
		if (voluntario.get().getRol() != null) {			
			permisos.add(new SimpleGrantedAuthority(Rol.ROLE_REGISTRADO.toString()));
			permisos.add(new SimpleGrantedAuthority(voluntario.get().getRol().toString()));
		}else if(voluntario.get().getRol().equals("ROLE_ADMIN")){
			permisos.add(new SimpleGrantedAuthority(Rol.ROLE_REGISTRADO.toString()));
			permisos.add(new SimpleGrantedAuthority(voluntario.get().getRol().toString()));
		}else {
			permisos.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}
		
		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("voluntariosession", voluntario);
		
		User user = new User(voluntario.get().getUsername(), voluntario.get().getPassword(), permisos);
		
		return user;
	}
	
	public void validar(String username,String password, String nombre, String apellido, String dni,
			 String telefono, LocalDate nacimiento, String email,
			 String descripcion, String direccion, Collection<InteresVoluntario> intereses,
			 String provincia, String localidad) throws Exception{
		try {
			if(username == null || username.isEmpty()) {
				throw new Error("El usuario es es inválido.");
			}
			if(password == null || password.isEmpty()) {
				throw new Error("El usuario es es inválido.");
			}
			if(nombre == null || nombre.isEmpty()) {
				throw new Error("El nombre ingresado es inválido.");
			}
			if(apellido == null || apellido.isEmpty()) {
				throw new Error("El apellido ingresado es inválido.");
			}
			if(dni == null || dni.length()<7) {
				throw new Error("El DNI ingresado es inválido.");
			}
			
			if(telefono == null || telefono.isEmpty()) {
				throw new Error("El telefono ingresado es inválido.");
			}
			
			LocalDate today = LocalDate.now();
			if(nacimiento == null || nacimiento.isAfter(today.minusYears(18))) {
				throw new Error("Tiene que ser mayor de edad.");
			}
			if(email == null || email.isEmpty()||!email.contains("@")) {
				throw new Error("email invalido.");
			}
			
			if(intereses.isEmpty()) {
				throw new Error("Por favor ingrese sus intereses.");
			}
			if(provincia.isEmpty() || provincia == null) {
				throw new Error("Por favor ingrese su provincia.");
			}
			if(localidad.isEmpty() || localidad == null) {
				throw new Error("Por favor ingrese su provincia.");
			}
			
		}catch(Exception e) {
			throw e;
		}catch(Error e) {
			e.printStackTrace();
			throw new Error("Error de sistema.");
		
		
	}

	}
	
}
