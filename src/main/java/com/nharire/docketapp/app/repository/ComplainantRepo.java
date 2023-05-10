package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Complainant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ComplainantRepo extends JpaRepository<Complainant,String> {
}
