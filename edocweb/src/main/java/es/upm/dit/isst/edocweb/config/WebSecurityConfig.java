// package es.upm.dit.isst.edocweb.config;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
// 	@Autowired
//     private CustomAuthenticationProvider authProvider;

//     @Override
//     protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//         auth.authenticationProvider(authProvider);
// 	}

// 	@Override
// 	protected void configure(HttpSecurity http) throws Exception {
// 		http
// 		.csrf().disable()
//         .authorizeRequests()
// 			.antMatchers("/css/**", "/img/**", "/layouts/**").permitAll()
// 			.antMatchers("/", "/lista-asignaturas-profesor").permitAll()
// 			.antMatchers("/formulario_valoracion").hasRole("ALUM")
// 			.antMatchers("/formulario_asignatura").hasRole("ADMIN")
// 			.antMatchers("/crear", "/guardar").permitAll()
// 			.anyRequest().authenticated()
//         .and()
//             .formLogin()
// 				.loginPage("/login")
// 				.permitAll()
// 		.and()
//             .logout()
// 			.permitAll();

//         // .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS) //NEW
//         // .and()
//         // .authorizeRequests()
// 		// 	.antMatchers("/login").permitAll() // sustituye por formLogin y logout
// 		// 	.antMatchers("/lista").permitAll()
//         //     .anyRequest().authenticated()
//         // .and()
// 		// 	.addFilterBefore(jwtAuthorizationFilter, UsernamePasswordAuthenticationFilter.class);

// 	}
// }

package es.upm.dit.isst.edocweb.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

	
	@Configuration
	@EnableWebSecurity
	public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
		@Autowired
		private CustomAuthenticationProvider authProvider;
	
		@Override
		protected void configure(AuthenticationManagerBuilder auth) throws Exception {
			auth.authenticationProvider(authProvider);
		}

	/*@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
		.authorizeRequests()	// Definimos quien puede acceder a los recursos
		.antMatchers("/css/**", "/img/**", "/layouts/**").permitAll()
		.antMatchers("/").permitAll()
		.antMatchers("/lista-asignaturas-profesor").permitAll()
		.antMatchers("/crear").hasRole("ADMIN")
		//.antMatchers("/crear").permitAll()
		.antMatchers("/guardar").permitAll()
		.antMatchers("/eliminar/{id}").hasRole("ADMIN")
		.antMatchers("/lista-valoraciones/crearval").hasRole("ALUM")
		.antMatchers("/guardarval").hasRole("ALUM")
		.anyRequest().authenticated()
		.and()
            .formLogin()
				.loginPage("/login")
				.permitAll()
		.and()
            .logout()
			.permitAll();
	}*/
}