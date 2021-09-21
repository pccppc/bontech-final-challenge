package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.ChangesOfSilo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ChangeOfSiloRepository extends JpaRepository<ChangesOfSilo,Long> {
}
