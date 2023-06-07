package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Complainant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ComplainantRepo extends JpaRepository<Complainant,String> {



    Complainant getById(List<Complainant> complainant);
    Complainant getById(String nationalId);

    List<Complainant> getDateOfComplaint(String dateReported);
}
