package fr.karspa.hikerthinkerv3.statistics;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
}
