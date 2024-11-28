package com.dpiataikin.microservice.common.usecase;

import java.util.function.Function;

public class UseCaseExecutorImpl implements UseCaseExecutor {
    @Override
    public <RequestDTO, ResponseDTO, Request, Response> ResponseDTO invoke(
            UseCase<Request, Response> useCase,
            RequestDTO requestDTO,
            Function<RequestDTO, Request> requestConverter,
            Function<Response, ResponseDTO> responseConverter
    ) {
        Request request = requestConverter.apply(requestDTO);
        Response response = useCase.execute(request);
        return responseConverter.apply(response);
    }
}
