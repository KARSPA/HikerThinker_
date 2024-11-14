package fr.karspa.hikerthinkerv3.equipment.base;

import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/equipments")
public class EquipmentController {

    private EquipmentService equipmentService;

    public EquipmentController(EquipmentService equipmentService) {
        this.equipmentService = equipmentService;
    }

    @GetMapping("")
    public ResponseEntity<ResponseModel<List<EquipmentDTO>>> getAllEquipments() {

        ResponseModel<List<EquipmentDTO>> response = equipmentService.findAll();

        return ResponseEntity.ok(response);
    }

    @GetMapping("{equipmentId}")
    public ResponseEntity<ResponseModel<EquipmentDTO>> getEquipmentById(@PathVariable("equipmentId") Long equipmentId) {

        ResponseModel<EquipmentDTO> response = equipmentService.findById(equipmentId);

        if(response.getCode().equals("750")){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(response);
        }

        return ResponseEntity.ok(response);
    }
}
