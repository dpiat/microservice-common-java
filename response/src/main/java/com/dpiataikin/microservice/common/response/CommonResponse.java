package com.dpiataikin.microservice.common.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class CommonResponse<T> implements Serializable {
    @Builder.Default
    private Integer status = CommonStatus.OK.getStatus();
    private String message;
    private T payload;
}
