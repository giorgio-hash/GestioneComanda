package config;

import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.test.EmbeddedKafkaBroker;
import org.springframework.kafka.test.EmbeddedKafkaZKBroker;

@TestConfiguration
public class EmbeddedKafkaConfig {

    @Bean
    public EmbeddedKafkaBroker embeddedKafkaBroker() {
        EmbeddedKafkaBroker embeddedKafkaBroker = new EmbeddedKafkaZKBroker(1, true);
        return embeddedKafkaBroker;
    }

}
