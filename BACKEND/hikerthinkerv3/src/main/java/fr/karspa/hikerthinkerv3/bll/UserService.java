package fr.karspa.hikerthinkerv3.bll;

import fr.karspa.hikerthinkerv3.bo.Inventory;
import fr.karspa.hikerthinkerv3.bo.User;
import fr.karspa.hikerthinkerv3.bo.UserStatistics;
import fr.karspa.hikerthinkerv3.dao.InventoryRepository;
import fr.karspa.hikerthinkerv3.dao.UserRepository;
import fr.karspa.hikerthinkerv3.dao.UserStatisticsRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService implements UserDetailsService {

    private UserRepository userRepository;
    private InventoryRepository inventoryRepository;
    private UserStatisticsRepository userStatisticsRepository;

    public UserService(UserRepository userRepository, InventoryRepository inventoryRepository, UserStatisticsRepository userStatisticsRepository) {
        this.userRepository = userRepository;
        this.inventoryRepository = inventoryRepository;
        this.userStatisticsRepository = userStatisticsRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(username));
    }

    public User getUserById(Long userId){
        return userRepository.findByUserId(userId).orElse(null);
    }

    @Transactional
    public User createUser(User user) {
        User newUser = userRepository.save(user);

        inventoryRepository.save(new Inventory(newUser)); //Création d'un nouvel enregistrement inventaire lié à cet user.
        userStatisticsRepository.save(new UserStatistics(newUser)); //Création d'un nouvel enregistrement UserStatistics lié à cet user.

        return newUser;
    }

    public boolean checkUsername(String username) {
        return userRepository.findByUsername(username).isEmpty();
    }

    public boolean checkEmail(String email) {
        return userRepository.findByEmail(email).isEmpty();
    }
}
