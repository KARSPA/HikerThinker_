package fr.karspa.hikerthinkerv3.dto;

import fr.karspa.hikerthinkerv3.bo.Brand;
import fr.karspa.hikerthinkerv3.bo.Category;
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
