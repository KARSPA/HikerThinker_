package fr.karspa.hikerthinkerv3.dto;

import fr.karspa.hikerthinkerv3.bo.Brand;
import fr.karspa.hikerthinkerv3.bo.Category;
import fr.karspa.hikerthinkerv3.bo.UserEquipmentStatistics;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserEquipmentDTO {

    private Long id;
    private String name;
    private String description;
    private int weight;

    private Category category;

    private Brand brand;

    private UserEquipmentStatistics statistics;
}
