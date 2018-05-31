package com.goosvandenbekerom.shutter.backend.config

import org.apache.kafka.clients.producer.ProducerConfig
import org.apache.kafka.common.serialization.ByteArraySerializer
import org.apache.kafka.common.serialization.StringSerializer
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.kafka.core.DefaultKafkaProducerFactory
import org.springframework.kafka.core.KafkaTemplate

@Configuration
@EnableKafka
class KafkaConfig {
    companion object {
        const val TOPIC_NEW_IMAGE = "new-image"
    }

    @Value("\${spring.kafka.bootstrap-servers}")
    private lateinit var bootstrapServers: String

    @Bean
    fun producerConfigs() = mapOf(
            ProducerConfig.BOOTSTRAP_SERVERS_CONFIG to  bootstrapServers,
            ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG to StringSerializer::class.java,
            ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG to ByteArraySerializer::class.java,
            ProducerConfig.MAX_BLOCK_MS_CONFIG to 5000,
            ProducerConfig.MAX_REQUEST_SIZE_CONFIG to 5000000
    )
    @Bean
    fun producerFactory() = DefaultKafkaProducerFactory<String, ByteArray>(producerConfigs())
    @Bean
    fun kafkaTemplate() = KafkaTemplate<String, ByteArray>(producerFactory())
}