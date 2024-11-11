package fr.karspa.hikerthinkerv3.bll;

import fr.karspa.hikerthinkerv3.bo.Equipment;
import fr.karspa.hikerthinkerv3.dao.EquipmentRepository;
import fr.karspa.hikerthinkerv3.dto.EquipmentDTO;
import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Service
public class EquipmentService {

    private EquipmentRepository equipmentRepository;

    public EquipmentService(EquipmentRepository equipmentRepository) {
        this.equipmentRepository = equipmentRepository;
    }

    public ResponseModel<List<EquipmentDTO>> findAll() {
        List<Equipment> equipments = equipmentRepository.findAll();
        List<EquipmentDTO> equipmentDTOs = equipments.stream().map(Equipment::toDTO).toList();

        return ResponseModel.buildResponse("200","Liste d'équipements trouvée.", equipmentDTOs);
    }


    public ResponseModel<EquipmentDTO> findById(Long equipmentId) {
        Equipment equipment = equipmentRepository.findById(equipmentId).orElse(null);

        if(equipment == null) {
            return ResponseModel.buildResponse("750", "Aucun équipement avec cet Id trouvé.", null);
        }

        return ResponseModel.buildResponse("200","Équipement trouvé avec succès.", equipment.toDTO());
    }
}
