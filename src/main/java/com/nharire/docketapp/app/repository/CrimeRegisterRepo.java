package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.CrimeRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface CrimeRegisterRepo extends JpaRepository<CrimeRegister,Long> {
    List<CrimeRegister> getAllByDateOfReport(Date dateOfReport);

    CrimeRegister getById(Long crimeId);

    void deleteById(Long crimeId);
}
