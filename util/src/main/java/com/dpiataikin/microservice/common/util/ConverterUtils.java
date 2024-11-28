package com.dpiataikin.microservice.common.util;

import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;
import java.util.function.Function;

public class ConverterUtils {
    private ConverterUtils() {
    }

    public static <T, R> Function<T, Mono<R>> convertToMono(Function<T, R> mapperFunction) {
        return entity -> Mono.just(mapperFunction.apply(entity));
    }

    public static <T, R> Function<Mono<T>, Mono<R>> convertMonoToMono(Function<T, R> mapperFunction) {
        return mono -> mono.flatMap(item -> Mono.just(mapperFunction.apply(item)));
    }

    public static <T, R> Function<Flux<T>, Mono<R>> convertFluxToMono(Function<List<T>, R> mapperFunction) {
        return flux -> flux.collectList().flatMap(list -> Mono.just(mapperFunction.apply(list)));
    }
}
