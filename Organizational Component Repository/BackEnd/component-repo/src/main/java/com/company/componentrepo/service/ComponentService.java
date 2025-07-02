package com.company.componentrepo.service;

import com.company.componentrepo.dto.ComponentRequest;
import com.company.componentrepo.dto.ComponentResponse;
import com.company.componentrepo.entity.Component;
import com.company.componentrepo.entity.Tag;
import com.company.componentrepo.repo.ComponentRepository;
import com.company.componentrepo.repo.TagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ComponentService {

    @Autowired
    private ComponentRepository componentRepo;

    @Autowired
    private TagRepository tagRepo;

    //Add new Component
    public ComponentResponse createComponent(ComponentRequest request){
        Component component = new Component();
        component.setName(request.name());
        component.setDescription(request.description());
        component.setTechnologies(request.technologies());
        component.setEnabled(true);

        List<Tag> tags = request.tags().stream()
                .limit(5)
                .map(tagName -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    tag.setComponent(component);
                    return tag;
                } ).toList();

        component.setTags(tags);

        Component saved = componentRepo.save(component);
        return mapToDto(saved);
    }

    private ComponentResponse mapToDto(Component component) {
        return new ComponentResponse(
                component.getId(),
                component.getName(),
                component.getDescription(),
                component.getTechnologies(),
                component.getTags().stream().map(Tag::getName).toList(),
                component.isEnabled()
        );
    }

    //Update Component using Transactional logic
    @Transactional
    public ComponentResponse updateComponent(Long id, ComponentRequest request){
        Component component= componentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not Found"));

        component.setName(request.name());
        component.setDescription(request.description());
        component.setTechnologies(request.technologies());

        component.getTags().clear();
        List<Tag> newTags = request.tags().stream()
                .limit(5)
                .map(tagName -> {
                    Tag tag = new Tag();
                    tag.setName(tagName);
                    tag.setComponent(component);
                    return tag;
                }).toList();

        component.getTags().addAll(newTags);

        Component updated = componentRepo.save(component);
        return mapToDto(updated);
    }

    @Transactional
    public void disableComponent(Long id) {
        Component component = componentRepo.findById(id)
                .orElseThrow(() -> new RuntimeException("Component not found"));
        component.setEnabled(false);
        componentRepo.save(component);
    }

    public ComponentResponse getComponent(Long id) {
        Component component = componentRepo.findById(id)
                .filter(Component::isEnabled)
                .orElseThrow(() -> new RuntimeException("Component not found or disabled"));
        return mapToDto(component);
    }

        //Searching pending

}
