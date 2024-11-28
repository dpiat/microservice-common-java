package com.dpiataikin.microservice.common.usecase;

public interface UseCase<Request, Response> {
    Response execute(Request request);
}
