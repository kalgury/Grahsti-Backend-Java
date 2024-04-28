package com.example.grahstibackend.controllers.Grahsti;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grahstibackend.entities.Group;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.services.AuthSerivce;
import com.example.grahstibackend.services.GroupService;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grahsti")
public class GrahstiController {

    private final AuthSerivce authSerivce;
    private final GroupService groupService;

    public GrahstiController(
            AuthSerivce authService,
            GroupService groupService) {
        this.authSerivce = authService;
        this.groupService = groupService;
    }

    // -------------- group apis
    @GetMapping("/groups")
    public ResponseEntity<Iterable<Group>> getGroupsListing() {
        // implement a user based groups listing
        User userDetails = authSerivce.getAuthenticatedUser();
        Iterable<Group> groupsList = groupService.getGroupListing(userDetails.id);

        return ResponseEntity.ok(groupsList);
    }

    @GetMapping("/group/{groupId}")
    public String getGroupDetails(@PathVariable String groupID) {
        return new String();
    }

    @PostMapping("/group")
    public String createGroup(@RequestBody String entity) {
        User userDetails = authSerivce.getAuthenticatedUser();
        Group newGroup = groupService.createNewGroup();

        // return ResponseEntity.ok(groupsList);
        return "DONE";
    }

    @PutMapping("group/{groupId}")
    public String updateGroup(@PathVariable String groupId, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // TODO: join group links can be done by some deeplinks or so

    // ------------- group members

    @GetMapping("/members")
    public String getGroupMembers(@RequestParam String param) {
        return new String();
    }

    // restrict this only to admins
    @PostMapping("/member/{groupId}")
    public String addMemberToGroup(@PathVariable String id, @RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @PutMapping("/member/{id}")
    public String updateGroupMember(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // can be used to kick a group member and leave group
    @DeleteMapping("/member/{id}")
    public String deleteGroupMember(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // ----------- expenses

    @GetMapping("/expenses/{groupId}")
    public String getGroupExpenses(@PathVariable String id, @RequestParam String param) {
        // recente first
        // paginated
        //
        return new String();
    }

    @GetMapping("/expense/details/{expenseId}")
    public String getExpenseDetails(@PathVariable String id, @RequestParam String param) {
        // recente first
        // paginated
        //
        return new String();
    }

    // restrict this only to admins
    @PostMapping("/expense/{groupId}")
    public String addExpense(@PathVariable String id, @RequestBody String entity) {
        // TODO: process POST request

        return entity;
    }

    @PutMapping("/expense/{id}")
    public String updateExpense(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // can be used to kick a group member and leave group
    @DeleteMapping("/expense/{id}")
    public String deleteExpense(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // swipe based settlement

}
