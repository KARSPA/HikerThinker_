package fr.karspa.hikerthinkerv3.equipment.custom;

import fr.karspa.hikerthinkerv3.equipment.brand.Brand;
import fr.karspa.hikerthinkerv3.equipment.category.Category;
import fr.karspa.hikerthinkerv3.statistics.UserEquipmentStatistics;
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
