package fr.karspa.hikerthinkerv3.bll;

import fr.karspa.hikerthinkerv3.bo.Role;
import fr.karspa.hikerthinkerv3.bo.User;
import fr.karspa.hikerthinkerv3.dao.RoleRepository;
import fr.karspa.hikerthinkerv3.dto.auth.LoginResponseDTO;
import fr.karspa.hikerthinkerv3.dto.auth.RegisterResponseDTO;
import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final TokenService tokenService;
    private UserService userService;
    private RoleRepository roleRepository;
    private PasswordEncoder passwordEncoder;

    public AuthService(UserService userService, RoleRepository roleRepository, PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager, TokenService tokenService) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.tokenService = tokenService;
    }

    public ResponseModel<RegisterResponseDTO> registerUser(String username, String email, String password) {
        //Checks si username déjà pris !
        boolean isUsernameAvailable = this.isUsernameAvailable(username);
        if(!isUsernameAvailable) {
            return ResponseModel.buildResponse("710","Un compte existe déjà avec ce pseudo.", null);
        }

        //Checks si email déjà pris !
        boolean isEmailAvailable = this.isEmailAvailable(email);
        if(!isEmailAvailable) {
            return ResponseModel.buildResponse("711","Un compte existe déjà avec cet email.", null);
        }

        //Encoder mot de passe
        String encodedPassword = passwordEncoder.encode(password);

        //Mettre le role "USER"
        Role userRole = roleRepository.findByAuthority("USER");
        Set<Role> authorities = new HashSet<>();
        authorities.add(userRole);

        //Sauvegarder en BDD grace au service qui se chargera également de créer l'enregistrement de UserStats et Inventory.
        User newUser = userService.createUser(new User(username, email, encodedPassword, authorities));

        RegisterResponseDTO registerResponseDTO = new RegisterResponseDTO(newUser.getUserId(), newUser.getUsername(), newUser.getEmail());

        return ResponseModel.buildResponse("201", "Utilisateur créé avec succès.", registerResponseDTO);
    }


    public ResponseModel<LoginResponseDTO> login(String username, String password) {

        try{
            Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username, password));
            String token = tokenService.generateJwt(authentication);

            User foundUser = (User) authentication.getPrincipal();
            LoginResponseDTO loginResponseDTO = new LoginResponseDTO(foundUser.getUserId(), foundUser.getUsername(), foundUser.getEmail(), token);

            return ResponseModel.buildResponse("200", "Utilisateur trouvé.", loginResponseDTO);

        }catch (AuthenticationException e) {
            return ResponseModel.buildResponse("703", "Erreur d'authentification. Pseudo ou mot de passe incorrect.", null);
        }
    }



    private boolean isUsernameAvailable(String username) {
        return userService.checkUsername(username);
    }
    private boolean isEmailAvailable(String email) {return userService.checkEmail(email);}
}
