package org.example;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.jdbc.core.JdbcTemplate;

public class ConexaoSql {
    private JdbcTemplate conexaoDoBanco;

    public ConexaoSql() {
        BasicDataSource dataSource = new BasicDataSource();
        dataSource.setDriverClassName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        dataSource.setUrl("jdbc:sqlserver://100.28.96.116:1433;database=officeEye;trustServerCertificate=true");
        dataSource.setUsername("officeeye");
        dataSource.setPassword("123");

        conexaoDoBanco = new JdbcTemplate(dataSource);
    }

    public JdbcTemplate getConexaoDoBanco(){
        return this.conexaoDoBanco;
    }
}
