package com.dpiataikin.microservice.common.event.starter.kafka;

import com.dpiataikin.microservice.common.event.starter.kafka.message.Message;
import com.dpiataikin.microservice.common.event.starter.kafka.message.MessageHeaders;
import com.dpiataikin.microservice.common.event.starter.publisher.EventPublisher;
import com.dpiataikin.microservice.common.event.starter.publisher.PublisherException;
import com.dpiataikin.microservice.common.event.starter.registry.DomainRegistry;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.dpiataikin.microservice.common.event.starter.model.DomainEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
public class KafkaEventPublisher implements EventPublisher {

    private final KafkaTemplate<String, Message> kafkaTemplate;
    private final ObjectMapper objectMapper;
    private final DomainRegistry domainRegistry;

    @Override
    public <T extends DomainEvent> void publish(String domain, List<T> events) {
        try {
            Objects.requireNonNull(domain);
            Objects.requireNonNull(events);
            if (!domainRegistry.isDomainRegistered(domain)) {
                throw new PublisherException("Domain '" + domain + "' is not registered. " +
                        "Consider adding the domain in DomainRegistry");
            }

            for (var event: events) {

                var eventType = domainRegistry.domainEvents(domain)
                        .eventWithType(event.getClass());

                final Message message = constructMessage(domain, eventType.getName(), event) ;

                kafkaTemplate.send(domain, message).exceptionally(err -> {
                    log.error("Failed to send a message with id " + message.getRequiredHeader(MessageHeaders.MESSAGE_ID) +
                            ". Cause: " + err.getMessage());
                    return null;
                });
            }

        } catch (Exception ex) {
            log.error("Error occurred while publishing events", ex);
            throw new PublisherException(ex);
        }
    }

    private <T extends DomainEvent> Message constructMessage(String domainType, String eventType, DomainEvent event) {
        Message message = new Message();
        message.setHeader(MessageHeaders.EVENT_TYPE, eventType);
        message.setHeader(MessageHeaders.DOMAIN_TYPE, domainType);
        message.setHeader(MessageHeaders.MESSAGE_ID, UUID.randomUUID().toString());
        message.setPayload(objectMapper.convertValue(event, JsonNode.class));

        return message;
    }
}
