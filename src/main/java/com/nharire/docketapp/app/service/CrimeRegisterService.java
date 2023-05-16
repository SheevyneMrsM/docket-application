package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.CrimeRegisterDTO;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface CrimeRegisterService {

    CrimeRegister saveCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO);

    CrimeRegisterDTO updateCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO);

    void deleteCrimeRegisterById(Long crimeId);

    void deleteAccusedDetailsById(String nationalId);

    void deleteComplainantDetailsById(String nationalId);

    CrimeRegisterDTO addAccused(Accused accused);

    CrimeRegisterDTO addComplainant(Complainant complainant);

    List<CrimeRegisterDTO> getAllCrimeRegisterDetails();

    Optional<CrimeRegisterDTO> getCrimeRegisterDetails(Long crimeId);


}
