package ma.inpt;

import ma.inpt.security.AppProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class DonationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(DonationServiceApplication.class, args);
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
