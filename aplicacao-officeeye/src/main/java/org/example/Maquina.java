package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.JdbcTemplate;

public class Maquina {
    private Integer idMaquina;
    private String modelo;
    private String fabricanteSO;
    private String nomeMaquina;
    private String sistemaOperacional;
    private Integer fkFuncionario;
    private Integer fkEmpresa;

    public Maquina(){}

    public Maquina(Integer idMaquina, String modelo, String fabricanteSO, String nomeMaquina, String sistemaOperacional, Integer fkFuncionario, Integer fkEmpresa) {
        this.idMaquina = idMaquina;
        this.modelo = modelo;
        this.fabricanteSO = fabricanteSO;
        this.nomeMaquina = nomeMaquina;
        this.sistemaOperacional = sistemaOperacional;
        this.fkFuncionario = fkFuncionario;
        this.fkEmpresa = fkEmpresa;
    }

    public void atualizarDadosDaMaquina(Integer idMaquina, JdbcTemplate con, Looca looca){

    }

    public Integer getIdMaquina() {
        return idMaquina;
    }

    public void setIdMaquina(Integer idMaquina) {
        this.idMaquina = idMaquina;
    }

    public String getModelo() {
        return modelo;
    }

    public String getFabricante() {
        return fabricanteSO;
    }

    public void setFabricante(String fabricanteSO) {
        this.fabricanteSO = fabricanteSO;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getNomeMaquina() {
        return nomeMaquina;
    }

    public void setNomeMaquina(String nomeMaquina) {
        this.nomeMaquina = nomeMaquina;
    }

    public String getSistemaOperacional() {
        return sistemaOperacional;
    }

    public void setSistemaOperacional(String sistemaOperacional) {
        this.sistemaOperacional = sistemaOperacional;
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
        return "Maquina{" +
                "idMaquina=" + idMaquina +
                ", modelo='" + modelo + '\'' +
                ", fabricanteSO='" + fabricanteSO + '\'' +
                ", nomeMaquina='" + nomeMaquina + '\'' +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", fkFuncionario=" + fkFuncionario +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
