package com.example.wanted.dto.req;

import lombok.Data;

@Data
public class UpdateRecuritReq {

    //채용포지션
    private String position;

    //채용 보상금
    private Long money;

    //채용 내용
    private String content;

    //사용기술
    private String techStack;
}
