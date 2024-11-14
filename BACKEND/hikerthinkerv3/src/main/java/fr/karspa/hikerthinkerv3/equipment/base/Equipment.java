package fr.karspa.hikerthinkerv3.equipment.base;

import fr.karspa.hikerthinkerv3.equipment.brand.Brand;
import fr.karspa.hikerthinkerv3.equipment.category.Category;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(uniqueConstraints = {@UniqueConstraint(columnNames = {"name","weight"})}, name = "equipments")
public class Equipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "equipment_id")
    private Long equipmentId;


    private String name;

    private String description;

    private int weight;

    @Column(columnDefinition = "varchar(255) DEFAULT 'equipment_placeholder'", name = "image_url")
    private String imageUrl;


    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;



    public EquipmentDTO toDTO(){
        return new EquipmentDTO(this.equipmentId,this.name,this.description,this.weight,this.category,this.brand);
    }
}
