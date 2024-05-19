package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;


import java.time.LocalDateTime;
import java.util.*;

public class TesteOfficeEye {
    private static final Logger log = LoggerFactory.getLogger(TesteOfficeEye.class);
    public static void main(String[] args) {

        //scanner para login
        Scanner leitorLogin = new Scanner(System.in);
        //temporizador para o laço de repetição
        Timer timer = new Timer();

        //conexão com o banco de dados
        Conexao conexao = new Conexao();
        JdbcTemplate con = conexao.getConexaoDoBanco();

        //instância do looca para coletar dados
        Looca looca = new Looca();

        // Login
        System.out.println(String.format("""
                ----------------------------------------------------------------
                            Bem-Vindo ao monitoramento officeEye!
                ----------------------------------------------------------------
                                            LOGIN
                Email:
                """));

        String email = leitorLogin.nextLine();
        System.out.println("Senha:");
        String senha = leitorLogin.nextLine();


        //instância do login de funcionário para que haja a verificação dos dados
        LoginFuncionario login = new LoginFuncionario();

        Boolean verificacaoLogin = login.verificarLogin(email, senha, con);

       if (verificacaoLogin){

           List<Maquina> maquinaFuncionario = con.query((String.format("SELECT * FROM maquina WHERE fkFuncionario = '%d' and fkEmpresa = '%d'", login.getIdFuncionario(), login.getFkEmpresa())),
                   new BeanPropertyRowMapper<>(Maquina.class));

           if (maquinaFuncionario.isEmpty()){
               System.out.println(String.format("""
                \n
                --------------------------------------------------------------------------------------
                      Não há nenhuma máquina vinculada a você! Entre em contato com sua empresa.
                --------------------------------------------------------------------------------------
                """));

               System.exit(0);
           }else{

               System.out.println(String.format("""
                \n
                ----------------------------------------------------------------
                   Olá, %s! O monitoramento de sua máquina irá começar em 30s
                ----------------------------------------------------------------
                """, login.getNome()));

               Integer idMaquina = maquinaFuncionario.get(0).getIdMaquina();
               String modelo  = maquinaFuncionario.get(0).getModelo();
               String fabricante = looca.getSistema().getFabricante();
               String nomeMaquina = maquinaFuncionario.get(0).getNomeMaquina();
               String sistemaOperacional = looca.getSistema().getSistemaOperacional();
               Integer fkFuncionario = login.getIdFuncionario();
               Integer fkEmpresa = login.getFkEmpresa();

               Maquina maquina = new Maquina(idMaquina, modelo, fabricante, nomeMaquina, sistemaOperacional, fkFuncionario, fkEmpresa);

               if (maquinaFuncionario.get(0).getSistemaOperacional() == null){

                   con.update("UPDATE maquina SET fabricanteSO = ?,"
                                   + "sistemaOperacional  = ? WHERE idMaquina= ?",
                           maquina.getFabricante(), maquina.getSistemaOperacional(), maquina.getIdMaquina());
               }

               Integer conversor = 1000000000;
               Double memoriaTotal = looca.getMemoria().getTotal().doubleValue()/conversor;
               Double tamanhoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal().doubleValue()/conversor;
               Double frequenciaProcessador = looca.getProcessador().getFrequencia().doubleValue()/conversor;


               con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                               + "WHERE fkMaquina= ? and idEspecificacaoComponente = 1",
                       tamanhoTotal, maquina.getIdMaquina());

               con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                               + "WHERE fkMaquina= ? and idEspecificacaoComponente = 2 ",
                       memoriaTotal, maquina.getIdMaquina());

               con.update("UPDATE especificacaoComponente SET informacaoTotalEspecificacao = ?"
                               + "WHERE fkMaquina= ? and idEspecificacaoComponente = 3 ",
                       frequenciaProcessador, maquina.getIdMaquina());

               EspecificacaoComponente especificacaoComponente = new EspecificacaoComponente();
               List<EspecificacaoComponente> especificacoesDaMaquina = especificacaoComponente.buscarListaDeEspecificacoesPorMaquina(maquina.getIdMaquina(), con);

               //coleta de registros a cada 30 segundos
               TimerTask task = new TimerTask() {

                   public void run() {
                       if (verificacaoLogin) {
                           //disco - Espaço disponivel
                           con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                   LocalDateTime.now(), looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue()/conversor, "Espaço disponível", especificacoesDaMaquina.get(0).getIdEspecificacaoComponente(), 1, maquina.getIdMaquina(), login.getIdFuncionario(), maquina.getFkEmpresa());
                           //memoria - Memória em uso
                           con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                   LocalDateTime.now(), looca.getMemoria().getEmUso().doubleValue()/conversor, "Memória em uso", especificacoesDaMaquina.get(1).getIdEspecificacaoComponente(), 2, maquina.getIdMaquina(), login.getIdFuncionario(), maquina.getFkEmpresa());
                           //cpu - Uso do processador
                           con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                   LocalDateTime.now(), looca.getProcessador().getUso().doubleValue()/conversor, "Uso do processador", especificacoesDaMaquina.get(2).getIdEspecificacaoComponente(), 3, maquina.getIdMaquina(), login.getIdFuncionario(), maquina.getFkEmpresa());
                           //Total de processos
                           con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                   LocalDateTime.now(), looca.getGrupoDeProcessos().getTotalProcessos(), "Total de processos", especificacoesDaMaquina.get(2).getIdEspecificacaoComponente(), 3, maquina.getIdMaquina(), login.getIdFuncionario(), maquina.getFkEmpresa());
                           // Temperatura da cpu
                           con.update("INSERT INTO registroEspecificacaoComponente (dataHoraRegistro, registroNumero, tipoRegistro, fkEspecificacaoComponente, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) VALUES (?, ?, ?, ?, ?, ?, ?, ?)",
                                   LocalDateTime.now(), looca.getTemperatura().getTemperatura(), "Temperatura da cpu", especificacoesDaMaquina.get(2).getIdEspecificacaoComponente(), 3, maquina.getIdMaquina(), login.getIdFuncionario(), maquina.getFkEmpresa());

                           System.out.println("Captura realizada.");

                       }else{
                           timer.cancel();
                       }
                   }
               };

               long delay = 1000; // 30 segundos
               long period = 1000; // 30 segundos

               timer.scheduleAtFixedRate(task, delay, period);
           }


       }else{
           System.out.println("""
                   \n
                   --------------------------------------------------------------------------------------------------------------------
                   Erro ao logar! 
                   --------------------------------------------------------------------------------------------------------------------
                   Esse login não existe. Verifique o e-mail e senha novamente ou entre em contato com o suporte técnico da sua empresa.
                   """);
       }

    }
}
