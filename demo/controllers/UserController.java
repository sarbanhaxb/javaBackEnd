package com.example.demo.controllers;

import com.example.demo.entity.User;
import com.example.demo.entity.payload.UserPayload;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Size;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import com.example.demo.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.*;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    @Size
    private final UserService userService;

    @Operation(summary = "Получить всех пользователей", tags = {"Users"}, description = "Контроллер возвращает всех пользователей", responses = {
    @ApiResponse(responseCode = "200", description = "Пользователи найдены")})
    @GetMapping
    public ResponseEntity<Iterable<User>> getUsers(){
        return ResponseEntity.ok(userService.findUsers());
    }


    @Operation(summary = "Получить пользователя", tags = {"Users"}, description = "Контроллер возвращает определенного пользователя", responses = {
            @ApiResponse(responseCode = "200", description = "Пользователь найден"),
            @ApiResponse(responseCode = "404", description = "Пользователь не найден")
    }, parameters = {
            @Parameter(name="id", description = "ID пользователя", required = true)
    })
    @GetMapping("{userid}")
    ResponseEntity<User> getUser(@PathVariable("userid") int id){
        return userService.findUser(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }


    @Operation(summary = "Создать пользователя", tags = {"Users"}, description = "Контроллер для создания пользователя", responses = {
            @ApiResponse(responseCode = "201", description = "Пользователь создан")
    }, requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Данные пользователя", required = true))
    @PostMapping
    public ResponseEntity<User> createUser(@RequestBody @Valid UserPayload user){
        User saved = userService.saveUser(user);
        return ResponseEntity.status(201).body(saved);
    }


    @Operation(summary = "Обновить данные пользователя", tags = {"Users"},
            description = "Контроллер для изменения данных пользователя",
            responses = {
            @ApiResponse(responseCode = "400", description = "Пользователь не найден"),
            @ApiResponse(responseCode = "400", description = "Отсутствие контента"),
            },
            parameters = {
            @Parameter(name = "id", description = "ID пользователя")
            },
            requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(description = "Обновленные данные пользователя", required = true))
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUser(@PathVariable int id, @RequestBody @Valid UserPayload user){
        Optional<User> existingUser = userService.findUser(id);
        if (existingUser.isEmpty()){
            return ResponseEntity.notFound().build();
        }
        userService.updateUser(id, user);
        return ResponseEntity.noContent().build();
    }


    @Operation(summary = "Удаление пользователя", tags = {"Users"},
    description = "Контроллер для удаления пользователя",
    responses = {
            @ApiResponse(responseCode = "400", description = "Пользователь отсутствует"),
            @ApiResponse(responseCode = "400", description = "Отсутствие контента")
    },
    parameters = {
            @Parameter(name = "id", description = "ID пользователя")
    }    )
    @DeleteMapping("/{id}")
    void deleteUser(@PathVariable int id){userService.deleteUser(id);}

//    @GetMapping

//    List<User> getUsers() {
//
//        return userService.findUsers();
//    }
//
//    @GetMapping("/api/find")
//    List<User> getUsersIf(@RequestParam(required = false) int age) {
//        return userService.ageFilter(age);
//    }
//
//    @GetMapping("/{userid}")
//    User getUser(@PathVariable("userid") int id) {
//        return userService.findUser(id).orElseThrow(() -> new NoSuchElementException("USER NOT FOUND"));
//    }
//
//    @PostMapping()
//    void createUser(@RequestBody @Valid UserPayload userPayload) {
//        userService.saveUser(userPayload.name(), userPayload.email(), userPayload.age());
//    }
//
//    @PutMapping("/{id}")
//    void userUpdate(@PathVariable int id, @RequestBody @Valid UserPayload userPayload) {
//        userService.updateUser(id, userPayload);
//    }
//
//    @DeleteMapping("/{id}")
//    void deleteUser(@PathVariable int id) {
//        userService.deleteUser(id);
//    }
  }