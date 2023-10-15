package com.example.wanted.dto.req;

import lombok.Data;

@Data
public class ApplayReq {

    //채용공고 id
    private Long recuritmentId;

    //유저의 uuid
    private String uuid;
}
