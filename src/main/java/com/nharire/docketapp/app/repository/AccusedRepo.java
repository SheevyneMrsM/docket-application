package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Accused;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AccusedRepo extends JpaRepository<Accused,String> {
}
