package com.example;

import com.example.model.Account;
import com.example.repositories.AccountRepository;
import com.example.services.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.math.BigDecimal;
import java.util.Optional;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@SpringBootTest
public class TestServiceWithAnnotationIntegration {
    @Autowired
    private TransferService service;

    @MockBean
    private AccountRepository repository;

    @Test
    public void moneyTransferCorrectFlow(){
        //Блок предусловия//........................
        Account accountSource = new Account();
        accountSource.setId(1);
        accountSource.setAmount(new BigDecimal(1000));

        Account accountDestination = new Account();
        accountDestination.setId(2);
        accountDestination.setAmount(new BigDecimal(500));


        when(repository.findById(accountSource.getId())).thenReturn(Optional.of(accountSource));
        when(repository.findById(accountDestination.getId())).thenReturn(Optional.of(accountDestination));
        //........................

        //Блок действия (вызова метода)//........................
        service.transferMoney(1, 2, new BigDecimal(400));
        //........................

        //Блок проверки действия//........................
        verify(repository).changeAmount(1, new BigDecimal(600));
        verify(repository).changeAmount(2, new BigDecimal(900));
        //........................
    }


}
