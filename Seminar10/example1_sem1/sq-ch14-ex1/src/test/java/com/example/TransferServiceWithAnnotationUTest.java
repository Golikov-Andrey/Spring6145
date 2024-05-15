package com.example;

import com.example.exceptions.AccountNotFoundException;
import com.example.model.Account;
import com.example.repositories.AccountRepository;
import com.example.services.TransferService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class TransferServiceWithAnnotationUTest {
    @InjectMocks
    private TransferService service;

    @Mock
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


        given(repository.findById(accountSource.getId())).willReturn(Optional.of(accountSource));
        given(repository.findById(accountDestination.getId())).willReturn(Optional.of(accountDestination));
        //........................

        //Блок действия (вызова метода)//........................
        service.transferMoney(1, 2, new BigDecimal(400));
        //........................

        //Блок проверки действия//........................
        verify(repository).changeAmount(1, new BigDecimal(600));
        verify(repository).changeAmount(2, new BigDecimal(900));
        //........................
    }

    @Test
    public void moneyTransferDestinationAccountNotFound(){
        //Блок предусловия//........................
        Account accountSource = new Account();
        accountSource.setId(1);
        accountSource.setAmount(new BigDecimal(1000));


        given(repository.findById(accountSource.getId())).willReturn(Optional.of(accountSource));
        given(repository.findById(2L)).willReturn(Optional.empty());
        //........................

        //Блок действия (вызова метода)//........................
        assertThrows(AccountNotFoundException.class, () -> {service.transferMoney(1, 2, new BigDecimal(400));});
        //........................

        //Блок проверки действия//........................
        verify(repository, never()).changeAmount(anyLong(), any());
        //........................
    }
}
