package br.estrategia.app.infra.security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

public class StalessAutheticationFilter extends OncePerRequestFilter {
    private static final Logger LOG = LoggerFactory.getLogger(SecurityConfig.class);


    @Override
    protected void doFilterInternal(HttpServletRequest req, HttpServletResponse response, FilterChain filterChain) throws ServletException,
            IOException {
        String token = req.getHeader("Authorization");
        if (token != null) {

            DecodedJWT jwt = JWT.decode(token);
            LOG.debug("token jwt" + jwt);

            Collection<? extends GrantedAuthority> papeis = extrairPapeis(jwt);

            UsuarioLogado usuarioLogado =
                    new UsuarioLogado(
                            papeis, jwt.getClaim("id").asLong(),
                            jwt.getClaim("nome").toString(),
                            token);


            LOG.debug("Usuario logado "+ usuarioLogado);

            SecurityContextHolder.getContext().setAuthentication(usuarioLogado);
        }
        filterChain.doFilter(req, response);
    }

    private Collection<? extends GrantedAuthority> extrairPapeis(final DecodedJWT jwt) {

        List<String> papeisDoUsuario =
                jwt.getClaims().get("roles").asList(String.class);

        LOG.debug("papeis do usuario ==> {}", papeisDoUsuario);

        if (papeisDoUsuario != null && !papeisDoUsuario.isEmpty()) {
            return papeisDoUsuario
                    .stream()
                    .map(String::toUpperCase)
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toSet());
        }

        return Collections.emptySet();
    }
}
