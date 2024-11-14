package fr.karspa.hikerthinkerv3.statistics;


import fr.karspa.hikerthinkerv3.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "user_statistics")
public class UserStatistics {

//    @Id
//    @Column(name = "user_statistics_id")
//    private Long userStatisticsId;
//
//    @OneToOne(mappedBy = "userStatistics")
//    private User user;

    @Id
    @Column(name = "user_id")
    private Long userId;

    @MapsId
    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int hikeCount;

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
    private float totalPositive;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float averagePositive;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float totalNegative;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float averageNegative;

    @Column(nullable = false)
    @ColumnDefault("0")
    private float averageWeight;


    public UserStatistics(User user) {
        this.user = user;
    }
}
