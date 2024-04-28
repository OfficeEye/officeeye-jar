package org.example;

import com.github.britooo.looca.api.core.Looca;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.*;

public class TesteOfficeEye {
    public static void main(String[] args) {

        //lista simulando a tabela de funcionarios cadastrados
        List<LoginFuncionario> funcionariosCadastrados = new ArrayList<>();

        LoginFuncionario funcionario1 = new LoginFuncionario(1, "Maria", "maria.viegas@gmail.com", "m123", 1);
        LoginFuncionario funcionario2 = new LoginFuncionario(2, "Felipe", "felipe@gmail.com", "f321", 1);
        LoginFuncionario funcionario3 = new LoginFuncionario(3, "Guilherme", "guilherme@gmail.com", "g123", 1);

        funcionariosCadastrados.add(funcionario1);
        funcionariosCadastrados.add(funcionario2);
        funcionariosCadastrados.add(funcionario3);


        //Lista simulando a tabela de máquinas cadastradas
        List<Maquina> maquinasCadastradas = new ArrayList<>();

        Maquina maquina1 = new Maquina(1, "modelo1", "maquina-maria", 1, 1);
        Maquina maquina2 = new Maquina(2, "modelo2", "maquina-guilherme", 3, 1);

        maquinasCadastradas.add(maquina1);
        maquinasCadastradas.add(maquina2);


        //scanner para login
        Scanner leitorLogin = new Scanner(System.in);
        //temporizador para o laço de repetição
        Timer timer = new Timer();
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
        //instância de máquina para verificar qual a máquina ligada ao usuario logado
        Maquina maquina = new Maquina();

        Boolean verificacaoLogin = login.verificarLogin(email, senha, funcionariosCadastrados);

        if (verificacaoLogin){

            Maquina maquinaFuncionario = maquina.buscarMaquinasAssociadasAoFuncionarioLogado(login.getIdFuncionario(), login.getFkEmpresa(), maquinasCadastradas);

            if (maquinaFuncionario == null){
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
                   Olá, %s! O monitoramento de sua máquina irá começar em 10s
                ----------------------------------------------------------------
                """, login.getNome()));


                String fabricante = looca.getSistema().getFabricante();
                String sistemaOperacional = looca.getSistema().getSistemaOperacional();

                maquina.setFabricante(looca.getSistema().getFabricante());
                maquina.setSistemaOperacional(looca.getSistema().getSistemaOperacional());


                Integer conversor = 1000000000;
                Double memoriaTotal = looca.getMemoria().getTotal().doubleValue()/conversor;
                Double tamanhoTotal = looca.getGrupoDeDiscos().getVolumes().get(0).getTotal().doubleValue()/conversor;
                Double frequenciaProcessador = looca.getProcessador().getFrequencia().doubleValue()/conversor;
                String identificadorCpu = looca.getProcessador().getIdentificador();

                System.out.println(String.format("""
                        DADOS INICIAIS
                        
                        Id da máquina: %d
                        Sistema operacional: %s
                        
                        Tamanho do disco: %.2f GB
                        Memória total: %.2f GB
                        Frequência da CPU: %.2f GHz
                        identificador da CPU: %s
                        """, maquina.getIdMaquina(), maquina.getSistemaOperacional(), tamanhoTotal, memoriaTotal,frequenciaProcessador, identificadorCpu));


                //coleta de registros a cada 30 segundos
                TimerTask task = new TimerTask() {

                    public void run() {
                        if (verificacaoLogin) {
                            System.out.println("""
                                    -----------------------------------------------------------------------------------------------
                                    """);
                            System.out.println(String.format("""
                                    Registro: %s
                                    """, LocalDateTime.now()));
                            //disco - Espaço disponivel
                            System.out.println(String.format("""
                                    Espaço disponível de disco: %.2f GB
                                    """, looca.getGrupoDeDiscos().getVolumes().get(0).getDisponivel().doubleValue()/conversor));
                            //memoria - Memória em uso
                            System.out.println(String.format("""
                                    Memória em uso: %.2f GB
                                    """, looca.getMemoria().getEmUso().doubleValue()/conversor));
                            //cpu - Uso do processador
                            System.out.println(String.format("""
                                    Uso do processador: %.2f GHz
                                    """, looca.getProcessador().getUso().doubleValue()/conversor));
                            //Total de processos
                            System.out.println(String.format("""
                                    Número total de processos: %d
                                    """, looca.getGrupoDeProcessos().getTotalProcessos()));
                            // Temperatura da cpu
                            System.out.println(String.format("""
                                    Temperatura da CPU: %.2f °C
                                    """, looca.getTemperatura().getTemperatura().doubleValue()));

                            System.out.println(String.format("""
                                    Janelas visíveis:
                                    %s
                                    """, looca.getGrupoDeJanelas().getJanelasVisiveis()));

                            System.out.println(String.format("""
                                    Listagem de processos: 
                                    %s
                                    """, looca.getGrupoDeProcessos().getProcessos()));

                            System.out.println("Captura realizada.");
                            System.out.println("""
                                    -----------------------------------------------------------------------------------------------
                                    """);

                        }else{
                            timer.cancel();
                        }
                    }
                };

                long delay = 10000; // 30 segundos
                long period = 10000; // 30 segundos

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
