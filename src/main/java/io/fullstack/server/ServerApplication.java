package io.fullstack.server;

import io.fullstack.server.ServerStatusEnum.Status;
import io.fullstack.server.model.Server;
import io.fullstack.server.repository.ServerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(ServerApplication.class, args);
	}

//	@Bean
//	CommandLineRunner run(ServerRepository serverRepository) {
//		return args -> {
//			serverRepository.save(new Server(null,
//					"192.168.1.7",
//					"Ubuntu Linux",
//					"32 GB",
//					"Working Laptop",
//					"http://localhost:8080/server/image/server1.png",
//					Status.SERVER_UP));
//
//			serverRepository.save(new Server(null,
//					"192.168.1.8",
//					"Non Server",
//					"16 GB",
//					"Imaginary Laptop",
//					"http://localhost:8080/server/image/server2.png",
//					Status.SERVER_DOWN));
//		};
//	}

}
