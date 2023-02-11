package com.example.mspr.Repository;

import com.example.mspr.bo.PhotoJournaliere;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PhotoRepository extends JpaRepository<PhotoJournaliere, Integer> {
}
