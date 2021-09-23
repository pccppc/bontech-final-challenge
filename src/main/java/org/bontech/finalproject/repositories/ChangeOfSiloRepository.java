package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.ChangesOfSilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChangeOfSiloRepository extends JpaRepository<ChangesOfSilo,Long> {
    @Query(nativeQuery = true, value = "select * from changes_of_silo where silo_id = ?1 order by date desc limit ?2")
    List<ChangesOfSilo> getHistory(Long siloId, Integer n);
}
