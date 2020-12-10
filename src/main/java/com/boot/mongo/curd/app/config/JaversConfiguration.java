package com.boot.mongo.curd.app.config;

import org.javers.spring.auditable.AuthorProvider;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
@Component
public class JaversConfiguration {
	private static final String AUTHOR = "author";

	@Bean
	public AuthorProvider provideJaversAuthor() {
		return new SimpleAuthorProvider();
	}

	private static class SimpleAuthorProvider implements AuthorProvider {

		@Override
		public String provide() {
			return AUTHOR;
		}

	}

}
