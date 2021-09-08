package br.estrategia.app.infra.security;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

public class UsuarioLogado implements Authentication {

    private Collection<? extends GrantedAuthority> papeis;
    private Long id;
    private String nome;
    private boolean autenticado;
    private String token;

    public UsuarioLogado(Collection<? extends GrantedAuthority> papeis, Long id, String nome, String token) {
        this.papeis = papeis;
        this.id = id;
        this.nome = nome;
        this.token = token;
        this.autenticado = true;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return papeis;
    }

    @Override
    public Object getCredentials() {
        return null;
    }

    @Override
    public String getDetails() {
        return this.token;
    }

    @Override
    public String getPrincipal() {
        return this.getName();
    }

    @Override
    public boolean isAuthenticated() {
        return autenticado;
    }

    @Override
    public void setAuthenticated(boolean b){
        this.autenticado = true;
    }

    @Override
    public String getName() {
        return this.nome;
    }

    @Override
    public String toString() {
        return "UsuarioLogado{" +
                "papeis=" + papeis +
                ", id=" + id +
                ", nome='" + nome + '\'' +
                ", autenticado=" + autenticado +
                ", token='" + token + '\'' +
                '}';
    }
}
