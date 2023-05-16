package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.PoliceStation;
import com.nharire.docketapp.app.model.dto.PoliceStationDTO;
import com.nharire.docketapp.app.repository.AddressRepo;
import com.nharire.docketapp.app.repository.PoliceStationRepo;
import com.nharire.docketapp.app.service.PoliceStationService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class PoliceStationServiceImpl implements PoliceStationService {

    private final PoliceStationRepo policeStationRepo;
    private final AddressRepo addressRepo;

    @Override
    public PoliceStation savePoliceStationDetails(PoliceStationDTO policeStationDTO) {
        log.info("SAVE POLICE STATION DETAILS: {}", policeStationDTO.toString());
        PoliceStation policeStation = new PoliceStation();
        BeanUtils.copyProperties(policeStationDTO,policeStation);
        log.info("Saving police station details: {}", policeStation);
        return policeStationRepo.save(policeStation);
    }

    @Override
    public PoliceStationDTO updatePoliceStationDetails(PoliceStationDTO policeStationDTO) {
        return null;
    }

    @Override
    public void deletePoliceStationDetailsById(Long id) {

    }

    @Override
    public void deleteOfficer(Long id) {

    }

    @Override
    public void deleteReview(Long id) {

    }

    @Override
    public PoliceStationDTO addPoliceStation(PoliceStation policeStation) {
        return null;
    }

    @Override
    public PoliceStationDTO addAddress(Address address) {
        return null;
    }

    @Override
    public List<PoliceStationDTO> addReview(PoliceStation policeStation) {
        return null;
    }

    @Override
    public Optional<PoliceStationDTO> getPoliceStation(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PoliceStationDTO> getAllPoliceStations() {
        return null;
    }
}
