package com.example.reservas;

//import org.springframework.amqp.rabbit.connection.ConnectionFactory;
//import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
public class ReservasApplication extends SpringBootServletInitializer {

	public static void main(String[] args) {
		SpringApplication.run(ReservasApplication.class, args);
	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
		return application.sources(ReservasApplication.class);
	}
//
//	@Bean
//	public ApplicationRunner runner(ConnectionFactory connectionFactory) {
//		return ApplicationRunner -> {
//			var connection = connectionFactory.createConnection();
//			connection.close();
//		};
//	}
}
