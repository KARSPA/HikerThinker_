package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.UserEquipmentStatistics;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserEquipmentStatisticsRepository extends JpaRepository<UserEquipmentStatistics, Long> {
}