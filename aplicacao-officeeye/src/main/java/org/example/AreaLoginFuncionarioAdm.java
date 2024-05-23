package org.example;

import java.util.List;

public class AreaLoginFuncionarioAdm {

    public static void mostrarMensagemErroCredenciais(){
        System.out.println(String.format("""
                        ----------------------------------------------------------------
                                                 ERRO AO LOGAR!
                        ----------------------------------------------------------------
                        Email e/ou senha incorretos. Tente novamente...
                                       
                        """));
        System.exit(0);
    }

    public static void exibirAreaLogadaFuncionarioAdm(BdSqlServer sqlserver){
        FuncionarioAdministrador funcionarioAdmLogado = sqlserver.buscarFuncionarioAdmLogado();

        // Login
        System.out.println(String.format("""
                        ----------------------------------------------------------------
                              Bem vindo, %s! Confira os dados das máquinas abaixo
                        ----------------------------------------------------------------
                                                   
                                       
                        """, funcionarioAdmLogado.getNome()));


        List<FuncionarioGeral> statusFuncionarios = sqlserver.verificarStatusLoginFuncionarios(funcionarioAdmLogado.getFkEmpresa());
        List<Maquina> dadosMaquinas = sqlserver.buscarDadosDeMaquinas(funcionarioAdmLogado.getFkEmpresa());


        for (int i = 0; i < statusFuncionarios.size(); i++) {
            System.out.println(String.format("""
                        ----------------------------------------------------------------------------------------------------------------------
                        Email | Área | Id da máquina | Nome da maquina | Sistema operacional | Status de login do funcionário
                        ----------------------------------------------------------------------------------------------------------------------
                         %s | %s | %d | %s | %s | %s                 
                         
                        """, statusFuncionarios.get(i).getEmail(), statusFuncionarios.get(i).getArea(), dadosMaquinas.get(i).getIdMaquina(), dadosMaquinas.get(i).getNomeMaquina(), dadosMaquinas.get(i).getSistemaOperacional(), statusFuncionarios.get(i).getStatusLogin()));
        }
    }
}
