package fr.karspa.hikerthinkerv3.controller;

import fr.karspa.hikerthinkerv3.bll.TokenService;
import fr.karspa.hikerthinkerv3.bll.UserEquipmentService;
import fr.karspa.hikerthinkerv3.dto.EquipmentDTO;
import fr.karspa.hikerthinkerv3.dto.NewUserEquipmentDTO;
import fr.karspa.hikerthinkerv3.dto.UserEquipmentDTO;
import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

@RestController
@RequestMapping("/inventory")
public class InventoryController {

    private TokenService tokenService;
    private UserEquipmentService userEquipmentService;

    public InventoryController(TokenService tokenService, UserEquipmentService userEquipmentService) {
        this.tokenService = tokenService;
        this.userEquipmentService = userEquipmentService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<UserEquipmentDTO>>> getAllUserEquipments(@RequestHeader(name = "Authorization") String token){
        Long requesterId = tokenService.getUserId(token.substring(7));

        ResponseModel<List<UserEquipmentDTO>> response = userEquipmentService.getAllUserEquipments(requesterId);

        if(response.getCode().equals("701")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else if (response.getCode().equals("751")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @GetMapping("{equipmentId}")
    public ResponseEntity<ResponseModel<UserEquipmentDTO>> getUserEquipmentById(@RequestHeader(name = "Authorization") String token, @PathVariable Long equipmentId){
        Long requesterId = tokenService.getUserId(token.substring(7));

        ResponseModel<UserEquipmentDTO> response = userEquipmentService.getUserEquipment(requesterId, equipmentId);

        if(response.getCode().equals("701")){
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
        } else if (response.getCode().equals("752")) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }

    @PostMapping("")
    public ResponseEntity<ResponseModel<UserEquipmentDTO>> addUserEquipment(@RequestHeader(name = "Authorization") String token, @Valid @RequestBody NewUserEquipmentDTO newUserEquipmentDTO, BindingResult bindingResult){

        if(bindingResult.hasErrors()){

            AtomicReference<String> errorMessage = new AtomicReference<>("Erreur. ");
            bindingResult.getFieldErrors().forEach(fieldError -> {
                errorMessage.set(errorMessage + fieldError.getField() +" "+ fieldError.getDefaultMessage()+ ". ");
            });


            ResponseModel<UserEquipmentDTO> response = ResponseModel.buildResponse("400", errorMessage.get(), null);
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
        }

        Long requesterId = tokenService.getUserId(token.substring(7));
        // Validate EquipmentDTO format (@NotNull sur chaque champ !!! Ils seront mappé à ceux de leur équipement rattaché si il sont vides ou égal à 0 pour la weight)
        System.out.println(newUserEquipmentDTO);
        ResponseModel<UserEquipmentDTO> response = userEquipmentService.createUserEquipment(requesterId, newUserEquipmentDTO);
        return ResponseEntity.ok(response);
    }

    @PatchMapping("")
    public String patchUserEquipment(@RequestHeader(name = "Authorization") String token, @RequestBody EquipmentDTO equipmentDTO){
        Long requesterId = tokenService.getUserId(token.substring(7));
        return "nouvel équipement";
    }
}
