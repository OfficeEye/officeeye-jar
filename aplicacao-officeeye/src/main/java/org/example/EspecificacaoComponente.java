package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class EspecificacaoComponente {
    private Integer idEspecificacaoComponente;
    private String nomeEspecificacao;
    private Double informacaoComponente;
    private Integer fkComponente;
    private Integer fkMaquina;
    private Integer fkFuncionario;
    private Integer fkEmpresa;



    public Integer getIdEspecificacaoComponente() {
        return idEspecificacaoComponente;
    }

    public void setIdEspecificacaoComponente(Integer idEspecificacaoComponente) {
        this.idEspecificacaoComponente = idEspecificacaoComponente;
    }

    public String getNomeEspecificacao() {
        return nomeEspecificacao;
    }

    public void setNomeEspecificacao(String nomeEspecificacao) {
        this.nomeEspecificacao = nomeEspecificacao;
    }

    public Double getInformacaoComponente() {
        return informacaoComponente;
    }

    public void setInformacaoComponente(Double informacaoComponente) {
        this.informacaoComponente = informacaoComponente;
    }

    public Integer getFkComponente() {
        return fkComponente;
    }

    public void setFkComponente(Integer fkComponente) {
        this.fkComponente = fkComponente;
    }

    public Integer getFkMaquina() {
        return fkMaquina;
    }

    public void setFkMaquina(Integer fkMaquina) {
        this.fkMaquina = fkMaquina;
    }

    public Integer getFkFuncionario() {
        return fkFuncionario;
    }

    public void setFkFuncionario(Integer fkFuncionario) {
        this.fkFuncionario = fkFuncionario;
    }

    public Integer getFkEmpresa() {
        return fkEmpresa;
    }

    public void setFkEmpresa(Integer fkEmpresa) {
        this.fkEmpresa = fkEmpresa;
    }

    @Override
    public String toString() {
        return "EspecificacaoComponente{" +
                "idEspecificacaoComponente=" + idEspecificacaoComponente +
                ", nomeEspecificacao='" + nomeEspecificacao + '\'' +
                ", informacaoComponente=" + informacaoComponente +
                ", fkComponente=" + fkComponente +
                ", fkMaquina=" + fkMaquina +
                ", fkFuncionario=" + fkFuncionario +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
