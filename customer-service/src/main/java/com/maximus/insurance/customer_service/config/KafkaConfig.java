package com.maximus.insurance.customer_service.config;


import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.*;
import org.springframework.kafka.support.serializer.JsonDeserializer;
import org.springframework.kafka.support.serializer.JsonSerializer;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableKafka
//Configures Kafka Producer + Consumer.
public class KafkaConfig {
    // ---------------- PRODUCER ----------------
    @Bean
    public ProducerFactory<String, Object> producerFactory() {
        //A ProducerFactory is responsible for creating Kafka Producer instances.
        // You can think of it like: Factory that makes Producer objects using the given configuration.

        Map<String, Object> config = new HashMap<>();   // Create config Map : A map to store Kafka producer configuration properties.
        config.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");   // Add Kafka broker address
        /* This tells the Producer where Kafka is running.
            "localhost:9092" means: Broker 1 is on your local machine
                                    Running on port 9092
       If Kafka was running in AWS: ec2-xxx-xxx.compute.amazonaws.com:9092 */

        config.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Set key serializer
        // Kafka needs to convert the key into bytes --> Here key type is String --> So use StringSerializer.

        config.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, JsonSerializer.class); //Set value serializer
        // Value can be Object --> Spring Kafka’s JsonSerializer converts Java objects → JSON → bytes.

        return new DefaultKafkaProducerFactory<>(config); // Return ProducerFactory
        // Creates a producer factory using this config. --> Spring will use this factory whenever it needs a Kafka producer.
    }

    //KafkaTemplate Bean
    //KafkaTemplate is what you actually use to send messages.
    @Bean
    public KafkaTemplate<String, Object> kafkaTemplate() {
        return new KafkaTemplate<>(producerFactory());
    }

    // ---------------- CONSUMER ----------------
    @Bean
    public ConsumerFactory<String, Object> consumerFactory() {
        JsonDeserializer<Object> deserializer = new JsonDeserializer<>();
        deserializer.addTrustedPackages("*");

        Map<String, Object> config = new HashMap<>();
        config.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.GROUP_ID_CONFIG, "customer-service-group");
        config.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
        config.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, deserializer);

        return new DefaultKafkaConsumerFactory<>(config, new StringDeserializer(), deserializer);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, Object> kafkaListenerContainerFactory() {
        ConcurrentKafkaListenerContainerFactory<String, Object> factory =
                new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory());
        return factory;
    }
}


// @Configuration: “This class contains beans that should be loaded into the Spring container.”
// Means Spring will run this class at startup and create the defined beans (producerFactory, kafkaTemplate).

//@EnableKafka : Without this, Spring will not create Kafka listeners or configure Kafka-related components.
// It activates Kafka features such as: KafkaTemplate, @KafkaListener, producer/consumer factories