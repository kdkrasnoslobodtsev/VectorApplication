package org.example;

import org.example.client.Client;
import org.example.client.Connection;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

@SpringBootApplication
public class Main implements CommandLineRunner {
    private final Client client;

    public Main(Client client) {
        this.client = client;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    @Override
    public void run(String... args) {
        client.start();
    }
}