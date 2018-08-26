package ru.job4j.beans;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author Yury Matskevich
 */
@Configuration
public class AppContext {

	@Bean
	public Third third() {
		return new Third();
	}
}
