package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.PoliceStation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PoliceStationRepo extends JpaRepository<PoliceStation,Long> {
}
