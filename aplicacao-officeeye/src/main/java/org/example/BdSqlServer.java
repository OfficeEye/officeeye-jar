package org.example;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

public class BdSqlServer {
    //conexao com o banco
    ConexaoSql conexaoSql = new ConexaoSql();
    JdbcTemplate conSql = conexaoSql.getConexaoDoBanco();
    FuncionarioGeral funcionario = new FuncionarioGeral();
    FuncionarioAdministrador funcionarioAdm = new FuncionarioAdministrador();
    Maquina maquina = new Maquina();

    public Boolean verificarLoginFuncionario(String email, String senha){
        Boolean existeFuncionario = false;


        List<Funcionario> funcionarios = conSql.query((String.format("SELECT * FROM funcionario WHERE email = '%s' and senha = '%s'", email, senha)),
                new BeanPropertyRowMapper<>(Funcionario.class));

        if (!funcionarios.isEmpty()){
            existeFuncionario = true;

            funcionario.setIdFuncionario(funcionarios.get(0).getIdFuncionario());
            funcionario.setNome(funcionarios.get(0).getNome());
            funcionario.setEmail(funcionarios.get(0).getEmail());
            funcionario.setSenha(funcionarios.get(0).getSenha());
            funcionario.setFkEmpresa(funcionarios.get(0).getFkEmpresa());

            System.out.println(funcionario.getIdFuncionario());
        }

        return existeFuncionario;
    }



    public Boolean verificarLoginAdministrador(String email, String senha){
        Boolean existeFuncionario = false;


        List<FuncionarioAdministrador> administradores = conSql.query((String.format("SELECT * FROM usuario WHERE email = '%s' and senha = '%s'", email, senha)),
                new BeanPropertyRowMapper<>(FuncionarioAdministrador.class));

        if (!administradores.isEmpty()){
            existeFuncionario = true;

            funcionarioAdm.setIdFuncionario(administradores.get(0).getIdFuncionario());
            funcionarioAdm.setNome(administradores.get(0).getNome());
            funcionarioAdm.setEmail(administradores.get(0).getEmail());
            funcionarioAdm.setSenha(administradores.get(0).getSenha());
            funcionarioAdm.setFkEmpresa(administradores.get(0).getFkEmpresa());
            funcionarioAdm.setTipo(administradores.get(0).getTipo());
        }

        return existeFuncionario;
    }


    public FuncionarioGeral buscarFuncionarioLogado(){
        return this.funcionario;
    }

    public FuncionarioAdministrador buscarFuncionarioAdmLogado(){
        return this.funcionarioAdm;
    }

    public List<FuncionarioGeral> verificarStatusLoginFuncionarios(Integer fkEmpresa){
        List<FuncionarioGeral> statusFuncionarios = conSql.query((String.format("\n" +
                        "SELECT email, statusLogin, area FROM funcionario join maquina m on idFuncionario = fkFuncionario where m.fkEmpresa = %d", fkEmpresa)),
                new BeanPropertyRowMapper<>(FuncionarioGeral.class));

        return statusFuncionarios;
    }


    public List<Maquina> buscarDadosDeMaquinas(Integer fkEmpresa){

        List<Maquina> dadosMaquinas = conSql.query((String.format("select * from maquina where fkEmpresa = %d order by fkFuncionario", fkEmpresa)),
                new BeanPropertyRowMapper<>(Maquina.class));

        return dadosMaquinas;
    }


    public void atualizarStatusLogin(FuncionarioGeral funcionario){
        conSql.update("UPDATE funcionario SET statusLogin = ? WHERE idFuncionario = ?",
                "Logado", funcionario.getIdFuncionario());

        funcionario.setStatusLogin("Logado");
    }

    public void deslogar(FuncionarioGeral funcionario){
        conSql.update("UPDATE funcionario SET statusLogin = ? WHERE idFuncionario = ?",
                "Deslogado", funcionario.getIdFuncionario());

        funcionario.setStatusLogin("Deslogado");
    }


