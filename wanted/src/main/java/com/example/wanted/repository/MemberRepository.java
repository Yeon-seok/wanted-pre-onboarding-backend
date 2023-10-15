package com.example.wanted.repository;

import com.example.wanted.entity.Company;
import com.example.wanted.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, String> {
}
