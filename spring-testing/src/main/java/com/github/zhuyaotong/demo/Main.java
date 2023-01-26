package com.github.zhuyaotong.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.sql.DataSource;

@Component
@Controller
@Service
@Repository
@Resource
//@Bean
public class Main {
    public static void main(String[] args) {

    }
}

@Configuration
class JdbcConfig {
    /**
     * 用于创建QueryRunner对象
     *
     * @param dataSource
     * @return
     */
    @Bean(value = "queryRunner")
    public QueryRunner createQueryRunner(DataSource dataSource) {
        return new QueryRunner(dataSource);
    }

    /**
     * 创建数据源对象
     *
     * @return
     */
    @Bean(value = "dataSource")
    public DataSource createDataSource() {
        try {
            ComboPooledDataSource comboPooledDataSource = new ComboPooledDataSource();
            comboPooledDataSource.setDriverClass("com.mysql.jdbc.Driver");
            comboPooledDataSource.setJdbcUrl("jdbc:mysql://localhost:3306/mydatabase");
            comboPooledDataSource.setUser("root");
            comboPooledDataSource.setPassword("root");
            return comboPooledDataSource;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    private static class QueryRunner {
        private DataSource dataSource;

        public QueryRunner(DataSource dataSource) {
            this.dataSource = dataSource;
        }
    }
}