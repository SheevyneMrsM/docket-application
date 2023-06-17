package com.nharire.docketapp.app.service;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.CrimeRegisterDTO;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public interface CrimeRegisterService {

    CrimeRegister saveCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO);

    CrimeRegisterDTO updateCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO);

    void deleteCrimeRegisterById(Long crimeId);

    void deleteAccusedDetailsById(Long id);

    void deleteComplainantDetailsById(Long id);

    CrimeRegister addAccused(Accused accused);

    CrimeRegister addComplainant(Complainant complainant);

    List<CrimeRegister> getAllCrimeRegisterDetails();

    Optional<CrimeRegister> getCrimeRegisterDetails(Long crimeId);

    List<CrimeRegister> getAllByDateOfReport(Date dateOfReport);


}
