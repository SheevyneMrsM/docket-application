package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Accused;
import com.nharire.docketapp.app.model.dto.AccusedDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AccusedRepo extends JpaRepository<Accused,String> {

    Accused getById(String nationalId);


    List<AccusedDTO> getAccusedByDateReported(String dateReported);
}
