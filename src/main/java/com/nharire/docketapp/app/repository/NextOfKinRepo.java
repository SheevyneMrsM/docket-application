package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.NextOfKin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface NextOfKinRepo extends JpaRepository<NextOfKin,String> {

}
