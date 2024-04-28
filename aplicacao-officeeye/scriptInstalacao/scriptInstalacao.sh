#!/bin/bash
echo " _____   __   __  _              _____"
echo "|  _  | / _| / _|(_)            |  ___|"
echo "| | | || |_ | |_  _   ___   ___ | |__   _   _   ___"
echo '| | | ||  _||  _|| | / __| / _ \|  __| | | | | / _ \ '
echo "\ \_/ /| |  | |  | || (__ |  __/| |___ | |_| ||  __/"
echo " \___/ |_|  |_|  |_| \___| \___|\____/  \__, | \___|"
echo "                                         __/ |"
echo "                                        |___/"
echo ""

echo "Estamos verificando se o Java já está instalado..."
java -version

if [ $? = 0 ]; then 
    echo "O Java já está instalado!!!"  
else
    echo "O Java ainda não está instalado"
    echo "Gostaria de instalar o Java [s/n]?"
    
    read get 
    if [ "$get" == "s" ]; then
        echo 'Atualizando pacotes...'
        sudo apt update
        echo "Instalando atualizações"
        sudo apt upgrade
        echo "Instalando versão atual do Java"
        sudo apt install openjdk-17-jre -y 
    fi
fi

echo "Estamos verificando se o MySQL está instalado..."
mysql -V

if [ $? = 0 ]; then 
    echo "O MySQL já está instalado!!!"  
else
    echo "O MySQL ainda não está instalado"
    echo "Gostaria de instalar o MySQL [s/n]?"

    read getMysql
    if [ "$getMysql" == "s" ]; then
        echo 'Atualizando pacotes...'
        sudo apt update 
        echo "Instalando atualizações"
        sudo apt upgrade 
        echo "Instalando MySQL"
        sudo apt install mysql-server -y
        echo "Configurando segurança para o banco"
        sudo mysql_secure_installation <<EOF
y
SUA_SENHA
SUA_SENHA
y
y
y
y
EOF
        echo "Verificando se o MySQL está em execução"
        sudo systemctl status mysql
    fi
fi

# A partir daqui, a execução do MySQL e a criação do banco de dados e tabelas estão corretas

echo "Login para acessar o banco de dados"
sudo mysql -u root -p officeeye123 <<EOF

CREATE DATABASE IF NOT EXISTS officeEye;

USE officeEye;


CREATE DATABASE officeEye;

USE officeEye;
CREATE TABLE empresa (
  idEmpresa INT NOT NULL auto_increment,
  nomeFantasia VARCHAR(45) NULL,
  razaoSocial VARCHAR(45) NULL,
  cnpj VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  senha VARCHAR(45) NULL,
  PRIMARY KEY (idempresa));

CREATE TABLE usuario (
  idUsuario INT NOT NULL auto_increment,
  nome VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  tipo VARCHAR(45) NULL,
  cpf VARCHAR(12) NULL,
  senha VARCHAR(45) NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idUsuario, fkEmpresa),
  CONSTRAINT fk_usuario_empresa
    FOREIGN KEY (fkEmpresa)
    REFERENCES empresa (idEmpresa));

CREATE TABLE funcionario (
  idFuncionario INT NOT NULL auto_increment,
  nome VARCHAR(45) NULL,
  cpf VARCHAR(12) NULL,
  area VARCHAR(45) NULL,
  email VARCHAR(45) NULL,
  senha VARCHAR(45) NULL, 
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idFuncionario, fkEmpresa),
  CONSTRAINT fk_funcionario_empresa1
    FOREIGN KEY (fkEmpresa)
    REFERENCES empresa (idEmpresa));

CREATE TABLE maquina (
  idmaquina INT NOT NULL auto_increment,
  modelo VARCHAR(45) NULL,
  fabricante VARCHAR(45) NULL,
  nomeMaquina VARCHAR(45) NULL,
  sistemaOperacional VARCHAR(45) NULL,
  fkFuncionario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idmaquina, fkFuncionario, fkEmpresa),
  CONSTRAINT fk_maquina_funcionario1
    FOREIGN KEY (fkFuncionario , fkEmpresa)
    REFERENCES funcionario (idFuncionario , fkEmpresa));

