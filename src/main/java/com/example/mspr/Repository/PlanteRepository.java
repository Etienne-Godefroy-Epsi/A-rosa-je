package com.example.mspr.Repository;

import com.example.mspr.bo.Plante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanteRepository extends JpaRepository<Plante, Integer> {
}
