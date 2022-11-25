package com.senyk.comprehensiveLaba.gems.dao;

import com.senyk.comprehensiveLaba.gems.entity.Gem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface GemRepository
        extends JpaRepository<Gem,Long> {
    @Query("SELECT s FROM Gem s WHERE s.id=?1")
    Optional<Gem> findById(Long id);
}
