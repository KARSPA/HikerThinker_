package fr.karspa.hikerthinkerv3.auth.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponseDTO {

    private Long userId;
    private String username;
    private String email;

    private String jwtToken;
}
