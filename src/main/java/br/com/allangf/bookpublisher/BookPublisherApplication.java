package br.com.allangf.bookpublisher;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaRepositories
@SpringBootApplication
public class BookPublisherApplication {

    public static void main(String[] args) {
        SpringApplication.run(BookPublisherApplication.class, args);
    }

}
