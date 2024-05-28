package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class BdMySql {
    //conexao com o banco
    Conexao conexao = new Conexao();
    JdbcTemplate con = conexao.getConexaoDoBanco();
    Funcionario funcionario = new FuncionarioGeral();

    public void registrarEspacoDisponivelEmDisco(LocalDateTime dataHora, Double espacoDisponivel, String tipoRegistro, Integer fkEspecificacaoComponenteDisco, Integer fkComponenteDisco, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registro (dataHora, informacaoRegistrada, tipoRegistro, fkEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, espacoDisponivel, tipoRegistro, fkEspecificacaoComponenteDisco, fkComponenteDisco, fkMaquina, fkFuncionario, fkEmpresa);
    }


    public void registrarMemoriaEmUso(LocalDateTime dataHora, Double memoriaEmUSo, String tipoRegistro, Integer fkEspecificacaoComponenteMemoria, Integer fkComponenteMemoria, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registro (dataHora, informacaoRegistrada, tipoRegistro, fkEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, memoriaEmUSo, tipoRegistro, fkEspecificacaoComponenteMemoria, fkComponenteMemoria, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarUsoProcessador(LocalDateTime dataHora, Double usoProcessador, String tipoRegistro, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registro (dataHora, informacaoRegistrada, tipoRegistro, fkEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, usoProcessador, tipoRegistro, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarTotalProcessos(LocalDateTime dataHora, Double totalProcessos, String tipoRegistroQtdeProcessos, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registro (dataHora, informacaoRegistrada, tipoRegistro, fkEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, totalProcessos, tipoRegistroQtdeProcessos, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarTemperaturaCpu(LocalDateTime dataHora, Double temperatura, String tipoRegistroTemperaturaCpu, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registro (dataHora, informacaoRegistrada, tipoRegistro, fkEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, temperatura, tipoRegistroTemperaturaCpu, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }
}
