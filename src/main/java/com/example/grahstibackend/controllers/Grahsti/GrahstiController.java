package com.example.grahstibackend.controllers.Grahsti;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.grahstibackend.dtos.AddExpenseDto;
import com.example.grahstibackend.dtos.CreateGroupDto;
import com.example.grahstibackend.dtos.ExpensesListDto;
import com.example.grahstibackend.entities.Expense;
import com.example.grahstibackend.entities.Group;
import com.example.grahstibackend.entities.GroupCategory;
import com.example.grahstibackend.entities.GroupMember;
import com.example.grahstibackend.entities.User;
import com.example.grahstibackend.entities.enums.CategoryEnums;
import com.example.grahstibackend.services.AuthSerivce;
import com.example.grahstibackend.services.ExpenseService;
import com.example.grahstibackend.services.GroupCategoryService;
import com.example.grahstibackend.services.GroupService;

import jakarta.validation.Valid;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/grahsti")
@CrossOrigin()
public class GrahstiController {

    private final AuthSerivce authSerivce;
    private final GroupService groupService;
    private final GroupCategoryService groupCategoryService;
    private final ExpenseService expenseService;

    public GrahstiController(
            AuthSerivce authService,
            GroupService groupService,
            GroupCategoryService groupCategoryService,
            ExpenseService expenseService) {
        this.authSerivce = authService;
        this.groupService = groupService;
        this.groupCategoryService = groupCategoryService;
        this.expenseService = expenseService;
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
    public ResponseEntity<Group> getGroupDetails(@PathVariable UUID groupId) {

        Group newGroup = groupService.fetchGroupById(groupId);

        // return ResponseEntity.ok(groupsList);
        return ResponseEntity.ok(newGroup);
    }

    @PostMapping("/group")
    public ResponseEntity<Group> createGroup(@RequestBody CreateGroupDto body) {
        User userDetails = authSerivce.getAuthenticatedUser();
        body.setCreatedBy(userDetails.id);
        Group newGroup = groupService.createNewGroup(body);

        // return ResponseEntity.ok(groupsList);
        return ResponseEntity.ok(newGroup);
    }

    @PostMapping("/group/{groupId}/join")
    public ResponseEntity<String> joinGroup(@PathVariable UUID groupId) {
        User userDetails = authSerivce.getAuthenticatedUser();
       groupService.joinGroup(groupId,userDetails.id);

        // return ResponseEntity.ok(groupsList);
        return ResponseEntity.ok("Member Added to group");
    }

    @PutMapping("group/{groupId}")
    public String updateGroup(@PathVariable String groupId, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    // ------------- group members

    @GetMapping("/group/{groupId}/members")
    public  ResponseEntity<Iterable<GroupMember>> getGroupMembers(@PathVariable UUID groupId) {
        //TODO: append user details like name and all
        Iterable<GroupMember> membersList =  groupService.getGroupMembersListing(groupId);
        return  ResponseEntity.ok(membersList);
    }
    @GetMapping("/group/{groupId}/member-details")
    public  ResponseEntity<GroupMember> getGroupMemberDetails(@PathVariable UUID groupId) {
        User userDetails = authSerivce.getAuthenticatedUser();
        GroupMember groupMemberDetails =  groupService.getGroupMemberDetails(groupId,userDetails.id);
        return  ResponseEntity.ok(groupMemberDetails);
    }

    // // restrict this only to admins
    // @PostMapping("/member/{groupId}")
    // public String addMemberToGroup(@PathVariable String id, @RequestBody String entity) {
    //     // TODO: process POST request

    //     return entity;
    // }

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
    public ResponseEntity<Iterable<ExpensesListDto>> getGroupExpenses(@PathVariable UUID groupId,@RequestParam(defaultValue = "1", required= false) int page) {
        Iterable<ExpensesListDto> expenseList = expenseService.groupExpenseListing(groupId,page);
        return ResponseEntity.ok(expenseList);
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
    public ResponseEntity<String> addExpense(@PathVariable UUID groupId, @Valid @RequestBody AddExpenseDto body) {
        User userDetails = authSerivce.getAuthenticatedUser();

        body.setUserId(userDetails.id);
        body.setGroupId(groupId);
        Expense newExpense = expenseService.addNewExpense(body);
        // Group newGroup = groupService.createNewGroup(body);
        System.out.println(newExpense);
        // TODO: process POST request

        return ResponseEntity.ok("Expense Added");
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

      // TODO: process PUT request and also consume Settlement model
        // id (INT PRIMARY KEY AUTO_INCREMENT)
        // expense_id (INT FOREIGN KEY REFERENCES Expenses(id))
        // from_user_id (INT FOREIGN KEY REFERENCES Users(id)) // User who paid the
        // expense
        // to_user_id (INT FOREIGN KEY REFERENCES Users(id)) // User who owes money
        // amount (DECIMAL(10,2))
        // settled_at (DATETIME) // Optional: When the settlement was completed
        // User userDetails = authSerivce.getAuthenticatedUser();
    @PutMapping("/settle/expense/{expenseId}")
    public ResponseEntity<String> settleExpense(@PathVariable UUID expenseId, @RequestBody String entity) {
        Expense newExpense = expenseService.settleExpense(expenseId,true);
        return ResponseEntity.ok("Expense settled Added");
    }
    @PutMapping("/unsettle/expense/{expenseId}")
    public ResponseEntity<String> unsettleExpense(@PathVariable UUID expenseId, @RequestBody String entity) {
        Expense newExpense = expenseService.settleExpense(expenseId,false);
        return ResponseEntity.ok("Expense settled Added");
    }
    


    @GetMapping("/categories")
    public ResponseEntity<Map<String, List<String>>> getCategory(
            @RequestParam(required = false) Map<String, String> queryParams) {

        UUID groupId = UUID.fromString(queryParams.get("groupId"));

        List<String> predefinedCategories = Arrays.asList(CategoryEnums.values()).stream()
                .map(CategoryEnums::name) // Assuming name() returns the enum name as a string
                .collect(Collectors.toList());
        ;
        List<String> groupCategories = Collections.emptyList();

        if (groupId != null) {
            Iterable<GroupCategory> groupCategories2 = groupCategoryService.listGroupCategories(groupId);
            List<String> tempList = new ArrayList<>(); // Create a temporary list

            // Loop through Iterable and add titles
            for (GroupCategory category : groupCategories2) {
                tempList.add(category.getTitle()); // Assuming getTitle() method
            }
            groupCategories = tempList;
        }

        Map<String, List<String>> result = new HashMap<>();
        result.put("defaultCategory", predefinedCategories);
        result.put("groupCategories", groupCategories);

        return ResponseEntity.ok(result);
    }

    // swipe based settlement

}
