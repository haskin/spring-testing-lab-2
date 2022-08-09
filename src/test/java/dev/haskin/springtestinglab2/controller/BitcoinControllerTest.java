package dev.haskin.springtestinglab2.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import dev.haskin.springtestinglab2.dto.BitcoinDTO;
import dev.haskin.springtestinglab2.dto.DataDTO;

@ExtendWith(MockitoExtension.class)
public class BitcoinControllerTest {

    @Mock
    private RestTemplate restTemplate;

    @InjectMocks
    private BitcoinController bitcoinController;

    @Test
    void testGetCostOfOneBitcoin() {
        // System.out.println(BitcoinController.getApiUrl());
        String expectedPrice = "20_000";
        DataDTO dataDTO = new DataDTO(expectedPrice);
        BitcoinDTO bitcoinDTO = new BitcoinDTO(dataDTO);
        when(restTemplate.getForObject(BitcoinController.getApiUrl(), BitcoinDTO.class)).thenReturn(bitcoinDTO);
        assertEquals(expectedPrice, bitcoinController.getCostOfOneBitcoin());
        // verify(bitcoinDTO).getData();
    }

    @Test
    void testGetCostOfOneBitcoin_null() {
        when(restTemplate.getForObject(BitcoinController.getApiUrl(), BitcoinDTO.class))
                .thenReturn(null);
        assertThrows(ResponseStatusException.class,
                () -> {
                    bitcoinController.getCostOfOneBitcoin();
                });
    }

    @Test
    void testGetCostOfOneBitcoin_restTemplateException() {
        when(restTemplate.getForObject(BitcoinController.getApiUrl(), BitcoinDTO.class))
                .thenThrow(new RestClientException(""));
        assertThrows(RestClientException.class,
                () -> {
                    bitcoinController.getCostOfOneBitcoin();
                });
    }
}
