package com.example.wanted.repository;

import com.example.wanted.entity.MemberToRecruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberToRecruitmentRepository extends JpaRepository<MemberToRecruitment, Long> {
}
