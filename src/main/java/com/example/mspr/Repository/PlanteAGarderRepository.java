package com.example.mspr.Repository;

import com.example.mspr.bo.PlanteAGarder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PlanteAGarderRepository extends JpaRepository<PlanteAGarder, Integer> {
    List<PlanteAGarder> findByContrat_Id(Integer id);

    List<PlanteAGarder> findByContrat_Client_Id(Integer id);
}
