package com.mloine.auth.auths;

import com.mloine.auth.auths.utils.SpringContext;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(SpringContext.class)
public class AuthsApplication {

	public static void main(String[] args) {
		SpringApplication.run(AuthsApplication.class, args);
	}

}
