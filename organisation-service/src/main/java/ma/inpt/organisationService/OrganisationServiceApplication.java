package ma.inpt.organisationService;

import ma.inpt.organisationService.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
//@EnableEurekaClient
public class OrganisationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(OrganisationServiceApplication.class, args);
    }

    @Bean
    public SpringApplicationContext springApplicationContext()
    {
        return new SpringApplicationContext();
    }

    @Bean(name="AppProperties")
    public AppProperties getAppProperties()
    {
        return new AppProperties();
    }
}
