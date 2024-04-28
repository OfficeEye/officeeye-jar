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
echo " ---------------------------------------------------"
sleep 2
echo "Estamos verificando se o Java já está instalado..."
java -version
sleep 2

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
    fi
fi

sleep 2
echo "Configuração realizada com sucesso!" 
sleep 2
echo "Ambiente pronto para rodar a aplicação."
echo "Gostaria de iniciar o monitoramento [s/n]?"

read run
if [ "$run" == "s" ]; 
    then
    echo "Iniciando a aplicação..."
    cd ..
    cd aplicacao-officeeye-aws/
    cd target/
    java -jar aplicacao-officeeye-aws-1.0-SNAPSHOT-jar-with-dependencies.jar
    else 
    echo "O monitoramento não foi iniciado!"
    exit 0

fi
