package fr.karspa.hikerthinkerv3.equipment.custom;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NewUserEquipmentDTO {

    @NotNull
    @Min(value = 1)
    private Long linkedId;

    @NotNull
    private String name;

    @NotNull
    private String description;

    @NotNull
    @Min(value = 0)
    @Max(value = 100)
    private Integer weight;


    private Long categoryId;
    private Long brandId;
}
