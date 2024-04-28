package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.ArrayList;
import java.util.List;

public class LoginFuncionario {
    private Integer idFuncionario;
    private String nome;
    private String email;
    private String senha;
    private Integer fkEmpresa;

    public LoginFuncionario(){}

    public LoginFuncionario(Integer idFuncionario, String nome, String email, String senha, Integer fkEmpresa) {
        this.idFuncionario = idFuncionario;
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.fkEmpresa = fkEmpresa;
    }

    public Boolean verificarLogin(String email, String senha, List<LoginFuncionario> funcionariosCadastrados){
        Boolean existeFuncionario = false;

        List<LoginFuncionario> funcionarioEncontrado = new ArrayList<>();
        for (int i = 0; i < funcionariosCadastrados.size(); i++) {
            if(funcionariosCadastrados.get(i).email.equals(email)){
                if (funcionariosCadastrados.get(i).senha.equals(senha)){
                    funcionarioEncontrado.add(funcionariosCadastrados.get(i));
                }else{
                    funcionarioEncontrado = null;
                }
            }
        }

        if (!funcionarioEncontrado.isEmpty() && funcionarioEncontrado != null){
            existeFuncionario = true;

            this.idFuncionario = funcionarioEncontrado.get(0).getIdFuncionario();
            this.nome = funcionarioEncontrado.get(0).getNome();
            this.email = funcionarioEncontrado.get(0).getEmail();
            this.senha = funcionarioEncontrado.get(0).getSenha();
            this.fkEmpresa = funcionarioEncontrado.get(0).getFkEmpresa();
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
