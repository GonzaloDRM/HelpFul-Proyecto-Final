package com.helpfull.egg.services;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.helpfull.egg.entities.Voluntario;
import com.helpfull.egg.entities.Zona;
import com.helpfull.egg.enums.Interes;
import com.helpfull.egg.enums.Rol;
import com.helpfull.egg.repositories.VoluntarioRepository;

@Service
public class VoluntarioService implements UserDetailsService{

	@Autowired
	private VoluntarioRepository voluntarioRepository;
	
	@Transactional
	public void save(Voluntario voluntario) {
		voluntarioRepository.save(voluntario);
	}
	
	public void save(String nombre, String apellido, String password, Integer telefono, String email,
			LocalDate nacimiento, EnumSet<Interes> intereses, LocalDate alta,
			LocalDate baja, Zona zona) {
		Voluntario voluntario = new Voluntario();
		
		voluntario.setNombre(nombre);
		voluntario.setApellido(apellido);
		voluntario.setPassword(password);
		voluntario.setTelefono(telefono);
		voluntario.setEmail(email);
		voluntario.setNacimiento(nacimiento);
		voluntario.setIntereses(intereses);
		voluntario.setAlta(alta);
		voluntario.setBaja(baja);
		voluntario.setZona(zona);
		
		voluntarioRepository.save(voluntario);
	}
	
	@Transactional
	public void eliminar(String id) {
		voluntarioRepository.deleteById(id);
	}
	
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<Voluntario> voluntario = voluntarioRepository.findByNombre(username);
		
		List<GrantedAuthority> permisos = new ArrayList<>();
		
		if (voluntario.get().getRol() != null) {			
			permisos.add(new SimpleGrantedAuthority(Rol.ROLE_REGISTRADO.toString()));
			permisos.add(new SimpleGrantedAuthority(voluntario.get().getRol().toString()));
		}else {
			permisos.add(new SimpleGrantedAuthority("ROLE_GUEST"));
		}
		
		User user = new User(voluntario.get().getNombre(), voluntario.get().getPassword(), permisos);
		
		return user;
	}

}
