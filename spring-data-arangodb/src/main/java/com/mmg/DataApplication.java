package com.mmg;

import com.mmg.runner.CrudRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class DataApplication {
    public static void main(final String... args) {
        Class<?>[] runner = new Class<?>[]{CrudRunner.class};
        System.exit(SpringApplication.exit(SpringApplication.run(runner, args)));
    }
}
