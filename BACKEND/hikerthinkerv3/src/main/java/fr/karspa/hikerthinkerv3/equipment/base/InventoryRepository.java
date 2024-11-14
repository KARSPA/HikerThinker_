package fr.karspa.hikerthinkerv3.equipment.base;

import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
