package hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Created by zjluoyue on 2016/8/3.
 */
@SpringBootApplication
public class Application {

    /**
     * 全局的跨域访问
     * @return
     */
    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurerAdapter() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/greeting").allowedOrigins("http://localhost:9000");
            }
        };
    }

    public static void main(String[] args) {

        SpringApplication.run(Application.class, args);
    }
}
