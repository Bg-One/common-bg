package com.example.dbtoword;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * @author liuzhaobo
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class DbToWordApplication {

	public static void main(String[] args) {
		SpringApplication.run(DbToWordApplication.class, args);
	}

}
