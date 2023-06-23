package org.anakrimba.club.repository;

import org.anakrimba.club.model.Barcelona;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BarcelonaRepository extends JpaRepository<Barcelona, Long> {

    List<Barcelona> findByNamaContaining(String nama);
}
