package org.freeride.shootbug;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@EnableAsync
@EnableWebSecurity
@SpringBootApplication
public class ShootBugApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ShootBugApiApplication.class, args);
    }

}
