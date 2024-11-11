package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface InventoryRepository extends JpaRepository<Inventory, Long> {
}
