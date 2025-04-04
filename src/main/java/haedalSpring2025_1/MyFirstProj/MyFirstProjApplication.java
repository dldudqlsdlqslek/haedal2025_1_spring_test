package haedalSpring2025_1.MyFirstProj;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

@SpringBootApplication
public class MyFirstProjApplication {


	@Autowired
	DataSource dataSource;
	public static void main(String[] args) {
		SpringApplication.run(MyFirstProjApplication.class, args);
	}

	@Bean
	CommandLineRunner runner() {
		return args -> {
			System.out.println("Current DB URL: " + dataSource.getConnection().getMetaData().getURL());
		};
	}

}
