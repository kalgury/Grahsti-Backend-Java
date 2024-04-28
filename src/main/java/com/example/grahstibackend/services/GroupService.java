package com.example.grahstibackend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.grahstibackend.entities.Group;
import com.example.grahstibackend.repositories.GroupRepository;

@Service
public class GroupService {
    private final GroupRepository groupRepository;

    public GroupService(GroupRepository groupRepository) {
        this.groupRepository = groupRepository;
    }

    public Iterable<Group> getGroupListing(UUID userId) {
        return groupRepository.findAll();
    }

    public Group createNewGroup() {
        Group user = new Group()
                .setTitle("FIRST GROUP");
        // .setFullName(body.getFullName())
        // .setMobileNumber(body.getMobileNumber());

        return groupRepository.save(user);
    }
}
