package com.dpiataikin.microservice.common.event.starter.model;

public interface DomainEventType {

    String getName();

    Class<?> getType();
}
