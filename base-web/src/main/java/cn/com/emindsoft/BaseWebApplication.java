package cn.com.emindsoft;

import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @author tianyu
 * @date 2019-05-14
 * @description base web application
 */
@Slf4j
@Configuration
@ComponentScan(basePackages = "cn.com.emindsoft")
@MapperScan(basePackages = "cn.com.emindsoft.mapper")
@EnableTransactionManagement
@SpringBootApplication
public class BaseWebApplication {

	@Autowired
	private HikariDataSource dataSource;

	@Value("${spring.datasource.url}")
	private String jdbcUrl;

	@Value("${spring.datasource.username}")
	private String username;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.hikari.maximum-pool-size}")
	private int maxPoolSize;

	public static void main(String[] args) {
		SpringApplication.run(BaseWebApplication.class, args);
	}

	@Bean
	public HikariDataSource dataSource() {
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(jdbcUrl);
		hikariDataSource.setUsername(username);
		hikariDataSource.setPassword(password);
		hikariDataSource.setMaximumPoolSize(maxPoolSize);
		log.info("url:{}", jdbcUrl);
		return hikariDataSource;
	}

	@Bean
	public DataSourceTransactionManager txManager() {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() {
		SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
		bean.setDataSource(dataSource);

		try {
			return bean.getObject();
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
