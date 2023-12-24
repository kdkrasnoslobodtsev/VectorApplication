package org.example.client;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ConnectionConfiguration {
    @Bean("standartConnection")
    public Connection standartConnection() {
        return new Connection();
    }
}
