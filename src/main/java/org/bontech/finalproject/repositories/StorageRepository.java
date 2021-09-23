package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.Storage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface StorageRepository extends JpaRepository<Storage,Long> {

    @Query(value = "update Storage set name = ?1 where id = ?2")
    @Transactional
    @Modifying
    void updateStorage(String name, Long id);

}
