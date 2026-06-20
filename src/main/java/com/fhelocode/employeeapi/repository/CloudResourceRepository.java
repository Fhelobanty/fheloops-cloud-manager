package com.fhelocode.employeeapi.repository;

import com.fhelocode.employeeapi.entity.CloudResource;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CloudResourceRepository extends JpaRepository<CloudResource, Long> {
}