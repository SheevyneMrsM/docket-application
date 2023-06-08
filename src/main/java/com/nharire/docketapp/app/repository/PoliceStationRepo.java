package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PoliceStationRepo extends JpaRepository<PoliceStation,Long> {
    PoliceStation getById(Long id);

    List<PoliceStationDTO> addReview(PoliceStation policeStation);
}
