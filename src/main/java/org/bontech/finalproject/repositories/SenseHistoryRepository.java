package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.SenseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SenseHistoryRepository extends JpaRepository<SenseHistory,Long> {
}
