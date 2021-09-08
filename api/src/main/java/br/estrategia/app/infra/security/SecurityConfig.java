package br.estrategia.app.infra.security;

import br.estrategia.app.infra.rest.URI_API_PATHS;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true, jsr250Enabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);

    @Value("${rest.security.enabled}")
    private boolean segurancaHabilitada;

    /**
     * Segui a doc em: https://dev.to/toojannarong/spring-security-with-jwt-the-easiest-way-2i43
     * https://www.baeldung.com/spring-security-oauth-jwt
     *
     * @param http
     * @throws Exception
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.addFilterAfter(
                new StalessAutheticationFilter(), BasicAuthenticationFilter.class);
        if (segurancaHabilitada) {
            habilitarSeguranca(http);
        } else {
            desabilitarSeguranca(http);
        }
    }

    private void habilitarSeguranca(HttpSecurity http) throws Exception {
        LOG.debug("************************HABILITANDO SEGURANÇA**************************************************");
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers(URI_API_PATHS.READNIESS,
                        URI_API_PATHS.LIVENESS, URI_API_PATHS.ACTUATOR+"/**")
                .permitAll()

                .antMatchers(HttpMethod.GET,URI_API_PATHS.ALUNOS_API,"/**")
                .hasAnyAuthority(PapelUsuario.ESTUDANTE.toString())

                .antMatchers(HttpMethod.GET,URI_API_PATHS.CONCURSOS_API,"/**")
                .hasAnyAuthority(PapelUsuario.ESTUDANTE.toString())

                .antMatchers(URI_API_PATHS.MATRICULAS_API,"/**")
                .hasAnyAuthority(PapelUsuario.ESTUDANTE.toString())


                .antMatchers("/**")
                .hasAnyAuthority(PapelUsuario.ADMIN.toString())

                .anyRequest().authenticated()
                .and()
                .sessionManagement(sessionManagement ->
                        sessionManagement.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeRequests();

    }

    public void desabilitarSeguranca(HttpSecurity http) throws Exception {
        LOG.debug("************************DESABILITANDO SEGURANÇA**************************************************");
        http.cors().and()
                .csrf().disable()
                .authorizeRequests()
                .antMatchers("/**")
                .permitAll();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowedOrigins(Arrays.asList("*"));
        corsConfiguration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "PATCH", "DELETE", "OPTIONS"));
        corsConfiguration.setAllowedHeaders(Arrays.asList("*"));
        corsConfiguration.setAllowCredentials(true);
        corsConfiguration.setMaxAge(3600l);

        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
