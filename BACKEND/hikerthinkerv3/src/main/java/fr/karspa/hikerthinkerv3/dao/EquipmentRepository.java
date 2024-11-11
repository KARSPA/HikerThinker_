package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.Equipment;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EquipmentRepository extends JpaRepository<Equipment, Long> {

//    @Query(value = "SELECT E.equipment_id, E.weight, E.description, E.name, E.image_url, C.category_id, C.cat_name, B.brand_id, B.brand_name FROM equipments E INNER JOIN categories C ON E.category_id = C.category_id INNER JOIN brands B ON E.brand_id = B.brand_id", nativeQuery = true)
//    List<Equipment> findAll();

    @EntityGraph(attributePaths = {"category", "brand"})
    List<Equipment> findAll();

    @EntityGraph(attributePaths = {"category", "brand"})
    Optional<Equipment> findByEquipmentId(Long equipmentId);
}
