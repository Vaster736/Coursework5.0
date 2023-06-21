package example.controller;

import example.model.User;
import example.repository.UserRepository;
import example.requests.JwtResponse;
import example.requests.RegistrationResponse;
import example.requests.SignInRequest;
import example.requests.SignUpRequest;
import example.security.JwtUtils;
import example.security.UserDetailsImpl;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Slf4j
@AllArgsConstructor
@CrossOrigin
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@RequestBody SignInRequest signInRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(signInRequest.getLogin(), signInRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        String role = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .findFirst()
                .orElse(null);

        JwtResponse jwtResponse = new JwtResponse(jwt, userDetails.getUsername(), role, userDetails.getId());

        return ResponseEntity.ok(jwtResponse);
    }



    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignUpRequest signUpRequest) {
        if (userRepository.existsByLogin(signUpRequest.getLogin())) {
            return ResponseEntity.badRequest().body("Error: Username is already taken!");
        } else if (Objects.equals(signUpRequest.getLogin(), "") ||
                Objects.equals(signUpRequest.getPassword(), "") ||
                Objects.equals(signUpRequest.getName(), "")){
            return ResponseEntity.badRequest().body("Error: Fill in all the fields!");
        }

        User user = User.builder()
                .login(signUpRequest.getLogin())
                .password(encoder.encode(signUpRequest.getPassword()))
                .build();
        user.setRole(signUpRequest.getRole());
        user.setName(signUpRequest.getName());
        userRepository.save(user);

        RegistrationResponse response = new RegistrationResponse(
                "User registered successfully!",
                user.getName(),
                user.getRole()
        );

        return ResponseEntity.ok(response);
    }
}

