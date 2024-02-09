package com.jmd.poec.java.demojpa2.service;

import com.jmd.poec.java.demojpa2.domain.dto.WilderFullDTO;
import com.jmd.poec.java.demojpa2.domain.entity.Wilder;
import com.jmd.poec.java.demojpa2.domain.exception.WilderException;
import com.jmd.poec.java.demojpa2.repository.WilderRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class WilderServiceUnitTest {

    @InjectMocks
    private WilderService wilderService;

    @Mock
    private WilderRepository wilderRepository;

    private static Wilder firstWilder = new Wilder(1L,"TEST","TEST","TEST");

    @Test
    void test_find_nominalCase() {
        // GIVEN
        when(wilderRepository.findById(anyLong())).thenReturn(Optional.of(firstWilder));
        // WHEN
        WilderFullDTO foundWilder = wilderService.find(1L);
        // THEN
        Assertions.assertNotNull(foundWilder);
        Assertions.assertEquals(1L,foundWilder.getId());
        Assertions.assertEquals("TEST",foundWilder.getName());
        Assertions.assertEquals("TEST",foundWilder.getCategory());
        Assertions.assertEquals("TEST",foundWilder.getEmail());
        verify(wilderRepository,times(2)).findById(anyLong());
    }

    @Test
    void test_find_emptyWilder() {
        // GIVEN
        when(wilderRepository.findById(anyLong())).thenReturn(Optional.empty());
        // WHEN
        WilderException wilderException = Assertions.assertThrows(WilderException.class, () -> wilderService.find(1L));
        // THEN
        Assertions.assertNotNull(wilderException);
        Assertions.assertNotNull(wilderException.getStatusCode());
        Assertions.assertEquals(HttpStatus.NOT_FOUND,wilderException.getStatusCode());
        Assertions.assertEquals("Wilder 1 is not found !",wilderException.getMessage());
        verify(wilderRepository,times(1)).findById(anyLong());
    }

    @Test
    void test_find_malformedRepository() {
        // GIVEN
        when(wilderRepository.findById(anyLong())).thenThrow(RuntimeException.class);
        //doThrow(RuntimeException.class).when(wilderRepository.findById(anyLong()));
        // WHEN
        RuntimeException dataFormatException = Assertions.assertThrows(RuntimeException.class, () -> wilderService.find(1L));
        // THEN
        Assertions.assertNotNull(dataFormatException);
        verify(wilderRepository,times(1)).findById(anyLong());
    }

    @ParameterizedTest
    @ValueSource(doubles = {1D, 3D, 5D, -3D, 15D, Double.MAX_VALUE}) // six numbers
    void isOdd_ShouldReturnTrueForOddNumbers(Double number) {
        Assertions.assertFalse(Double.isNaN(number));
    }

}