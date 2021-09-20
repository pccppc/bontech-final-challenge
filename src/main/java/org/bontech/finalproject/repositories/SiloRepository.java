package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.Silo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SiloRepository extends JpaRepository<Silo,Long> {

}
