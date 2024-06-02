package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class Conexao {
    private JdbcTemplate conexaoDoBanco;

    public Conexao(){
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/officeEye");
        dataSource.setUsername("root");
        dataSource.setPassword("30112004");

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }
    
    public JdbcTemplate getConexaoDoBanco(){
        return this.conexaoDoBanco;
    }
}
