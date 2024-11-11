package fr.karspa.hikerthinkerv3.bo;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.karspa.hikerthinkerv3.dto.UserEquipmentDTO;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "user_equipments")
public class UserEquipment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_equipment_id")
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "equipment_id")
    private Equipment equipment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    @JsonIgnore //Pour éviter les boucles infinies
    private Inventory inventory;

    @Column(name = "is_archived", columnDefinition = "boolean DEFAULT false")
    private boolean isArchived;

    //Définir la valeur par défaut à celle de l'équipement associé
    @Column(nullable = false)
    private String name;

    //Définir la valeur par défaut à celle de l'équipement associé
    @Column(nullable = false)
    private int weight;

    @Column(nullable = false)
    private String description;

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_equipment_id", referencedColumnName = "user_equipment_id")
    private UserEquipmentStatistics userEquipmentStatistics;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "category_id")
    private Category category;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "brand_id")
    private Brand brand;

    @ManyToMany(mappedBy = "userEquipments")
    private Set<Hike> hikes;


    public UserEquipmentDTO toDTO(){
        return new UserEquipmentDTO(this.id,this.name,this.description, this.weight,this.getCategory(),this.getBrand(), this.userEquipmentStatistics);
    }

    @Override
    public String toString() {
        return "UserEquipment{" +
                "id=" + id +
                ", equipment=" + equipment +
                ", isArchived=" + isArchived +
                ", name='" + name + '\'' +
                ", weight=" + weight +
                ", description='" + description + '\'' +
                ", category=" + category +
                ", brand=" + brand +
                '}';
    }
}
