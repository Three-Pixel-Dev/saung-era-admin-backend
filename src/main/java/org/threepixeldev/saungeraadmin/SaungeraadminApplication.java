package org.threepixeldev.saungeraadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.data.redis.RedisRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(exclude = {RedisRepositoriesAutoConfiguration.class})
@EnableJpaRepositories(basePackages = "org.threepixeldev.saungeraadmin.shared.data.repository.jpa")
public class SaungeraadminApplication {

	public static void main(String[] args) {
		SpringApplication.run(SaungeraadminApplication.class, args);
	}

}
