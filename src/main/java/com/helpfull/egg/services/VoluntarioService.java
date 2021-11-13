package com.helpfull.egg.services;

import java.io.IOException;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
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

import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Rol;
import com.helpfull.egg.repositories.VoluntarioRepository;

@Service
public class VoluntarioService implements UserDetailsService{

	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@Autowired
	private PasswordEncoder passwordEncoder;
	
	@Transactional
	public void save(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
	}
	
	public void save(String username, String password, String nombre, String apellido, String dni,
					 String telefono, LocalDate nacimiento, String email, MultipartFile foto,
					 String descripcion, String direccion) throws IOException {
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
//		voluntario.setIntereses(intereses);
		voluntario.setAlta(LocalDate.now());
		voluntario.setRol(Rol.ROLE_VOLUNTARIO);
		voluntario.setBaja(null);
		voluntario.setDescripcion(descripcion);

		voluntarioRepository.save(voluntario);
	}
	
	@Transactional
	public List<Voluntario> listartodos(){
		return voluntarioRepository.findAll();
	}
	
	@Transactional
	public Voluntario buscarPorId(String id) {
		return voluntarioRepository.getById(id);
	}
	
	@Transactional
	public void eliminar(String id) {
		voluntarioRepository.deleteById(id);
	}
	
	public void modificar(String username, String password, String nombre, String apellido, 
						  String direccion, Integer dni, String email, Integer telefono, LocalDate nacimiento) {
	Voluntario voluntario = new Voluntario();
	
	voluntario.setUsername(username);
	voluntario.setPassword(passwordEncoder.encode(password));
	voluntario.setNombre(nombre);
	voluntario.setApellido(apellido);
	voluntario.setDireccion(direccion);
	voluntario.setDni(dni);
	voluntario.setTelefono(telefono);
	voluntario.setEmail(email);
	voluntario.setNacimiento(nacimiento);
	voluntario.setFoto(voluntario.getFoto());
//	voluntario.setIntereses(intereses);
	voluntario.setAlta(voluntario.getAlta());
	voluntario.setRol(Rol.ROLE_VOLUNTARIO);
	voluntario.setBaja(null);
	voluntario.setDescripcion(voluntario.getDescripcion());
	
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

}
