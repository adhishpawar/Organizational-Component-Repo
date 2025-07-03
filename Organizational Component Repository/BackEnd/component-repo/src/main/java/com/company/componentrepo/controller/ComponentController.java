package com.company.componentrepo.controller;

import com.company.componentrepo.dto.ComponentRequest;
import com.company.componentrepo.dto.ComponentResponse;
import com.company.componentrepo.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @PostMapping("/create")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComponentResponse> createComponent(@RequestBody ComponentRequest request) {
        ComponentResponse response = componentService.createComponent(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<ComponentResponse> updateComponent(
            @PathVariable Long id,
            @RequestBody ComponentRequest request) {
        ComponentResponse response = componentService.updateComponent(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/disable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> disableComponent(@PathVariable Long id) {
        componentService.disableComponent(id);
        return ResponseEntity.noContent().build();
    }

    @PutMapping("/enable/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Void> enableComponent(@PathVariable Long id) {
        componentService.enableComponent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentResponse> getComponent(@PathVariable Long id) {
        ComponentResponse response = componentService.getComponent(id);
        return ResponseEntity.ok(response);
    }

    @GetMapping("/search/name")
    public ResponseEntity<List<ComponentResponse>> searchByName(@RequestParam String name) {
        return ResponseEntity.ok(componentService.searchByName(name));
    }

    @GetMapping("/search/description")
    public ResponseEntity<List<ComponentResponse>> searchByDescription(@RequestParam String desc) {
        return ResponseEntity.ok(componentService.searchByDescription(desc));
    }

    @GetMapping("/search/technology")
    public ResponseEntity<List<ComponentResponse>> searchByTech(@RequestParam String tech)
    {
        return ResponseEntity.ok(componentService.searchByTechnology(tech));
    }

    @GetMapping("/search/tag")
    public ResponseEntity<List<ComponentResponse>> searchByTag(@RequestParam String tag){
        return ResponseEntity.ok(componentService.searchByTag(tag));
    }

    @GetMapping("/search/global")
    public ResponseEntity<List<ComponentResponse>> globalSearch(@RequestParam String query){
        return ResponseEntity.ok(componentService.globalSearch(query));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin/disabled")
    public ResponseEntity<List<ComponentResponse>> getDisabledComponents() {
        return ResponseEntity.ok(componentService.getDisabledComponents());
    }
}
