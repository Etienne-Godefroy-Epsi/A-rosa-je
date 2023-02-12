package com.example.mspr.Repository;

import com.example.mspr.bo.Contrat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;

@Repository
public interface ContratRepository extends JpaRepository<Contrat, Integer> {
    List<Contrat> findByEtatIn(Collection<Character> etats);

    List<Contrat> findByEtat(Character etat);

    List<Contrat> findByClient_Id(Integer id);

    List<Contrat> findByClient_IdAndEtat(Integer id, Character etat);

    List<Contrat> findByClient_IdAndEtatIn(Integer id, Collection<Character> etats);

    List<Contrat> findByGardien_Id(Integer id);

    List<Contrat> findByGardien_IdAndEtat(Integer id, Character etat);

    List<Contrat> findByGardien_IdAndEtatIn(Integer id, Collection<Character> etats);

    List<Contrat> findByBotaniste_Id(Integer id);

    List<Contrat> findByBotaniste_IdAndEtat(Integer id, Character etat);

    List<Contrat> findByBotaniste_IdAndEtatIn(Integer id, Collection<Character> etats);

    List<Contrat> findByClient_IdAndDatedebutAndDatefin(Integer id, LocalDate datedebut, LocalDate datefin);


}
