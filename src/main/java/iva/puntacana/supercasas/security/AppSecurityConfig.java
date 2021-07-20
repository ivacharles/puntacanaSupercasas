package iva.puntacana.supercasas.security;

import iva.puntacana.supercasas.auth.AppUserRole;
import iva.puntacana.supercasas.auth.AppUserService;
import iva.puntacana.supercasas.jwt.JwtTokenVerifier2;
import iva.puntacana.supercasas.jwt.JwtUsernameAndPasswordAuthFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class AppSecurityConfig extends WebSecurityConfigurerAdapter {
    private final PasswordEncoder passwordEncoder;
    private final AppUserService appUserService;

    @Autowired
    public AppSecurityConfig(PasswordEncoder passwordEncoder, AppUserService appUserService) {
        this.passwordEncoder = passwordEncoder;
        this.appUserService = appUserService;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .csrf().csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) //cross site request forgery setup so javascript can't access it
                .and()
                .sessionManagement()
                .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .addFilter(new JwtUsernameAndPasswordAuthFilter(authenticationManager()))
                .addFilterAfter(new JwtTokenVerifier2(), JwtUsernameAndPasswordAuthFilter.class)
                .authorizeRequests()

                .anyRequest()
                .authenticated();
    }

    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(appUserService);
        return provider;
    }

    @Override
    public void configure(WebSecurity web) throws Exception {
        web.ignoring()
        .antMatchers("/",
              "/csrf",
              "/v2/api-docs",
              "/swagger-resources/configuration/ui",
              "/configuration/ui", "/swagger-resources",
              "/swagger-resources/configuration/security",
              "/configuration/security", "/swagger-ui.html",
              "/webjars/**");
    }
//.antMatchers("/",
//              "/csrf",
//              "/v2/api-docs",
//              "/swagger-resources/configuration/ui",
//              "/configuration/ui", "/swagger-resources",
//              "/swagger-resources/configuration/security",
//              "/configuration/security", "/swagger-ui.html",
//              "/webjars/**")
}
