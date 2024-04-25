package org.soneech.truereview;

import org.soneech.truereview.configuration.JwtConfig;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({JwtConfig.class})
public class TrueReviewApplication {

    public static void main(String[] args) {
        SpringApplication.run(TrueReviewApplication.class, args);
    }
}
