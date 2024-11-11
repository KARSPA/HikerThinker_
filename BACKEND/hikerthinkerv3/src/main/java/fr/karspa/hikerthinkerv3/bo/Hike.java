package fr.karspa.hikerthinkerv3.bo;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "hikes")
public class Hike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hike_id")
    private Long hikeId;

    //Bidirectionnelle car on devra chercher toutes les Randos d'UN utilisateur particulier
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private float duration;

    @Column(nullable = false)
    private float distance;

    @Column(nullable = false)
    private int positive;
    @Column(nullable = false)
    private int negative;

    @Column(nullable = false)
    private LocalDate date;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "hike_userequipment_junction",
            joinColumns = @JoinColumn(name = "hike_id"),
            inverseJoinColumns = @JoinColumn(name = "user_equipment_id")
    )
    private Set<UserEquipment> userEquipments;
}
