package example.controller;

import example.service.EmailService;
import jakarta.validation.Valid;

import example.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import example.service.UserService;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin

public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private PasswordEncoder encoder;
    @Autowired
    private EmailService emailService;

    @GetMapping("/user/{id}")
    public User getUser(@PathVariable long id){
        return userService.getUserById(id);
    }

    @PostMapping("/user")
    public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
        user.setPassword(encoder.encode(user.getPassword()));
        User savedUser = userService.saveOrUpdate(user);
        return ResponseEntity.ok(savedUser);
    }

    @PutMapping("/user/{id}")
    public ResponseEntity<User> updateUser(@RequestBody @Valid User user){
        userService.saveOrUpdate(user);
        return ResponseEntity.ok(user);
    }

    @DeleteMapping("/user/{id}")
    public void deleteUser(@PathVariable long id){
        userService.deleteUserById(id);
    }

    @GetMapping("/users")
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping("/users")
    public void addListOfUsers(@RequestBody @Valid List<User> users) {
        userService.addListOfUsers(users);
    }

    @DeleteMapping("/users")
    public void deleteListOfUsers(@RequestBody @Valid List<User> users) {
        users.forEach((user)->{
            userService.deleteListOfUsers(users);
        });
    }

    @GetMapping("/users/course/{userId}")
    public ResponseEntity<List<User>> getUsersByCourseId(@PathVariable("userId") Long userId) {
        List<User> users = userService.getUsersByCourseId(userId);
        return ResponseEntity.ok(users);
    }
    @PostMapping("users/sendemail/{userId}")
    public ResponseEntity<String> sendEmailConfirmation(@PathVariable("userId") Long userId) {
        User user = userService.getUserById(userId);
        String email = user.getName();
        if (email != null && !email.trim().isEmpty()) {
            emailService.sendEmail(email, "Подтверждение электронной почты", "Пожалуйста, подтвердите вашу электронную почту.");
            return ResponseEntity.ok("Письмо отправлено на адрес " + email);
        } else {
            return ResponseEntity.badRequest().body("Логин пользователя должен содержать электронную почту.");
        }
    }
    @PostMapping("/users/file")
    public String writetofile() {
        List<User> users = getAllUsers();

        try {
            FileWriter writer = new FileWriter("users.txt");

            for (User user : users) {
                writer.write(user.toString() + "\n");
            }

            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
            return "Error writing to file";
        }

        return "Successfully wrote to file";
    }
}
