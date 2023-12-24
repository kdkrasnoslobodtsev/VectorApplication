package org.example;

import org.example.server.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;


@SpringBootApplication
public class Main implements CommandLineRunner {
    private final Server server;
    private static ApplicationContext applicationContext;

    public Main(Server server) {
        this.server = server;
    }

    public static void main(String[] args) {
        applicationContext = SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        server.start();
    }

}