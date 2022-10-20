package com.mmg.config;

import com.arangodb.ArangoDB;
import com.arangodb.springframework.annotation.EnableArangoRepositories;
import com.arangodb.springframework.config.ArangoConfiguration;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableArangoRepositories(basePackages = {"com.mmg"})
public class DataConfiguration implements ArangoConfiguration {

    @Override
    public ArangoDB.Builder arango() {
        return new ArangoDB
                .Builder()
                .host("localhost", 8529)
                .user("root")
                .password("openSesame");
    }

    @Override
    public String database() {
        return "wikimedia-db";
    }
}
