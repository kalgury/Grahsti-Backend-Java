package com.example.grahstibackend.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.grahstibackend.entities.GroupCategory;
import com.example.grahstibackend.repositories.GroupCategoryRepository;

@Service
public class GroupCategoryService {
      private final GroupCategoryRepository groupCategoryRepository;

    public GroupCategoryService(GroupCategoryRepository groupCategoryRepository) {
        this.groupCategoryRepository = groupCategoryRepository;
    }

    public Iterable<GroupCategory> listGroupCategories(UUID groupId) {
        return groupCategoryRepository.findAllByGroupId(groupId);
    }

    public void addNewGroupCategory(){
        return;
    }
}
