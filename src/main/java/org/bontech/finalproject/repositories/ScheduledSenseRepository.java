package org.bontech.finalproject.repositories;

import org.bontech.finalproject.model.ScheduledSense;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ScheduledSenseRepository extends JpaRepository<ScheduledSense,Long> {
}
