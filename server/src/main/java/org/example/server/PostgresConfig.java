package org.example.server;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PostgresConfig {
    @Bean
    public VectorCollection postgresConnection() {
        return new VectorCollection();
    }
}
