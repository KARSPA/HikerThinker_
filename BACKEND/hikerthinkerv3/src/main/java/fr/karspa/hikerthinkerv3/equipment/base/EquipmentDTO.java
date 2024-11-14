package fr.karspa.hikerthinkerv3.equipment.base;

import fr.karspa.hikerthinkerv3.equipment.brand.Brand;
import fr.karspa.hikerthinkerv3.equipment.category.Category;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EquipmentDTO {

    private Long id;
    private String name;
    private String description;
    private int weight;

    private Category category;

    private Brand brand;

}
