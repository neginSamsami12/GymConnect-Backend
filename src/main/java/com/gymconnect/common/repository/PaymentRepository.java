package com.gymconnect.common.repository;

import com.gymconnect.common.entity.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

public interface PaymentRepository extends JpaRepository<Payment, UUID> {

    List<Payment> findByPaymentDateBetween(Instant paymentDateAfter, Instant paymentDateBefore);

    @Query("""
       SELECT COALESCE(SUM(p.amount), 0)
       FROM Payment p
       WHERE p.paymentDate BETWEEN :start AND :end
       """)
    BigDecimal sumAmountBetween(
            @Param("start") Instant start,
            @Param("end") Instant end
    );
}