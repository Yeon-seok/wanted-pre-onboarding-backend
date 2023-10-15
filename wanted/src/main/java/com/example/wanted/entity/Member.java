package com.example.wanted.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity @Builder @Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "MEMBER")
public class Member {

    //지원자의 UUID
    @Id
    @Column(name = "MEMBER_UUID")
    private String id;

    @OneToMany(mappedBy = "member")
    private final List<MemberToRecruitment> memberToRecruitmentList = new ArrayList<>();




}
