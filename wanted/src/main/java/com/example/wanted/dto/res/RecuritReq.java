package com.example.wanted.dto.res;

import lombok.Builder;
import lombok.Data;

@Data @Builder
public class RecuritReq {

    //채용공고id
    private Long id;

    //회사명
    private String CompanyName;

    //회사 국가
    private String CompanyNation;

    //회사 지역
    private String CompanyRegin;

    //채용포지션
    private String position;

    //채용 보상금
    private Long money;

    //사용기술
    private String techStack;
}
