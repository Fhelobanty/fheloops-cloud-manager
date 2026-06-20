package com.fhelocode.employeeapi.service;

import com.fhelocode.employeeapi.entity.CloudResource;
import com.fhelocode.employeeapi.repository.CloudResourceRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CloudResourceService {

    private final CloudResourceRepository cloudResourceRepository;

    public CloudResourceService(CloudResourceRepository cloudResourceRepository) {
        this.cloudResourceRepository = cloudResourceRepository;
    }

    public CloudResource createResource(CloudResource resource) {
        return cloudResourceRepository.save(resource);
    }

    public List<CloudResource> getAllResources() {
        return cloudResourceRepository.findAll();
    }

    public Optional<CloudResource> getResourceById(Long id) {
        return cloudResourceRepository.findById(id);
    }

    public CloudResource updateResource(Long id, CloudResource resourceDetails) {

        CloudResource resource = cloudResourceRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));

        resource.setResourceName(resourceDetails.getResourceName());
        resource.setResourceType(resourceDetails.getResourceType());
        resource.setCloudProvider(resourceDetails.getCloudProvider());
        resource.setRegion(resourceDetails.getRegion());
        resource.setEnvironment(resourceDetails.getEnvironment());
        resource.setStatus(resourceDetails.getStatus());
        resource.setMonthlyCost(resourceDetails.getMonthlyCost());
        resource.setOwner(resourceDetails.getOwner());

        return cloudResourceRepository.save(resource);
    }

    public void deleteResource(Long id) {
        cloudResourceRepository.deleteById(id);
    }
}