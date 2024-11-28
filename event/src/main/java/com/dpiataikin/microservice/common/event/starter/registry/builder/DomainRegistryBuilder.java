package com.dpiataikin.microservice.common.event.starter.registry.builder;


import com.dpiataikin.microservice.common.event.starter.DomainRelation;
import com.dpiataikin.microservice.common.event.starter.model.DomainEventType;
import com.dpiataikin.microservice.common.event.starter.registry.DomainRegistry;

import java.util.Set;

public class DomainRegistryBuilder {

    private final DomainRegistry domainRegistry = new DomainRegistry();

    public DomainRegistryBuilder register(String domain, DomainEventType[] eventTypes, Set<DomainRelation> relation) {
        domainRegistry.register(domain, Set.of(eventTypes), relation);
        return this;
    }

    public DomainRegistryBuilder register(String domain, Set<DomainEventType> eventTypes, Set<DomainRelation> relation) {
        domainRegistry.register(domain, eventTypes, relation);
        return this;
    }

    public DomainRegistry build() {
        return domainRegistry;
    }
}
