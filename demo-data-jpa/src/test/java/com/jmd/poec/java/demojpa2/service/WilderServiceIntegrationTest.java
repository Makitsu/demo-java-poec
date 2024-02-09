package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.dto.WilderFullDTO;
import com.jmd.poec.java.demojpa2.repository.WilderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class WilderServiceIntegrationTest {

    @Autowired
    private WilderService wilderService;

    @Autowired
    private WilderRepository wilderRepository;

    @Test
    void test_find_nominalCase() {
        // GIVEN

        // WHEN
        WilderFullDTO foundWilder = wilderService.find(1L);
        // THEN
        Assertions.assertNotNull(foundWilder);
        Assertions.assertEquals(1L,foundWilder.getId());
        Assertions.assertEquals("TEST",foundWilder.getName());
        Assertions.assertEquals("TEST",foundWilder.getCategory());
        Assertions.assertEquals("TEST",foundWilder.getEmail());
    }
}
