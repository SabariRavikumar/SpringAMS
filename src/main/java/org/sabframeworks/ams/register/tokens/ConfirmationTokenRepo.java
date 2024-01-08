package org.sabframeworks.ams.register.tokens;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Repository
public interface ConfirmationTokenRepo extends JpaRepository<ConfirmationToken, Long> {

    Optional<ConfirmationToken> findBytoken(String token);

    @Transactional
    @Modifying
    @Query("update ConfirmationToken c set c.confirmedAt = ?2 where c.token =?1")
    int updateConfirmedAt(String token, LocalDateTime confirmedAt);
}
