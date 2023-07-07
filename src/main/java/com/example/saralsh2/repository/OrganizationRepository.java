package com.example.saralsh2.repository;

import com.example.saralsh2.entity.Organization;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface OrganizationRepository extends JpaRepository<Organization, Long> {
    void deleteByRegionId(Long regionId);

    Optional<Organization> findByRegionId(Long regionId);
}
