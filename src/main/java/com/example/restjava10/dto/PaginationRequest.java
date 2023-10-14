package com.example.restjava10.dto;

import com.fasterxml.jackson.annotation.JsonAnyGetter;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Setter
@Getter
@Builder
@AllArgsConstructor
public class PaginationRequest {
    private List<StudentResponse> students;
    private int currentPage;
    private int pageSize;
}
