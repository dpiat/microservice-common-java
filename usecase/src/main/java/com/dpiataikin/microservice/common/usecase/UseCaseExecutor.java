package com.dpiataikin.microservice.common.usecase;

import reactor.core.publisher.Mono;

import java.util.function.Function;

public interface UseCaseExecutor {
    <RequestDTO, ResponseDTO, Request, Response> ResponseDTO invoke(
            UseCase<Request, Response> useCase,
            RequestDTO RequestDTO,
            Function<RequestDTO, Request> requestConverter,
            Function<Response, ResponseDTO> responseConverter
    );

    default <RequestDTO, Request, ResponseDTO> ResponseDTO invoke(
            UseCase<Request, Void> useCase,
            RequestDTO RequestDTO,
            Function<RequestDTO, Request> requestConverter
    ) {
        return invoke(useCase, RequestDTO, requestConverter, response -> null);
    }

    default Mono<Void> invoke(UseCase<Void, Void> useCase) {
        return invoke(useCase, null, request -> null);
    }

    default <ResponseDTO, Response> ResponseDTO invoke(
            UseCase<Void, Response> useCase,
            Function<Response, ResponseDTO> responseConverter
    ) {
        return invoke(useCase, null, request -> null, responseConverter);
    }
}
