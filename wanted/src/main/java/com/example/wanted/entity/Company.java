package com.example.wanted.entity;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "COMPANY")
public class Company {
    
    //회사의 UUID
    @Id
    @Column(name = "COMPANY_UUID")
    private String id;
    
    //회사 ID
    @Column(name = "COMPANY_ID", updatable = false, unique = true)
    private Long companyId;
    
    //회사 이름
    @Column(name = "NAME", updatable = false)
    private String name;
    
    //회사 국적
    @Column(name = "NATION")
    private String nation;
    
    //회사 지역
    @Column(name = "REGION")
    private String region;
}
