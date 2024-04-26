package ru.maliutin.shop.paymentapp.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.maliutin.shop.paymentapp.models.Account;

/**
 * Репозиторий для работы со счетами.
 */
@Repository
public interface PaymentRepository extends JpaRepository<Account, Long> {

    Account findByNumber(Long number);

}
