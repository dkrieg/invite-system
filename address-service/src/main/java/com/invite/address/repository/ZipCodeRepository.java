package com.invite.address.repository;

import com.invite.address.entity.ZipCodeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface ZipCodeRepository extends JpaRepository<ZipCodeEntity, Long> {
    Set<ZipCodeEntity> findByStatesAbbreviation(String abbreviation);

    ZipCodeEntity findByPostalCodeAndPlusFour(String postalCode, String plusFour);
}
