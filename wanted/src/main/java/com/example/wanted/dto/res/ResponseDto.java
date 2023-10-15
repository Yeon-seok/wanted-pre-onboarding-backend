package com.example.wanted.dto.res;


import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data @Builder
@NoArgsConstructor
public class ResponseDto<T> {

    private String code;
    private String msg;
    private T data;

    public ResponseDto(String code, String msg, T data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }
}
