package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Witness;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WitnessRepo extends JpaRepository<Witness,String> {
}
