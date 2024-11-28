package com.dpiataikin.microservice.common.event.starter.publisher;


import com.dpiataikin.microservice.common.event.starter.model.DomainEvent;

import java.util.List;

public interface EventPublisher {

    <T extends DomainEvent> void publish(String domain, List<T> event);
}
