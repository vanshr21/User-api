package com.vansh.User.controller;

import com.vansh.User.dto.*;
import com.vansh.User.service.UserService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/user")
    public ResponseEntity<CreateUserResponseDTO> create(@Valid @RequestBody CreateUserRequestDTO createUserRequestDTO) {
        CreateUserResponseDTO userCreated = userService.createUser(createUserRequestDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(userCreated);
    }

    @GetMapping("/user")
    public ResponseEntity<GetUserResponseDTO> get(@RequestParam("id") Integer id) {
        GetUserResponseDTO getUser = userService.getUser(id);
        return ResponseEntity.status(HttpStatus.OK).body(getUser);
    }

    @GetMapping("/users")
    public ResponseEntity<List<GetUserResponseDTO>> getAll() {
        List<GetUserResponseDTO> getAllUsers = userService.getUsers();
        return ResponseEntity.status(HttpStatus.OK).body(getAllUsers);
    }

    @PutMapping("/user")
    public ResponseEntity<UpdateUserResponseDTO> update(@RequestParam("id") Integer id, @Valid @RequestBody UpdateUserRequestDTO updateUserRequestDTO) {
        UpdateUserResponseDTO updateUserResponseDTO = userService.updateUser(id, updateUserRequestDTO);
        return ResponseEntity.status(HttpStatus.OK).body(updateUserResponseDTO);
    }

    @PatchMapping("/user/soft-delete")
    public ResponseEntity<Void> softDelete(@RequestParam("id") Integer id) {
        userService.softDeleteUser(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @DeleteMapping("user")
    public ResponseEntity<Void> deleteUser(@RequestParam("id") Integer id) {
        userService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
