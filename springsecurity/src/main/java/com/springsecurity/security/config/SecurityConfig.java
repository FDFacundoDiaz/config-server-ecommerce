package com.springsecurity.security.config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

import java.util.ArrayList;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        return httpSecurity
                .csrf(csrf -> csrf.disable())
                .httpBasic(Customizer.withDefaults())
                .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                /*.authorizeHttpRequests(http -> {
            // Endpoints públicos
            http.requestMatchers(HttpMethod.GET, "/holanoseg").permitAll();
            http.requestMatchers(HttpMethod.GET, "/holaseg").hasAuthority("READ");
            http.anyRequest().denyAll();
        })*/
            .build();
    }


    //creamos authentication manager
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }
    //creamos authentication provider
    //Agregamos el user Details Service como parámetro
    @Bean
    public AuthenticationProvider authenticationProvider(UserDetailsService userDetailsService){
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        //sacamos el anterior, el lógico y agregamos el nuevo
        //  provider.setUserDetailsService(userDetailsService());
        provider.setUserDetailsService(userDetailsService);


        return provider;
    }

   /* @Bean
    public UserDetailsService userDetailsService () {
        List<UserDetails> userDetailsList = new ArrayList<>();

        userDetailsList.add(User.withUsername("todocode")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("ADMIN")
                .authorities("CREATE", "READ", "UPDATE", "DELETE")
                .build());

        userDetailsList.add(User.withUsername("seguidor")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("USER")
                .authorities("READ")
                .build());

        userDetailsList.add(User.withUsername("actualizador")
                .password("1234") // esto si no está codificado, sino, tiene que seguir el algoritmo de codificación
                .roles("USER")
                .authorities("UPDATE")
                .build());

        return new InMemoryUserDetailsManager(userDetailsList);
    }*/

    //password encoder
    @Bean
    public PasswordEncoder passwordEncoder(){
        //return new BCryptPasswordEncoder();
         return NoOpPasswordEncoder.getInstance();
    }


}