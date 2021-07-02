package com.jossticeteam.toolx.controller;

import com.jossticeteam.toolx.domain.model.User;
import com.jossticeteam.toolx.domain.service.UserService;
import com.jossticeteam.toolx.resource.SaveUserResource;
import com.jossticeteam.toolx.resource.UserResource;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "users", description = "Users API")
@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    private ModelMapper mapper;

    @Autowired
    private UserService userService;

    @Operation(summary = "Get Users", description = "Gett All Users by Pages", tags = {"users"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "All Users returned", content = @Content(mediaType = "application/json"))
    })
    @GetMapping("/users")
    public Page<UserResource> getAllUsers(Pageable pageable){
        Page<User> usersPage = userService.getAllUsers(pageable);
        List<UserResource> resources = usersPage.getContent().stream().map(this::convertToResource).collect(Collectors.toList());

        return new PageImpl<>(resources, pageable, resources.size());
    }


    @Operation(summary = "Get User by Id", description = "Get a User by specifying Id", tags = {"users"})
    @GetMapping("/users/{id}")
    public UserResource getUserById(@Parameter(description="UserId")@PathVariable(name="id")Long userId) {
        return convertToResource(userService.getUserByUserId(userId));
    }

    @PostMapping("/users")
    public UserResource createUser(@Valid @RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.createUser(user));
    }

    @PutMapping("/users/{id}")
    public UserResource updateUser(@PathVariable(name = "id") Long userId, @Valid @RequestBody SaveUserResource resource){
        User user = convertToEntity(resource);
        return convertToResource(userService.updateUser(userId, user));
    }

    @DeleteMapping("/users/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") Long userId){
        return userService.deleteUser(userId);
    }

    // Auto Mapper
    private User convertToEntity(SaveUserResource resource) {
        return mapper.map(resource, User.class);
    }

    private UserResource convertToResource(User entity) {
        return mapper.map(entity, UserResource.class);
    }
}
