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
public class VoluntarioService implements UserDetailsService {

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

	public void save(String username, String password, String nombre, String apellido, String dni, String telefono,
			LocalDate nacimiento, String email, MultipartFile foto, String descripcion, String direccion,
			Collection<InteresVoluntario> intereses, String provincia, String localidad) throws Exception {
		
		validar(username, password, nombre, apellido, dni, telefono, nacimiento, email, foto, descripcion, direccion,
				intereses, provincia, localidad);
		
		Voluntario voluntario = new Voluntario();

		voluntario.setUsername(username);
		voluntario.setPassword(passwordEncoder.encode(password));
		voluntario.setNombre(nombre);
		voluntario.setApellido(apellido);
		voluntario.setDireccion(direccion);
		voluntario.setDni(Integer.parseInt(dni));
		voluntario.setTelefono(Long.parseLong(telefono));
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
	}

	@Transactional
	public List<Voluntario> listartodos() {
		return voluntarioRepository.findAll();
	}

	@Transactional
	public Voluntario buscarPorId(String id) {
		return voluntarioRepository.getById(id);
	}

	public void modificar(String username, String password, String nombre, String apellido, String direccion,
			Integer dni, String email, String telefono, LocalDate nacimiento) {
		Voluntario voluntario = buscarPorId(username);
		
		validarModificacion(nombre, apellido, dni, telefono, nacimiento, email, direccion);

		voluntario.setUsername(username);
		voluntario.setPassword(voluntario.getPassword());
		voluntario.setNombre(nombre);
		voluntario.setApellido(apellido);
		voluntario.setDireccion(direccion);
		voluntario.setDni(dni);
		voluntario.setTelefono(Long.parseLong(telefono));
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
		} else if (voluntario.get().getRol().equals("ROLE_ADMIN")) {
			permisos.add(new SimpleGrantedAuthority(Rol.ROLE_REGISTRADO.toString()));
			permisos.add(new SimpleGrantedAuthority(voluntario.get().getRol().toString()));
		} else {
			permisos.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}

		ServletRequestAttributes attr = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
		HttpSession session = attr.getRequest().getSession(true);
		session.setAttribute("voluntariosession", voluntario);

		User user = new User(voluntario.get().getUsername(), voluntario.get().getPassword(), permisos);

		return user;
	}

	public void validar(String username, String password, String nombre, String apellido, String dni, String telefono,
			LocalDate nacimiento, String email, MultipartFile foto, String descripcion, String direccion,
			Collection<InteresVoluntario> intereses, String provincia, String localidad) throws Error {
	
		if(voluntarioRepository.findById(username).isPresent()) {
			throw new Error("Ese nombre de usuario ya existe");
		}
		
		if(username == null || username.isEmpty()) {
			throw new Error("Ingresó un nombre de usuario vacio o nulo");
		}
		
		if(password == null || password.isEmpty()) {
			throw new Error("Ingresó una contraseña vacia o nula");
		}
		
		if (nombre == null || nombre.isEmpty()) {
			throw new Error("Ingresó un nombre vacio o nulo");
		}

		if (apellido == null || apellido.isEmpty()) {
			throw new Error("Ingresó el apellido vacio o nulo");
		}
		
		if (dni == null || dni.isEmpty()) {
			throw new Error("Ingresó un DNI vacio o nulo");
		}

		if (telefono == null || telefono.isEmpty()) {
			throw new Error("Ingresó el telefono vacio o nulo");
		}

		if (foto == null || foto.isEmpty()) {
			throw new Error("Debe ingresar al menos una foto");
		}

		if (telefono.length() < 6) {
			throw new Error("El telefono no puede tener menos de 6 digitos.");
		}

		if (provincia == null || provincia.isEmpty()) {
			throw new Error("No ingresó una provincia o es nula.");
		}

		if (localidad == null || localidad.isEmpty()) {
			throw new Error("No ingresó una localidad o es nula.");
		}

		if (nacimiento == null) {
			throw new Error("No ingresó una fecha de nacimiento.");
		}

		if (intereses == null || intereses.isEmpty()) {
			throw new Error("Debe seleccionar al menos un interes.");
		}
	}
	
	public void validarModificacion(String nombre, String apellido, Integer dni, String telefono, 
									LocalDate nacimiento, String email, String direccion) throws Error {
		
		if (nombre == null || nombre.isEmpty()) {
			throw new Error("Ingresó un nombre vacio o nulo");
		}

		if (apellido == null || apellido.isEmpty()) {
			throw new Error("Ingresó el apellido vacio o nulo");
		}
		
		if (dni == null) {
			throw new Error("Ingresó un DNI vacio o nulo");
		}

		if (telefono == null) {
			throw new Error("Ingresó el telefono vacio o nulo");
		}

		if (telefono.length() < 6) {
			throw new Error("El telefono no puede tener menos de 6 digitos.");
		}

		if (nacimiento == null) {
			throw new Error("No ingresó una fecha de nacimiento.");
		}
	}

}
