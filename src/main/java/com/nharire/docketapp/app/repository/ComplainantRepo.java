package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Complainant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ComplainantRepo extends JpaRepository<Complainant,String> {




    Complainant getById(String nationalId);

    Optional<Complainant> findByNationalIdEqualsIgnoreCase(String nationalId);



    List<Complainant> getDateOfComplaint(String dateReported);
}
