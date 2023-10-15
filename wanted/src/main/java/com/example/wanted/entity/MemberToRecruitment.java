package com.example.wanted.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity @Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER_TO_RECRUITMENT",
        uniqueConstraints = @UniqueConstraint(columnNames = {"MEMBER_UUID", "RECRUITMENT_ID"}))
public class MemberToRecruitment {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "MEMBER_TO_RECRUITMENT_ID")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MEMBER_UUID")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RECRUITMENT_ID")
    private Recruitment recruitment;
}
