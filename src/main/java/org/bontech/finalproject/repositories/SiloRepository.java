package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.Silo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public interface SiloRepository extends JpaRepository<Silo,Long> {

    @Query(value = "update Silo set maximumCapacity = ?1, name = ?2 where id = ?3")
    @Transactional
    @Modifying
    void updateSilo(Long maximumCapacity, String name, Long id);

    @Query(nativeQuery = true, value = "select sum(current_weight) from silo where storage_id = ?1")
    Long sumOfAllCurrentWeightWithStorageId(Long storageId);

    List<Silo> findAllByStorageId(Long storageId);
}
