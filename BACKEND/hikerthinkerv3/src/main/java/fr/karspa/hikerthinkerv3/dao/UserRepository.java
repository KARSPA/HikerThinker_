package fr.karspa.hikerthinkerv3.dao;

import fr.karspa.hikerthinkerv3.bo.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

    @EntityGraph(attributePaths = {"inventory","userStatistics", "authorities"})
    Optional<User> findByUsername(String username);

   // @Query(value = "SELECT U.user_id, U.username, U.email, U.password, US.average_duration, US.average_distance, US.average_positive, US.average_negative, US.average_weight, US.total_duration, US.total_distance, US.total_positive, US.total_negative, US.hike_count FROM public.users U INNER JOIN user_statistics US ON U.user_id = US.user_id WHERE U.email = ?1", nativeQuery = true)
    Optional<User> findByEmail(String email);

    @EntityGraph(attributePaths = {"inventory", "userStatistics", "authorities"})
    Optional<User> findByUserId(Long userId);
}
