package welcometoesprit;


import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.entity.Interview;
import tn.esprit.spring.services.EmailService;

@EnableSpringConfigured
@Configuration
@Slf4j
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {"tn.esprit.spring.entity"})
@ComponentScan(basePackages = {"tn.esprit.spring.controllers","tn.esprit.spring.services","tn.esprit.spring.configuration"})
@EnableJpaRepositories(basePackages = {"tn.esprit.spring.repositories"})
@EnableAspectJAutoProxy
public class WelcomeToEspritApplication {




    public static void main(String[] args)
    {
        SpringApplication.run(WelcomeToEspritApplication.class, args);
    }

}
