package webprog.eksamensoving;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;

@SpringBootApplication (exclude={SecurityAutoConfiguration.class})
public class EksamensforberedelseApplication {

    public static void main(String[] args) {
        SpringApplication.run(EksamensforberedelseApplication.class, args);
    }

}
