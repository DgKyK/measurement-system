package com.alex.keeper;

import com.alex.keeper.domain.Measurement;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.data.mongodb.core.CollectionOptions;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@EnableEurekaClient
@SpringBootApplication
@EnableReactiveMongoRepositories
public class KeeperApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(KeeperApplication.class, args);

        // Explicit creation of capped collection, because spring haven't any options for specifying collection type
        ReactiveMongoTemplate mongoTemplate = context.getBean(ReactiveMongoTemplate.class);
        mongoTemplate.createCollection(Measurement.class.getSimpleName().toLowerCase(), CollectionOptions.empty().capped().size(5242880).maxDocuments(500)).subscribe();

    }

}
