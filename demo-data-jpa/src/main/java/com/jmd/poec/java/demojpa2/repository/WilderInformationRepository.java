package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.Skill;
import com.jmd.poec.java.demojpa2.domain.entity.WilderInformation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface WilderInformationRepository extends JpaRepository<WilderInformation,Long> {

    Optional<WilderInformation> findFirstByContentAndWilder_Id(String content, Long wilder_id);

}
