package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.Skill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillRepository extends JpaRepository<Skill,Long> {


}
