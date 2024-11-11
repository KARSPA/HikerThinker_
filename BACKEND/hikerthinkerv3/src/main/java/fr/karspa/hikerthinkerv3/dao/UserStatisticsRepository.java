package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.UserStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserStatisticsRepository extends JpaRepository<UserStatistics, Long> {
}
