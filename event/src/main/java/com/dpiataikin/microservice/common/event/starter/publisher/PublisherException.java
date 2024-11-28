package com.dpiataikin.microservice.common.event.starter.publisher;

public class PublisherException extends RuntimeException {

    public PublisherException(String msg) {
        super(msg);
    }

    public PublisherException(Throwable throwable) {
        super(throwable);
    }
}
