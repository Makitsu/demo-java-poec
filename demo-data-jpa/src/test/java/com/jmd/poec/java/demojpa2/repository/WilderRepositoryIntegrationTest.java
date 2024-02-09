package com.jmd.poec.java.demojpa2.repository;

import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;

@DataJpaTest
class WilderRepositoryIntegrationTest {

    @Autowired
    WilderRepository wilderRepository;

    @Test
    void findAllByEmail() {
        //GIVEN
        //WHEN
        List<Wilder> allByEmail = wilderRepository.findAllByEmail("test@gmail.com");
        //THEN
        Assertions.assertNotNull(allByEmail);
        Assertions.assertEquals(1,allByEmail.size());
        Assertions.assertEquals("TEST",allByEmail.getFirst().getName());
        Assertions.assertEquals("TEST",allByEmail.getFirst().getCategory());
        Assertions.assertEquals("test@gmail.com",allByEmail.getFirst().getEmail());
    }
}