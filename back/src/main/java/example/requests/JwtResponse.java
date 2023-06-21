package example.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

//@AllArgsConstructor
//@NoArgsConstructor
//@Data
//public class JwtResponse {
//
//    private String jwt;
//    private String login;
//    private List<String> roles;
//
//}
@AllArgsConstructor
@NoArgsConstructor
@Data
public class JwtResponse {
    private String jwt;
    private String name;
    private String role;
    private Long id;
}
