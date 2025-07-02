package com.company.componentrepo.controller;

import com.company.componentrepo.dto.ComponentRequest;
import com.company.componentrepo.dto.ComponentResponse;
import com.company.componentrepo.service.ComponentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/components")
public class ComponentController {

    @Autowired
    private ComponentService componentService;

    @PostMapping
    public ResponseEntity<ComponentResponse> createComponent(@RequestBody ComponentRequest request) {
        ComponentResponse response = componentService.createComponent(request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ComponentResponse> updateComponent(
            @PathVariable Long id,
            @RequestBody ComponentRequest request) {
        ComponentResponse response = componentService.updateComponent(id, request);
        return ResponseEntity.ok(response);
    }

    @PutMapping("/{id}/disable")
    public ResponseEntity<Void> disableComponent(@PathVariable Long id) {
        componentService.disableComponent(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ComponentResponse> getComponent(@PathVariable Long id) {
        ComponentResponse response = componentService.getComponent(id);
        return ResponseEntity.ok(response);
    }
}
