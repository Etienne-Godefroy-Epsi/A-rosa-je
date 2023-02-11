package com.example.mspr.Repository;

import com.example.mspr.bo.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {


    List<Client> findByEtat(Character etat);

}