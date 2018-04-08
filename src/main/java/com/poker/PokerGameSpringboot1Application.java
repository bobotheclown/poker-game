package com.poker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@EnableJpaRepositories
public class PokerGameSpringboot1Application {

	public static void main(String[] args) {
		SpringApplication.run(PokerGameSpringboot1Application.class, args);
	}
}
