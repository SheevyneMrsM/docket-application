package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Complainant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComplainantRepo extends JpaRepository<Complainant,String> {
}
