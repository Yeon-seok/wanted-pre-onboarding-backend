package com.example.wanted.dto.res;

import lombok.Builder;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data @Builder
public class RecuritDetailReq {

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

    //채용 내용
    private String content;

    //회사가 올린 다른 채용 공고
    private final List<Long> idList = new ArrayList<>();
}
