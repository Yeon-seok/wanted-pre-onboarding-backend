package com.example.wanted.service;

import com.example.wanted.entity.Company;
import com.example.wanted.entity.Member;
import com.example.wanted.repository.CompanyRepository;
import com.example.wanted.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class InitService {

    private final CompanyRepository companyRepository;
    private final MemberRepository memberRepository;

    @PostConstruct
    public void saveCompanyAndUser(){

        if(companyRepository.findAll().size() == 0 ){
            companyRepository.save(Company.builder()
                .id(UUID.randomUUID().toString())
                    .id("3a81c9e4-de03-4484-85e3-3a7ed7d1e71f")
                    .companyId(1L)
                    .name("원티드")
                    .nation("대한민국")
                    .region("서울")
                    .build());

            companyRepository.save(Company.builder()
                    .id("e63cf969-823a-4428-9b68-290923c2106e")
                    .companyId(2L)
                    .name("네이버")
                    .nation("대한민국")
                    .region("판교")
                    .build());

//            memberRepository.save(Member.builder().id(UUID.randomUUID().toString()).build());
            memberRepository.save(Member.builder().id("3b055551-5f71-4edb-979b-5033d13261a8").build());
        }
    }
}
