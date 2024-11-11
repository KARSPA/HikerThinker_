package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.Inventory;
import fr.karspa.hikerthinkerv3.bo.UserEquipment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UserEquipmentRepository extends JpaRepository<UserEquipment, Long> {

    @EntityGraph(attributePaths = {"equipment","category","brand","userEquipmentStatistics"})
    Optional<List<UserEquipment>> findByInventory(Inventory inventory);

    @EntityGraph(attributePaths = {"equipment","category","brand","userEquipmentStatistics"})
    Optional<UserEquipment> findByIdAndInventory(Long equipmentId, Inventory inventory);

    @EntityGraph(attributePaths = {"equipment","category","brand","userEquipmentStatistics"})
    Optional<UserEquipment> findByNameAndInventory(String name, Inventory inventory);
}
