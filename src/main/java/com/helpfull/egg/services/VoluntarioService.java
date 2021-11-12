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
	
	public void save(String username, String nombre, String apellido, String password, Integer telefono, String email,
				LocalDate nacimiento, MultipartFile foto) throws IOException {
		Voluntario voluntario = new Voluntario();
		
		
		voluntario.setUsername(username);;
		voluntario.setNombre(nombre);
		voluntario.setApellido(apellido);
		voluntario.setPassword(passwordEncoder.encode(password));
		voluntario.setTelefono(telefono);
		voluntario.setEmail(email);
		voluntario.setNacimiento(nacimiento);
		voluntario.setFoto(foto.getBytes());
//		voluntario.setIntereses(intereses);
		voluntario.setAlta(LocalDate.now());
		voluntario.setRol(Rol.ROLE_VOLUNTARIO);
		voluntario.setBaja(null);

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
