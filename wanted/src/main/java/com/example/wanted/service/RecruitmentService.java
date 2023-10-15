package com.example.wanted.service;

import com.example.wanted.dto.req.ApplayReq;
import com.example.wanted.dto.req.RegistRecuritReq;
import com.example.wanted.dto.req.UpdateRecuritReq;
import com.example.wanted.dto.res.RecuritDetailReq;
import com.example.wanted.dto.res.RecuritReq;
import com.example.wanted.entity.Company;
import com.example.wanted.entity.Member;
import com.example.wanted.entity.MemberToRecruitment;
import com.example.wanted.entity.Recruitment;
import com.example.wanted.repository.CompanyRepository;
import com.example.wanted.repository.MemberRepository;
import com.example.wanted.repository.MemberToRecruitmentRepository;
import com.example.wanted.repository.RecruitmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class RecruitmentService {

    private final MemberRepository memberRepository;
    private final RecruitmentRepository recruitmentRepository;
    private final CompanyRepository companyRepository;
    private final MemberToRecruitmentRepository       memberToRecruitmentRepository;

    //채용공고 등록
    public void registRecruitment(RegistRecuritReq recuritReq){
        recruitmentRepository.save(Recruitment.builder()
                .position(recuritReq.getPosition())
                .money(recuritReq.getMoney())
                .content(recuritReq.getContent())
                .techStack(recuritReq.getTechStack())
                .company(companyRepository.findByCompanyId(recuritReq.getId()).orElseThrow(
                        () -> new RuntimeException("해당하는 회사는 등록되지 않은 회사입니다.")))
                .build());
    }

    //채용공고 수정
    public void updateRecruitment(Long recruitmentId, UpdateRecuritReq updateRecuritReq){
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
                () -> new RuntimeException("해당하는 채용 공고는 없는 공고입니다."));
        recruitment.updateRecruitment(updateRecuritReq);
    }

    //채용공고 삭제
    public void deleteRecruitment(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
                () -> new RuntimeException("해당하는 채용 공고는 없는 공고입니다."));
        recruitmentRepository.delete(recruitment);
    }
    
    //전체 채용 목록
    @Transactional(readOnly = true)
    public List<RecuritReq> listRecruitment() {
        List<Recruitment> recruitmentList = recruitmentRepository.findAll();
        if(recruitmentList.size() == 0){
            throw new RuntimeException("채용 목록이 없습니다.");
        }
        List<RecuritReq> recuritReqs = new ArrayList<>();
        for(Recruitment recruitment : recruitmentList){
            Company company = recruitment.getCompany();
            recuritReqs.add(RecuritReq.builder()
                    .id(recruitment.getId())
                    .CompanyName(company.getName())
                    .CompanyNation(company.getNation())
                    .CompanyRegin(company.getRegion())
                    .position(recruitment.getPosition())
                    .money(recruitment.getMoney())
                    .techStack(recruitment.getTechStack())
                    .build());
        }
        return recuritReqs;
    }
    
    //채용 상세 페이지 가져오기
    @Transactional(readOnly = true)
    public RecuritDetailReq getRecruitment(Long recruitmentId) {
        Recruitment recruitment = recruitmentRepository.findById(recruitmentId).orElseThrow(
                () -> new RuntimeException("해당하는 채용 공고는 없는 공고입니다."));
        Company company = recruitment.getCompany();
        List<Long> recruitmentList = recruitmentRepository.findByCompany(company.getId());
        RecuritDetailReq req = RecuritDetailReq.builder()
                .id(recruitment.getId())
                .CompanyName(company.getName())
                .CompanyNation(company.getNation())
                .CompanyRegin(company.getRegion())
                .position(recruitment.getPosition())
                .money(recruitment.getMoney())
                .techStack(recruitment.getTechStack())
                .content(recruitment.getContent())
                .build();
        for(Long id : recruitmentList){
            req.getIdList().add(id);
        }
        return req;
    }
    
    //채용 공고 검색
    @Transactional(readOnly = true)

    public List<RecuritReq> searchRecruitment(String word) {
        List<Recruitment> search = recruitmentRepository.findSearch(word);
        if(search.size() == 0){
            throw new RuntimeException("검색 목록이 없습니다.");
        }
        List<RecuritReq> recuritReqs = new ArrayList<>();
        for(Recruitment recruitment : search){
            Company company = recruitment.getCompany();
            recuritReqs.add(RecuritReq.builder()
                    .id(recruitment.getId())
                    .CompanyName(company.getName())
                    .CompanyNation(company.getNation())
                    .CompanyRegin(company.getRegion())
                    .position(recruitment.getPosition())
                    .money(recruitment.getMoney())
                    .techStack(recruitment.getTechStack())
                    .build());
        }
        return recuritReqs;
    }

    //하나의 회사에 지원
    public void apply(ApplayReq applayReq) {
        Member member = memberRepository.findById(applayReq.getUuid()).orElseThrow(
                () -> new RuntimeException("회원이 존재하지 않습니다."));
        Recruitment recruitment = recruitmentRepository.findById(applayReq.getRecuritmentId()).orElseThrow(
                () -> new RuntimeException("채용공고가 존재하지 않습니다.")
        );
        memberToRecruitmentRepository.save(MemberToRecruitment.builder()
                .member(member)
                .recruitment(recruitment)
                .build()
        );
    }
}
