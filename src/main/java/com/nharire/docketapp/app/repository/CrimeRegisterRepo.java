package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.CrimeRegister;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CrimeRegisterRepo extends JpaRepository<CrimeRegister,Long> {
}
