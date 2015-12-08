package user_service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

import edu.cmu.ini.ericsson.practicum.models.userService.User;
import user_service.domain.UserRepository;

@EnableEurekaClient
@SpringBootApplication
public class UserServiceApplication implements CommandLineRunner {

	@Autowired
	private UserRepository repository;
	
    public static void main(String[] args) {
        SpringApplication.run(UserServiceApplication.class, args);
    }

	@Override
	public void run(String... arg0) throws Exception {
		repository.deleteAll();
		repository.save(new User("1", "6", "M", "Doctor", "15213", "1qaz2wsx", "John"));
		System.out.println("findAll():");
		System.out.println("-------------------------------");
		for (User movie : repository.findAll()) {
			System.out.println(movie);
		}
		System.out.println("--------------------------------");
		System.out.println("findByName");
		System.out.println("-------------------------------");
		System.out.println(repository.findByName("2"));
		System.out.println("--------------------------------");
	}
}
