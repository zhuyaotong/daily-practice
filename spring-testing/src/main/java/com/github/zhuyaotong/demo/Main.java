package com.github.zhuyaotong.demo;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
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

class UserService {
//    //@Autowired /* Autowire默认按照类型(byType)装配 */
//    @Autowired(required = false)
//    /* @Autowire注解默认情况下要求依赖对象必须存在。如果不存在，则在注入的时候会抛出异常。如果允许依赖对象为null，需设置required属性为false。 */
//    @Qualifier("userService") /* 按照名称(byName)装配 */
//    private UserService userService;

//    @Autowired
//    @Qualifier(value = "userService")
//    //@Qualifier("userService")     //value属性可以省略不写
//    private UserService userService;

    //    @Resource(name = "userService")
    //@Resource(type="userService")
    @Resource
    private UserService userService;

}

@Configuration
class ValueClass implements InitializingBean {
    // 1.基本类型数据和String类型数据的变量注入数据
    @Value("tom")
    private String name;
    @Value("18")
    private Integer age;

	/*

		// 2.从properties配置文件中获取数据并设置到成员变量中
		// 2.1jdbcConfig.properties配置文件定义如下
		jdbc.driver = com.mysql.jdbc.Driver
		jdbc.url = jdbc:mysql://localhost:3306/eesy
		jdbc.username = root
		jdbc.password = root

	*/

    // 2.2获取数据如下
    @Value("${jdbc.driver}")
    private String driver;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    public static void main(String[] args) throws InterruptedException {
        AnnotationConfigApplicationContext ac
                = new AnnotationConfigApplicationContext(ValueClass.class);
        ac.getBean("valueClass");
    }

    @PostConstruct
    public void init() {
        System.out.println("初始化方法执行");
    }

    @PreDestroy
    public void destroy() {
        System.out.println("销毁方法执行");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("afterPropertiesSet...");
    }
}

@Configuration
class MyConfiguration {
    public MyConfiguration() {
        System.out.println("构造方法被调用");
    }

    @PostConstruct
    private void init() {
        System.out.println("PostConstruct注解方法被调用");
    }

    @PreDestroy
    private void shutdown() {
        System.out.println("PreDestroy注解方法被调用");
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(MyConfiguration.class);
    }
}
