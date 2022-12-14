package com.senyk.comprehensiveLaba.gems.dao;

import com.senyk.comprehensiveLaba.gems.entity.Sketch;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface SketchRepository
        extends JpaRepository<Sketch,Long> {
    @Query("SELECT s FROM Sketch s WHERE s.id=?1")
    Optional<Sketch> findById(Long id);

    @Query("SELECT s FROM Sketch s WHERE s.name=?1")
    Optional<Sketch> findSketchByName(String name);
}
