package com.example.friends2support;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface DonarRepository extends JpaRepository<Donor,Integer> {
    List<Donor> findByBloodGroup(String bloodGroup);
    List<Donor> findByCountry(String country);
    List<Donor> findByDistrict(String district);
    List<Donor> findByState(String state);
}
