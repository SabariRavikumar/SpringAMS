package org.sabframeworks.ams.appuser;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface AppUserRepo extends JpaRepository<AppUser,Long> {
    Optional<AppUser> findByEmailId(String email);

    @Transactional
    @Modifying
    @Query("update AppUser a set a.enabled = TRUE where a.emailId = ?1")
    int enableAppuser(String email);
}
