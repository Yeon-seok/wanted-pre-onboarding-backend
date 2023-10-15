package com.example.wanted.entity;

import com.example.wanted.dto.req.UpdateRecuritReq;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.hibernate.sql.Update;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "RECRUITMENT")
public class Recruitment {

    //채용공고 ID
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "RECRUITMENT_ID")
    private Long id;

    //채용포지션
    @Column(name = "POSITION")
    private String position;
    
    //채용 보상금
    @Column(name = "MONEY")
    private Long money;
    
    //채용 내용
    @Column(name = "CONTENT")
    private String content;
    
    //사용기술
    @Column(name = "TECH_STACK")
    private String techStack;
    
    //채용 회사
    @ManyToOne
    @JoinColumn(name = "COMPANY_UUID")
    private Company company;

    @OneToMany(mappedBy = "recruitment")
    private final List<MemberToRecruitment> memberToRecruitmentList = new ArrayList<>();

    public void updateRecruitment(UpdateRecuritReq updateRecuritReq){
        this.position = updateRecuritReq.getPosition();
        this.money = updateRecuritReq.getMoney();;
        this.content = updateRecuritReq.getContent();
        this.techStack = updateRecuritReq.getTechStack();
    }
}