CREATE TABLE componente (
  idComponente INT NOT NULL auto_increment,
  nomeComponente VARCHAR(45) NULL,
  PRIMARY KEY (idComponente));

CREATE TABLE especificacaoComponentes (
  idEspecificacaoComponentes INT NOT NULL auto_increment,
  nomeEspecificacao VARCHAR(45) NULL,
  informacaoTotalEspecificacao DECIMAL(5,2) NULL,
  fkComponente INT NOT NULL,
  fkMaquina INT NOT NULL,
  fkFuncionario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idespecificacaoComponentes, fkComponente, fkMaquina, fkFuncionario, fkEmpresa),
  CONSTRAINT fk_especificacaoComponentes_componente1
    FOREIGN KEY (fkComponente)
    REFERENCES componente (idcomponente),
    CONSTRAINT fk_especificacaoComponentes_maquina1
    FOREIGN KEY (fkMaquina, fkFuncionario, fkEmpresa)
    REFERENCES maquina (idMaquina, fkFuncionario, fkEmpresa));

CREATE TABLE registrosEspecificacaoComponente (
  idRegistros INT NOT NULL auto_increment,
  dataHoraRegistro DATETIME NULL,
  registroNumero DECIMAL(5,2) NULL,
  registroTexto longtext NULL,
  tipoRegistro VARCHAR(45) NULL,
  fkEspecificacaoComponentes INT NOT NULL,
  fkComponente INT NOT NULL,
  fkMaquina INT NOT NULL,
  fkFuncionario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idRegistros, fkEspecificacaoComponentes, fkComponente, fkMaquina, fkFuncionario, fkEmpresa),
  CONSTRAINT fk_RegistrosMaquina_especificacaoComponentes1
    FOREIGN KEY (fkEspecificacaoComponentes , fkComponente , fkMaquina , fkFuncionario , fkEmpresa)
    REFERENCES especificacaoComponentes (idEspecificacaoComponentes , fkComponente , fkMaquina , fkFuncionario , fkEmpresa));
    
    
CREATE TABLE metricasEspecificacaoComponente (
  idMetricasEspecificacaoComponente INT NOT NULL auto_increment,
  porcentagemIdeal DECIMAL,
  porcentagemAlerta DECIMAL,
  porcentagemCritico DECIMAL,
  fkEspecificacaoComponentes INT NOT NULL,
  fkComponente INT NOT NULL,
  fkMaquina INT NOT NULL,
  fkFuncionario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idMetricasEspecificacaoComponente, fkEspecificacaoComponentes, fkComponente, fkMaquina, fkFuncionario, fkEmpresa),
  CONSTRAINT fk_Metricas_especificacaoComponentes1
    FOREIGN KEY (fkEspecificacaoComponentes , fkComponente , fkMaquina , fkFuncionario , fkEmpresa)
    REFERENCES especificacaoComponentes (idEspecificacaoComponentes , fkComponente , fkMaquina , fkFuncionario , fkEmpresa));
    

CREATE TABLE Chamados (
  idChamados INT NOT NULL auto_increment,
  dataAbertura DATETIME NULL,
  dataFechamento DATETIME NULL,
  status VARCHAR(45) NULL,
  nivelPrioridade VARCHAR(45),
  fkUsuario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  PRIMARY KEY (idChamados, fkUsuario, fkEmpresa),
  CONSTRAINT fk_Chamados_usuario1
    FOREIGN KEY (fkUsuario , fkEmpresa)
    REFERENCES usuario (idUsuario , fkEmpresa));

