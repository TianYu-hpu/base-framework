package cn.com.emindsoft;

import com.zaxxer.hikari.HikariDataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.sql.Connection;
import java.sql.SQLException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BaseWebApplicationTests {

	@Autowired
	private HikariDataSource dataSource;


	@Test
	public void contextLoads() {
	}

	@Test
	public void testDataSource() throws SQLException {
		Connection connection = dataSource.getConnection();
	}



}
