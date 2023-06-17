package com.nharire.docketapp.app.service.impl;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.Complainant;
import com.nharire.docketapp.app.model.CrimeRegister;
import com.nharire.docketapp.app.model.dto.CrimeRegisterDTO;
import com.nharire.docketapp.app.repository.AccusedRepo;
import com.nharire.docketapp.app.repository.CrimeRegisterRepo;
import com.nharire.docketapp.app.service.CrimeRegisterService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
@Service
@Slf4j
@RequiredArgsConstructor
public class CrimeRegisterImpl implements CrimeRegisterService {
    private final CrimeRegisterRepo crimeRegisterRepo;

    @Override
    public CrimeRegister saveCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {
        log.info("SAVE CRIME REGISTER DETAILS: {}", crimeRegisterDTO);
        CrimeRegister crimeRegister = new CrimeRegister();
        BeanUtils.copyProperties(crimeRegisterDTO,crimeRegister);
        log.info("Saving crime register details: {}",crimeRegister);
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public CrimeRegisterDTO updateCrimeRegisterDetails(CrimeRegisterDTO crimeRegisterDTO) {
        Optional<CrimeRegister> crimeRegister =crimeRegisterRepo.findById(crimeRegisterDTO.getCrimeId());
        CrimeRegister crimeRegister1;
        if (crimeRegister.isPresent()){
            crimeRegister1 =crimeRegister.get();
            BeanUtils.copyProperties(crimeRegisterDTO, crimeRegister1);
        }else {
            throw new RuntimeException("No details found ,cant update!!!");
        }
        BeanUtils.copyProperties(crimeRegister1,crimeRegisterDTO);
        return crimeRegisterDTO;
    }

    @Override
    public void deleteCrimeRegisterById(Long crimeId) {
        crimeRegisterRepo.deleteById(crimeId);

    }

    @Override
    public void deleteAccusedDetailsById(Long id) {
        crimeRegisterRepo.deleteById(id);

    }

    @Override
    public void deleteComplainantDetailsById(Long id) {
        crimeRegisterRepo.deleteById(id);

    }

    @Override
    public CrimeRegister addAccused(Accused accused) {
        CrimeRegister crimeRegister= crimeRegisterRepo.getById(accused.getCrime().getCrimeId());
        crimeRegister.getAccusedList().add(accused);
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public CrimeRegister addComplainant(Complainant complainant) {
     CrimeRegister crimeRegister = crimeRegisterRepo.getById(complainant.getCrime().getCrimeId());
     crimeRegister.getComplainer().getCrime();
        return crimeRegisterRepo.save(crimeRegister);
    }

    @Override
    public List<CrimeRegister> getAllCrimeRegisterDetails() {
        return crimeRegisterRepo.findAll();
    }

    @Override
    public Optional<CrimeRegister> getCrimeRegisterDetails(Long crimeId) {
        return crimeRegisterRepo.findById(crimeId);
    }

    @Override
    public List<CrimeRegister> getAllByDateOfReport(Date dateOfReport) {
        return crimeRegisterRepo.getAllByDateOfReport(dateOfReport);
    }
}
