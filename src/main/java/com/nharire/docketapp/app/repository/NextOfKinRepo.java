package com.nharire.docketapp.app.repository;

import com.nharire.docketapp.app.model.Address;
import com.nharire.docketapp.app.model.NextOfKin;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface NextOfKinRepo extends JpaRepository<NextOfKin,String> {
    NextOfKin getById(String nationalId);

    Optional<NextOfKin> findByNationalIdEqualsIgnoreCase(String nationalId);

    NextOfKin addAddressDetails(Address address);
}
