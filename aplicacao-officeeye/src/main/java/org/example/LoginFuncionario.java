package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class LoginFuncionario {
    private Integer idFuncionario;
    private String nome;
    private String email;
    private String senha;
    private Integer fkEmpresa;

    public LoginFuncionario(){}

    public Boolean verificarLogin(String email, String senha, JdbcTemplate con){
        Boolean existeFuncionario = false;

        List<LoginFuncionario> funcionarios = con.query((String.format("SELECT * FROM funcionario WHERE email = '%s' and senha = '%s'", email, senha)),
                new BeanPropertyRowMapper<>(LoginFuncionario.class));

        if (!funcionarios.isEmpty()){
            existeFuncionario = true;

            this.idFuncionario = funcionarios.get(0).getIdFuncionario();
            this.nome = funcionarios.get(0).getNome();
            this.email = funcionarios.get(0).getEmail();
            this.senha = funcionarios.get(0).getSenha();
            this.fkEmpresa = funcionarios.get(0).getFkEmpresa();
        }
        return existeFuncionario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Integer getIdFuncionario() {
        return idFuncionario;
    }

    public void setIdFuncionario(Integer idFuncionario) {
        this.idFuncionario = idFuncionario;
    }


    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "LoginFuncionario{" +
                "idFuncionario=" + idFuncionario +
                ", nome='" + nome + '\'' +
                ", email='" + email + '\'' +
                ", senha='" + senha + '\'' +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
