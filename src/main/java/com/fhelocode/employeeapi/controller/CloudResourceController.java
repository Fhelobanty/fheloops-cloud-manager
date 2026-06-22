package com.fhelocode.employeeapi.controller;

import com.fhelocode.employeeapi.entity.CloudResource;
import com.fhelocode.employeeapi.service.CloudResourceService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/resources")
public class CloudResourceController {

    private final CloudResourceService cloudResourceService;

    public CloudResourceController(CloudResourceService cloudResourceService) {
        this.cloudResourceService = cloudResourceService;
    }

    @PostMapping
    public CloudResource createResource(@Valid @RequestBody CloudResource resource) {
        return cloudResourceService.createResource(resource);
    }

    @GetMapping
    public List<CloudResource> getAllResources() {
        return cloudResourceService.getAllResources();
    }

    @GetMapping("/{id}")
    public CloudResource getResourceById(@PathVariable Long id) {
        return cloudResourceService.getResourceById(id)
                .orElseThrow(() -> new RuntimeException("Resource not found"));
    }

    @PutMapping("/{id}")
    public CloudResource updateResource(
            @PathVariable Long id,
            @RequestBody CloudResource resource) {
        return cloudResourceService.updateResource(id, resource);
    }

    @DeleteMapping("/{id}")
    public void deleteResource(@PathVariable Long id) {
        cloudResourceService.deleteResource(id);
    }
}