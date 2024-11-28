package com.dpiataikin.microservice.common.response;

import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

@SuperBuilder
@NoArgsConstructor
public class VoidResponse extends CommonResponse<Void> implements Serializable {
}
