package com.dpiataikin.microservice.common.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PagedResponse<T> implements Serializable {
    private List<T> content;
    private Integer page;
    private Integer size;
    private Long totalElements;
    private Integer totalPages;
    private Boolean isLast;

    public PagedResponse(List<T> content, Integer page, Integer size, long total) {
        this.content = content;
        this.page = page;
        this.size = size;
        this.totalElements = total;
        this.totalPages = this.size == 0 ? 1 : (int) Math.ceil((double) totalElements / (double) getSize());
        this.isLast = this.page + 1 >= this.totalPages;
    }
}
