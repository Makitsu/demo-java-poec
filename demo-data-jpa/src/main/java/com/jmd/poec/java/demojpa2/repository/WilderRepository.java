package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WilderRepository extends JpaRepository<Wilder,Long> {

    List<Wilder> findAllByEmail(String email);

    @Query("select wilder from Wilder wilder where wilder.email = ?1 order by wilder.category asc")
    List<Wilder> queryFromCustom(String email);

    @Query(value = "select * from wilders as s where s.wilder_email = ?1 order by s.category asc",nativeQuery = true)
    List<Wilder> queryFromCustomSQL(String email);

    List<Wilder> findAllByEmailAndCategoryAndIdOrderByCategory(String email,
                                                               String category,
                                                               Long id);

    Optional<Wilder> findFirstByCategoryStartingWith(String content);




}
