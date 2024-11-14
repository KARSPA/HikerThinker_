package fr.karspa.hikerthinkerv3.statistics;

import com.fasterxml.jackson.annotation.JsonIgnore;
import fr.karspa.hikerthinkerv3.equipment.custom.UserEquipment;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class UserEquipmentStatistics {

    @Id
    @Column(name = "user_equipment_id")
    private Long userEquipmentStatisticsId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_equipment_id")
    @JsonIgnore //Pour éviter les boucles infinies
    private UserEquipment userEquipment;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int use_count;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float totalDistance;
    @Column(nullable = false)
    @ColumnDefault("0")
    private float averageDistance;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float totalDuration;
    @Column(nullable = false)
    @ColumnDefault("0")
    private float averageDuration;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float usagePercentage;




    public UserEquipmentStatistics(float totalDistance, float averageDistance, float totalDuration, float averageDuration, float usagePercentage) {
        this.totalDistance = totalDistance;
        this.averageDistance = averageDistance;
        this.totalDuration = totalDuration;
        this.averageDuration = averageDuration;
        this.usagePercentage = usagePercentage;
    }

}
