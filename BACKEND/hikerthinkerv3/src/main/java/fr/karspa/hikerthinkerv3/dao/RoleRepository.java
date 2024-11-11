package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role, Long> {
    Role findByAuthority(String authority);
}
