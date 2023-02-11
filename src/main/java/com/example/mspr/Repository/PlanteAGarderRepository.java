package com.example.mspr.Repository;

import com.example.mspr.bo.PlanteAGarder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlanteAGarderRepository extends JpaRepository<PlanteAGarder, Integer> {
}
