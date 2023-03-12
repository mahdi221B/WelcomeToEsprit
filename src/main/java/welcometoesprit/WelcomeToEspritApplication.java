package welcometoesprit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.context.annotation.aspectj.EnableSpringConfigured;
import org.springframework.context.event.EventListener;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import tn.esprit.spring.services.IServiceFilesStorage;
import javax.annotation.Resource;

@EnableSpringConfigured
@Configuration
@Slf4j
@EnableScheduling
@SpringBootApplication
@EntityScan(basePackages = {"tn.esprit.spring.entity"})
@ComponentScan(basePackages = {"tn.esprit.spring.controllers","tn.esprit.spring.services","tn.esprit.spring.configuration"})
@EnableJpaRepositories(basePackages = {"tn.esprit.spring.repositories"})
@EnableAspectJAutoProxy
@EnableCaching
public class WelcomeToEspritApplication implements CommandLineRunner  {
    @Resource
    IServiceFilesStorage iServiceFilesStorage;
    public static void main(String[] args)
    {
        SpringApplication.run(WelcomeToEspritApplication.class, args);
    }
    @Override
    public void run(String... args) throws Exception {
        iServiceFilesStorage.init();
    }
}