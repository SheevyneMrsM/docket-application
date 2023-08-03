package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PoliceStationRepo extends JpaRepository<PoliceStation,Long> {

    Optional<PoliceStation> findByPoliceStationNameEqualsIgnoreCase(String policeStationName);

    Optional<PoliceStation> findByIdEquals(Long id);



    List<PoliceStationDTO> addReview(PoliceStation policeStation);
}
