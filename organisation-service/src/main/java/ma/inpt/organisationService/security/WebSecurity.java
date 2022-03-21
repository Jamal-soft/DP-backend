package ma.inpt.organisationService.security;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

@EnableWebSecurity
public class WebSecurity extends WebSecurityConfigurerAdapter {

    public WebSecurity() {

    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable().authorizeRequests()
                .antMatchers(SecurityConstants.H2_CONSOLE)
                .permitAll()
                .anyRequest().authenticated().and()
                //.addFilter(getAuthenticationFilter())
                .addFilter(new AuthorizationFilter(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
        ;

        http.headers().frameOptions().disable();


    }






    @Bean
    public CorsConfigurationSource corsConfigurationSource()
    {
        final CorsConfiguration configuration = new CorsConfiguration();

        configuration.setAllowedOrigins(Arrays.asList("*"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE","OPTIONS"));
        configuration.setAllowCredentials(false);
        configuration.setAllowedHeaders(Arrays.asList("*"));

        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);

        return source;
    }
}
