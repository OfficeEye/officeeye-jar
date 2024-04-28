package org.example;

import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public class Maquina {
    private Integer idMaquina;
    private String modelo;
    private String fabricante;
    private String nomeMaquina;
    private String sistemaOperacional;
    private Integer fkFuncionario;
    private Integer fkEmpresa;

    public Maquina(){}

    public Maquina(Integer idMaquina, String modelo, String nomeMaquina, Integer fkFuncionario, Integer fkEmpresa) {
        this.idMaquina = idMaquina;
        this.modelo = modelo;
        this.fabricante = null;
        this.nomeMaquina = nomeMaquina;
        this.sistemaOperacional = null;
        this.fkFuncionario = fkFuncionario;
        this.fkEmpresa = fkEmpresa;
    }

    public Maquina buscarMaquinasAssociadasAoFuncionarioLogado (Integer fkFuncionario, Integer fkEmpresa, List<Maquina> maquinasCadastradas){
        Maquina maquinaFuncionario = null;
        for (int i = 0; i < maquinasCadastradas.size() ; i++) {
            if (maquinasCadastradas.get(i).fkFuncionario.equals(fkFuncionario) && maquinasCadastradas.get(i).fkEmpresa.equals(fkEmpresa)){
                maquinaFuncionario= maquinasCadastradas.get(i);
                this.idMaquina = maquinasCadastradas.get(i).getIdMaquina();
                this.modelo = maquinasCadastradas.get(i).getModelo();
                this.nomeMaquina = maquinasCadastradas.get(i).getNomeMaquina();
                this.fkFuncionario = fkFuncionario;
                this.fkEmpresa = fkEmpresa;
            }
        }
        return maquinaFuncionario;
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
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
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
                ", fabricante='" + fabricante + '\'' +
                ", nomeMaquina='" + nomeMaquina + '\'' +
                ", sistemaOperacional='" + sistemaOperacional + '\'' +
                ", fkFuncionario=" + fkFuncionario +
                ", fkEmpresa=" + fkEmpresa +
                '}';
    }
}
