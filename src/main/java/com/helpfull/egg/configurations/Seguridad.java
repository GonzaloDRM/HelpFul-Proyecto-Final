package com.helpfull.egg.configurations;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import com.helpfull.egg.services.VoluntarioService;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class Seguridad extends WebSecurityConfigurerAdapter{

	@Autowired
	@Qualifier("voluntarioService")
	public VoluntarioService voluntarioService;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(voluntarioService).passwordEncoder(new BCryptPasswordEncoder());
	}

	protected void configure(HttpSecurity http) throws Exception {
		http.headers().frameOptions().sameOrigin().and()
			.authorizeRequests().antMatchers("/css/*", "/js/*", "/img/*", "/**").permitAll()
			.and()
				.authorizeRequests().antMatchers("/list").hasRole("ADMIN")
			.and()
				.formLogin().loginPage("/login").loginProcessingUrl("/logincheck")
				.usernameParameter("nombre").passwordParameter("password")
				.defaultSuccessUrl("/").failureUrl("/login?error=error")
				.permitAll()
			.and()
				.logout().logoutUrl("/logout").logoutSuccessUrl("/").permitAll()
			.and()
				.csrf().disable();
	}
	
	@Override
	@Bean
	protected UserDetailsService userDetailsService() {
		UserDetails Admin = User.builder()
			.username("Admin")
			.password(passwordEncoder.encode("123"))
			.roles("ADMIN")
			.build();
		
		return new InMemoryUserDetailsManager(Admin);	
	}
	
}
