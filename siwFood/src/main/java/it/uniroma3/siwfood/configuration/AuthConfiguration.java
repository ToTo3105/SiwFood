package it.uniroma3.siwfood.configuration;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import it.uniroma3.siwfood.model.RuoloUtente;

@Configuration
@EnableWebSecurity
public class AuthConfiguration {

    @Autowired
    private DataSource dataSource;

    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception{
        auth.jdbcAuthentication()
        .dataSource(dataSource)
        .authoritiesByUsernameQuery("SELECT username, ruolo_utente FROM credenziali WHERE username=?")
        .usersByUsernameQuery("SELECT username, password, 1 as enabled FROM credenziali WHERE username=?");
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        return passwordEncoder;
    }

    @Bean
    protected SecurityFilterChain configure(final HttpSecurity httpSecurity) throws Exception{
        httpSecurity
        .csrf().and().cors().disable()
        .authorizeRequests()
        .requestMatchers(HttpMethod.GET, "/amministratore/**").hasAnyAuthority(RuoloUtente.AMMINISTRATORE.toString())
        .requestMatchers(HttpMethod.POST, "/amministratore/**").hasAnyAuthority(RuoloUtente.AMMINISTRATORE.toString())
        .requestMatchers(HttpMethod.GET, "/cuoco/**").hasAnyAuthority(RuoloUtente.CUOCO.toString())
        .requestMatchers(HttpMethod.POST, "/cuoco/**").hasAnyAuthority(RuoloUtente.CUOCO.toString())
        .anyRequest().permitAll()
        .and().formLogin()
        .loginPage("/login").permitAll()
        .defaultSuccessUrl("/success", true)
        .failureUrl("/failure")
        .and().logout()
        .logoutSuccessUrl("/")
        .invalidateHttpSession(true)
        .deleteCookies("JSESSIONID")
        .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
        .clearAuthentication(true).permitAll();
        return httpSecurity.build();
    }
}