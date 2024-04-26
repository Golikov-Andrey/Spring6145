package ru.maliutin.shop.paymentapp.services;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.maliutin.shop.paymentapp.models.Account;
import ru.maliutin.shop.paymentapp.models.Transaction;
import ru.maliutin.shop.paymentapp.models.exceptions.ExcessAmountException;
import ru.maliutin.shop.paymentapp.repositories.PaymentRepository;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;


@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {

    @Mock
    private PaymentRepository paymentRepository;

    @InjectMocks
    private PaymentService paymentService;

    /**
     * Успешная оплата.
     */
    @Test
    public void transactionExpectCorrect(){
        Long creditNumber = 1L;
        Long debitNumber = 2L;
        BigDecimal sum = new BigDecimal(100);
        Transaction transaction = new Transaction();
        transaction.setCreditNumber(creditNumber);
        transaction.setDebitNumber(debitNumber);
        transaction.setSum(sum);

        Account creditAccount = new Account();
        BigDecimal creditBalance = new BigDecimal(1000);
        creditAccount.setBalance(creditBalance);
        Account debitAccount = new Account();
        BigDecimal debitBalance = new BigDecimal(0);
        debitAccount.setBalance(debitBalance);

        given(paymentRepository.findByNumber(creditNumber))
                .willReturn(creditAccount);
        given(paymentRepository.findByNumber(debitNumber))
                .willReturn(debitAccount);
        paymentService.transaction(transaction);

        verify(paymentRepository).findByNumber(creditNumber);
        verify(paymentRepository).findByNumber(debitNumber);
        verify(paymentRepository).save(creditAccount);
        verify(paymentRepository).save(debitAccount);

        Assertions.assertEquals(creditBalance.subtract(sum),
                creditAccount.getBalance());
        Assertions.assertEquals(debitBalance.add(sum),
                debitAccount.getBalance());
    }

    /**
     * Исключение при попытке списать больше денежных средств,
     * чем есть на счету.
     */
    @Test
    public void transactionExpectException(){
        BigDecimal sum = new BigDecimal(1000);
        Account creditAccount = new Account();
        Long creditNumber = 1L;
        BigDecimal creditBalance = new BigDecimal(100);
        creditAccount.setBalance(creditBalance);
        Transaction transaction = new Transaction();
        transaction.setCreditNumber(creditNumber);
        transaction.setSum(sum);

        given(paymentRepository.findByNumber(creditNumber))
                .willReturn(creditAccount);

        assertThrows(ExcessAmountException.class,
                () -> paymentService.transaction(transaction));

        verify(paymentRepository).findByNumber(creditNumber);
    }

    /**
     * Отмена оплаты.
     */
    @Test
    public void rollbackTransaction(){
        Long creditNumber = 1L;
        Long debitNumber = 2L;
        BigDecimal sum = new BigDecimal(100);
        Transaction transaction = new Transaction();
        transaction.setCreditNumber(creditNumber);
        transaction.setDebitNumber(debitNumber);
        transaction.setSum(sum);

        Account creditAccount = new Account();
        BigDecimal creditBalance = new BigDecimal(0);
        creditAccount.setBalance(creditBalance);
        Account debitAccount = new Account();
        BigDecimal debitBalance = new BigDecimal(1000);
        debitAccount.setBalance(debitBalance);

        given(paymentRepository.findByNumber(creditNumber))
                .willReturn(creditAccount);
        given(paymentRepository.findByNumber(debitNumber))
                .willReturn(debitAccount);
        paymentService.rollbackTransaction(transaction);

        verify(paymentRepository).findByNumber(debitNumber);
        verify(paymentRepository).findByNumber(creditNumber);
        verify(paymentRepository).save(creditAccount);
        verify(paymentRepository).save(debitAccount);

        Assertions.assertEquals(debitBalance.subtract(sum),
                debitAccount.getBalance());
        Assertions.assertEquals(creditBalance.add(sum),
                creditAccount.getBalance());
    }

    /**
     * Получение списка всех счетов.
     */
    @Test
    public void getAllAccounts(){
        List<Account> accounts = List.of(new Account());

        given(paymentRepository.findAll()).willReturn(accounts);

        List<Account> testAccounts = paymentService.getAllAccounts();

        verify(paymentRepository).findAll();

        assertEquals(accounts, testAccounts);
    }
}
