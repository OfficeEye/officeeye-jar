package org.example;

public class MetricaComponente {
    private Double porcentagemIdeal;
    private Double porcentagemAlerta;
    private Double porcentagemCritico;
    private Integer fkEspecificacaoComponente;
    private Integer fkComponente;
    private Integer fkMaquina;
    private Integer fkFuncionario;
    private Integer fkEmpresa;
    private String nomeMetrica;

    public Double getPorcentagemIdeal() {
        return porcentagemIdeal;
    }

    public void setPorcentagemIdeal(Double porcentagemIdeal) {
        this.porcentagemIdeal = porcentagemIdeal;
    }

    public Double getPorcentagemAlerta() {
        return porcentagemAlerta;
    }

    public void setPorcentagemAlerta(Double porcentagemAlerta) {
        this.porcentagemAlerta = porcentagemAlerta;
    }

    public Double getPorcentagemCritico() {
        return porcentagemCritico;
    }

    public void setPorcentagemCritico(Double porcentagemCritico) {
        this.porcentagemCritico = porcentagemCritico;
    }

    public Integer getFkEspecificacaoComponente() {
        return fkEspecificacaoComponente;
    }

    public void setFkEspecificacaoComponente(Integer fkEspecificacaoComponente) {
        this.fkEspecificacaoComponente = fkEspecificacaoComponente;
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

    public String getNomeMetrica() {
        return nomeMetrica;
    }

    public void setNomeMetrica(String nomeMetrica) {
        this.nomeMetrica = nomeMetrica;
    }

    @Override
    public String toString() {
        return "MetricaComponente{" +
                "porcentagemIdeal=" + porcentagemIdeal +
                ", porcentagemAlerta=" + porcentagemAlerta +
                ", porcentagemCritico=" + porcentagemCritico +
                ", fkEspecificacaoComponente=" + fkEspecificacaoComponente +
                ", fkComponente=" + fkComponente +
                ", fkMaquina=" + fkMaquina +
                ", fkFuncionario=" + fkFuncionario +
                ", fkEmpresa=" + fkEmpresa +
                ", nomeMetrica='" + nomeMetrica + '\'' +
                '}';
    }
}
