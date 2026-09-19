package com.clau.payment.producer.config.kafka

import org.apache.avro.specific.SpecificRecord
import org.apache.kafka.clients.producer.ProducerConfig
import org.springframework.boot.autoconfigure.condition.ConditionalOnBooleanProperty
import org.springframework.boot.kafka.autoconfigure.KafkaConnectionDetails
import org.springframework.boot.kafka.autoconfigure.KafkaProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.kafka.core.ProducerFactory

@Configuration
@ConditionalOnBooleanProperty("kafka.producer.enabled")
class KafkaProducerConfig(
    private val kafkaProperties: KafkaProperties,
    private val connectionDetails: KafkaConnectionDetails,
) {

    @Bean
    fun producerFactory(): ProducerFactory<String, SpecificRecord> {
        val props = kafkaProperties.buildProducerProperties()
        // Endereço vem do KafkaConnectionDetails (respeita Testcontainers @ServiceConnection,
        // env vars e yaml de forma uniforme) — NÃO do yaml lido diretamente.
        props[ProducerConfig.BOOTSTRAP_SERVERS_CONFIG] = connectionDetails.bootstrapServers
        return DefaultKafkaProducerFactory(props)
    }

    @Bean
    fun kafkaTemplate(
        producerFactory: ProducerFactory<String, SpecificRecord>
    ): KafkaTemplate<String, SpecificRecord> = KafkaTemplate(producerFactory)
}