CREATE TABLE HistoricoChamados (
  idHistoricoChamados INT NOT NULL auto_increment,
  fkMaquina INT NOT NULL,
  fkChamados INT NOT NULL,
  fkUsuario INT NOT NULL,
  fkEmpresa INT NOT NULL,
  motivo VARCHAR(45) NULL,
  descricaoProblema VARCHAR(255) NULL,
  descricaoSolucao VARCHAR(255) NULL,
  PRIMARY KEY (idHistoricoChamados, fkMaquina, fkChamados, fkUsuario, fkEmpresa),
  CONSTRAINT fk_maquina_has_Chamados_maquina1
    FOREIGN KEY (fkMaquina)
    REFERENCES maquina (idmaquina),
    CONSTRAINT fk_maquina_has_Chamados_Chamados1
    FOREIGN KEY (fkChamados , fkUsuario , fkEmpresa)
    REFERENCES Chamados (idChamados , fkUsuario , fkEmpresa));
    

EOF

echo "Populando as tabelas e configurando o banco de dados..."
sudo mysql -u root -p officeeye123 <<EOF

use officeEye;

Insert into empresa(nomeFantasia, razaoSocial, cnpj, email, senha) values
('OfficeEye', 'officeEye', '123456789012345', 'office.eye@gmail.com', 'theoffice1');

select * from empresa;

insert into funcionario(nome, cpf, area, email, senha, fkEmpresa) values 
('Maria', '55998567803', 'Tecnologia - Dev', 'maria.veigas@gmail.com', 'm123', 1),
('Jorge', '55998567765', 'RH ', 'jorge.vv@gmail.com', '1009', 1),
('Felipe', '123456789012', 'Administração', 'felipe@gmail.com', 'f321', 1),
('Gabriela', '98765432112', 'Administração', 'gabi@gmail.com', 'g4321', 1);

select * from funcionario;

insert into usuario(nome, email, tipo, cpf, senha, fkEmpresa) values 
('Pedro', 'pedro.silva@gmail.com', 'Técnico de suporte', '12345678909', 'p321', 1);

select * from usuario;

insert into maquina(modelo, nomeMaquina, fkFuncionario, fkEmpresa) values 
('teste-modelo', 'maquina-maria', 1, 1),
('teste-modelo2', 'maquina-felipe', 3, 1),
('teste-modelo3', 'maquina-gabriela', 4, 1);

select * from maquina;

SELECT * FROM maquina WHERE fkFuncionario = 1 and fkEmpresa = 1;

-- trocar o nome da tabela para maíusculo
insert into Chamados(status, nivelPrioridade, fkUsuario, fkEmpresa) values 
('Fechado', 'Alta', 1, 1);

select * from Chamados;

select * from HistoricoChamados;

insert into componente(nomeComponente) values
('Disco'),
('Memória'),
('CPU');

select * from componente;

insert into especificacaoComponentes(nomeEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) values
('Tamanho total', 1, 1, 1, 1),
('Memória total', 2, 1, 1, 1),
('Frequência', 3, 1, 1, 1);

insert into especificacaoComponentes(nomeEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) values
('Tamanho total', 1, 2, 3, 1),
('Memória total', 2, 2, 3, 1),
('Frequência', 3, 2, 3, 1);

insert into especificacaoComponentes(nomeEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) values
('Tamanho total', 1, 3, 4, 1),
('Memória total', 2, 3, 4, 1),
('Frequência', 3, 3, 4, 1);

insert into especificacaoComponentes(nomeEspecificacao, fkComponente, fkMaquina, fkFuncionario, fkEmpresa) values
('Procesos em execução no momento', 3, 1, 1, 1);

select * from especificacaoComponentes;

select * from registrosEspecificacaoComponente;

EOF

echo "Configuração do banco realizada com sucesso!" 

echo "Ambiente pronto para rodar a aplicação."
echo "Gostaria de iniciar o monitoramento [s/n]?"

read run
if [ "$run" == "s" ]; 
    then
    echo "Iniciando a aplicação..."
    cd ..
    cd target/
    java -jar aplicacao-officeeye-1.0-SNAPSHOT-jar-with-dependencies.jar
    else 
    echo "Aplicação não está rodando"
    exit 0
fi
