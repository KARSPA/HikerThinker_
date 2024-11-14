package fr.karspa.hikerthinkerv3.auth;

import fr.karspa.hikerthinkerv3.auth.dto.LoginDTO;
import fr.karspa.hikerthinkerv3.auth.dto.LoginResponseDTO;
import fr.karspa.hikerthinkerv3.auth.dto.RegisterDTO;
import fr.karspa.hikerthinkerv3.auth.dto.RegisterResponseDTO;
import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/register")
    public ResponseEntity<ResponseModel<RegisterResponseDTO>> registerUser(@RequestBody RegisterDTO body){

        ResponseModel<RegisterResponseDTO> response = authService.registerUser(body.getUsername(),body.getEmail(),body.getPassword());

        if(response.getCode().equals("201")){
            return ResponseEntity.status(HttpStatus.CREATED).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

    }

    @PostMapping("/login")
    public ResponseEntity<ResponseModel<LoginResponseDTO>> loginUser(@RequestBody LoginDTO body){

        ResponseModel<LoginResponseDTO> response = authService.login(body.getUsername(),body.getPassword());

        if(response.getCode().equals("200")){
            return ResponseEntity.status(HttpStatus.OK).body(response);
        }else{
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        }
    }

}
