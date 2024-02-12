package br.com.hibernate.model;

import javax.persistence.*;
import java.util.List;

@Entity
@NamedQueries({
        @NamedQuery(name = "UsuarioPessoa.findAll", query = "select u from UsuarioPessoa u"),
        @NamedQuery(name = "UsuarioPessoa.sumIds", query = "select sum(u.id) from UsuarioPessoa u"),
        @NamedQuery(name = "UsuarioPessoa.buscaPorNome", query = "select u from UsuarioPessoa u where u.nome = :nome"),
})
public class UsuarioPessoa {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private String nome;
    private String sobrenome;
    private String email;
    private String login;
    private String senha;

    @OneToMany(mappedBy = "usuarioPessoa", fetch = FetchType.EAGER)
    private List<TelefoneUser> telefoneUserList;

    public UsuarioPessoa() {}

    public UsuarioPessoa(String nome, String sobrenome, String email, String login, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.email = email;
        this.login = login;
        this.senha = senha;
    }

    public List<TelefoneUser> getTelefoneUserList() {
        return telefoneUserList;
    }

    public void setTelefoneUserList(List<TelefoneUser> telefoneUserList) {
        this.telefoneUserList = telefoneUserList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    @Override
    public String toString() {
        return "UsuarioPessoa{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", sobrenome='" + sobrenome + '\'' +
                ", email='" + email + '\'' +
                ", login='" + login + '\'' +
                ", senha='" + senha + '\'' +
                '}';
    }
}