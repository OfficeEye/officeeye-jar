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

    public List<Maquina> buscarDadosDeMaquinas(Integer fkEmpresa){

        List<Maquina> dadosMaquinas = con.query((String.format("SELECT * FROM maquina WHERE fkEmpresa = '%d'", fkEmpresa)),
                new BeanPropertyRowMapper<>(Maquina.class));

        return dadosMaquinas;
    }


    public void atualizarDadosDaMaquina(Maquina maquina){

        con.update("UPDATE maquina SET fabricanteSO = ?,"
                        + "sistemaOperacional  = ? WHERE idMaquina= ?",
                maquina.getFabricanteSO(), maquina.getSistemaOperacional(), maquina.getIdMaquina());
    }

    public void atualizarInformacaoEspecificacaoComponente(List<EspecificacaoComponente> especificacoes, Double memoriaTotal, Double tamanhoTotal, Double frequenciaProcessador, Maquina maquina){

        con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ?",
                tamanhoTotal, maquina.getIdMaquina(), especificacoes.get(0).getIdEspecificacaoComponente());

        con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ? ",
                memoriaTotal, maquina.getIdMaquina(), especificacoes.get(1).getIdEspecificacaoComponente());

        con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ? ",
                frequenciaProcessador, maquina.getIdMaquina(), especificacoes.get(2).getIdEspecificacaoComponente());

    }


    public void registrarEspacoDisponivelEmDisco(LocalDateTime dataHora, Double espacoDisponivel, String tipoRegistro, Integer fkEspecificacaoComponenteDisco, Integer fkComponenteDisco, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, espacoDisponivel, tipoRegistro, fkEspecificacaoComponenteDisco, fkComponenteDisco, fkMaquina, fkFuncionario, fkEmpresa);
    }


    public void registrarMemoriaEmUso(LocalDateTime dataHora, Double espacoDisponivel, String tipoRegistro, Integer fkEspecificacaoComponenteMemoria, Integer fkComponenteMemoria, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, espacoDisponivel, tipoRegistro, fkEspecificacaoComponenteMemoria, fkComponenteMemoria, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarUsoProcessador(LocalDateTime dataHora, Double usoProcessador, String tipoRegistro, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, usoProcessador, tipoRegistro, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarTotalProcessos(LocalDateTime dataHora, Double totalProcessos, String tipoRegistroQtdeProcessos, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, totalProcessos, tipoRegistroQtdeProcessos, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarTemperaturaCpu(LocalDateTime dataHora, Double temperatura, String tipoRegistroTemperaturaCpu, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, temperatura, tipoRegistroTemperaturaCpu, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }
}
