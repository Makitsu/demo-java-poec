package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.WilderInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WilderInformationRepository extends JpaRepository<WilderInformation,Long> {

    Optional<WilderInformation> findFirstByContentAndWilder_Id(String content, Long wilder_id);

}
