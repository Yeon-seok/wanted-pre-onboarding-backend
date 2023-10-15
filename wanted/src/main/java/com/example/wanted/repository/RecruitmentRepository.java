package com.example.wanted.repository;

import com.example.wanted.entity.Recruitment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface RecruitmentRepository extends JpaRepository<Recruitment, Long> {
    @Query("SELECT r.id FROM Recruitment r WHERE r.company.id = :id")
    List<Long> findByCompany(@Param("id") String id);

    @Query("SELECT r FROM Recruitment r WHERE r.company.name LIKE CONCAT('%', :word, '%') OR r.position LIKE CONCAT('%', :word, '%') OR r.techStack LIKE CONCAT('%', :word, '%')")
    List<Recruitment> findSearch(@Param("word") String word);
}