    public List<Maquina> buscarMaquinaDoFuncionario(){
        List<Maquina> maquinaFuncionario = conSql.query((String.format("SELECT * FROM maquina WHERE fkFuncionario = '%d' and fkEmpresa = '%d'", funcionario.getIdFuncionario(), funcionario.getFkEmpresa())),
                new BeanPropertyRowMapper<>(Maquina.class));

        return maquinaFuncionario;
    }


    public List<EspecificacaoComponente> buscarListaDeEspecificacoesPorMaquina(Maquina maquina){

        List<EspecificacaoComponente> especificacoes = conSql.query((String.format("SELECT * FROM especificacaoComponente WHERE fkMaquina = '%d'", maquina.getIdMaquina())),
                new BeanPropertyRowMapper<>(EspecificacaoComponente.class));
        return especificacoes;
    }


    public List<MetricaComponente> buscarListaDeMetricas(Maquina maquina){

        List<MetricaComponente> metricas = conSql.query((String.format("SELECT * FROM metricaComponente WHERE fkMaquina = '%d'", maquina.getIdMaquina())),
                new BeanPropertyRowMapper<>(MetricaComponente.class));
        return metricas;
    }


    public void atualizarDadosDaMaquina(Maquina maquina){

        conSql.update("UPDATE maquina SET fabricanteSO = ?,"
                        + "sistemaOperacional  = ? WHERE idMaquina= ?",
                maquina.getFabricanteSO(), maquina.getSistemaOperacional(), maquina.getIdMaquina());
    }

    public void atualizarInformacaoEspecificacaoComponente(List<EspecificacaoComponente> especificacoes, Double memoriaTotal, Double tamanhoTotal, Double frequenciaProcessador, Maquina maquina){

        conSql.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ?",
                tamanhoTotal, maquina.getIdMaquina(), especificacoes.get(0).getIdEspecificacaoComponente());

        conSql.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ? ",
                memoriaTotal, maquina.getIdMaquina(), especificacoes.get(1).getIdEspecificacaoComponente());

        conSql.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                        + "WHERE fkMaquina= ? and idEspecificacaoComponente = ? ",
                frequenciaProcessador, maquina.getIdMaquina(), especificacoes.get(2).getIdEspecificacaoComponente());

    }


    public void registrarEspacoDisponivelEmDisco(LocalDateTime dataHora, Double espacoDisponivel, String tipoRegistro, Integer fkEspecificacaoComponenteDisco, Integer fkComponenteDisco, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa, String status){

        conSql.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa, statusRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, espacoDisponivel, tipoRegistro, fkEspecificacaoComponenteDisco, fkComponenteDisco, fkMaquina, fkFuncionario, fkEmpresa, status);
    }


    public void registrarMemoriaEmUso(LocalDateTime dataHora, Double espacoDisponivel, String tipoRegistro, Integer fkEspecificacaoComponenteMemoria, Integer fkComponenteMemoria, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa, String status){

        conSql.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa, statusRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, espacoDisponivel, tipoRegistro, fkEspecificacaoComponenteMemoria, fkComponenteMemoria, fkMaquina, fkFuncionario, fkEmpresa, status);
    }

    public void registrarUsoProcessador(LocalDateTime dataHora, Double usoProcessador, String tipoRegistro, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa, String status){

        conSql.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa, statusRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, usoProcessador, tipoRegistro, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa, status);
    }

    public void registrarTotalProcessos(LocalDateTime dataHora, Double totalProcessos, String tipoRegistroQtdeProcessos, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa){

        conSql.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, totalProcessos, tipoRegistroQtdeProcessos, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa);
    }

    public void registrarTemperaturaCpu(LocalDateTime dataHora, Double temperatura, String tipoRegistroTemperaturaCpu, Integer fkEspecificacaoComponenteProcessador, Integer fkComponenteProcessador, Integer fkMaquina, Integer fkFuncionario, Integer fkEmpresa, String status){

        conSql.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa, statusRegistro) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
                dataHora, temperatura, tipoRegistroTemperaturaCpu, fkEspecificacaoComponenteProcessador, fkComponenteProcessador, fkMaquina, fkFuncionario, fkEmpresa, status);
    }
}
