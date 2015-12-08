package eureka_trial;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEurekaServer
public class EurekaTrialApplication {

    public static void main(String[] args) {
        SpringApplication.run(EurekaTrialApplication.class, args);
    }
    
}
