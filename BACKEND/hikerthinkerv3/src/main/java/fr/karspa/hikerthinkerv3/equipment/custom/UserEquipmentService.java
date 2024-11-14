package fr.karspa.hikerthinkerv3.equipment.custom;

import fr.karspa.hikerthinkerv3.equipment.base.Equipment;
import fr.karspa.hikerthinkerv3.equipment.base.EquipmentRepository;
import fr.karspa.hikerthinkerv3.equipment.brand.BrandRepository;
import fr.karspa.hikerthinkerv3.equipment.category.CategoryRepository;
import fr.karspa.hikerthinkerv3.statistics.UserEquipmentStatisticsRepository;
import fr.karspa.hikerthinkerv3.user.User;
import fr.karspa.hikerthinkerv3.statistics.UserEquipmentStatistics;
import fr.karspa.hikerthinkerv3.user.UserService;
import fr.karspa.hikerthinkerv3.utils.responses.ResponseModel;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserEquipmentService {

    private UserService userService;
    private EquipmentRepository equipmentRepository;
    private UserEquipmentRepository userEquipmentRepository;
    private CategoryRepository categoryRepository;
    private BrandRepository brandRepository;
    private UserEquipmentStatisticsRepository userEquipmentStatisticsRepository;

    public UserEquipmentService(UserService userService, EquipmentRepository equipmentRepository, UserEquipmentRepository userEquipmentRepository, CategoryRepository categoryRepository, BrandRepository brandRepository, UserEquipmentStatisticsRepository userEquipmentStatisticsRepository) {
        this.userService = userService;
        this.equipmentRepository = equipmentRepository;
        this.userEquipmentRepository = userEquipmentRepository;
        this.categoryRepository = categoryRepository;
        this.brandRepository = brandRepository;
        this.userEquipmentStatisticsRepository = userEquipmentStatisticsRepository;
    }

    public ResponseModel<List<UserEquipmentDTO>> getAllUserEquipments(Long userId) {
        User requester = userService.getUserById(userId);

        if(requester == null){
            return ResponseModel.buildResponse("701","Utilisateur dans le token non trouvé.", null);
        }

        List<UserEquipment> equipments = userEquipmentRepository.findByInventory(requester.getInventory()).orElse(null);

        if(equipments == null){
            return ResponseModel.buildResponse("751","Liste des équipement de cet utilisateur non trouvée ou nulle.", null);
        }

        List<UserEquipmentDTO> equipmentsDTO = equipments.stream().map(UserEquipment::toDTO).toList();

        return ResponseModel.buildResponse("200", "Équipements trouvés avec succès.", equipmentsDTO);
    }


    public ResponseModel<UserEquipmentDTO> getUserEquipment(Long userId, Long equipmentId) {
        User requester = userService.getUserById(userId);

        if(requester == null){ //Peut être utile si une personne obtient un token, supprime son compte, et une requête arrive avec son token. L'id dedans ne sera plus dans son compte.
            return ResponseModel.buildResponse("701","Utilisateur dans le token non trouvé.", null);
        }

        UserEquipment equipment = userEquipmentRepository.findByIdAndInventory(equipmentId, requester.getInventory()).orElse(null);

        if(equipment == null){
            return ResponseModel.buildResponse("752","L'équipement avec cet Id est introuvable pour cet utilisateur.", null);
        }

        return ResponseModel.buildResponse("200", "Équipement trouvé avec succès.", equipment.toDTO());
    }



    public ResponseModel<UserEquipmentDTO> createUserEquipment(Long userId, NewUserEquipmentDTO newUserEquipmentDTO) {
        // Check requester exists
        User requester = userService.getUserById(userId);

        if(requester == null){ //Peut être utile si une personne obtient un token, supprime son compte, et une requête arrive avec son token. L'id dedans ne sera plus dans son compte.
            return ResponseModel.buildResponse("701","Utilisateur dans le token non trouvé.", null);
        }

        //Créer le userEquipment qu'on sauvegardera en BDD. On le remplira en fonction de ce qu'il y dans newUserEquipmentDTO.
        UserEquipment userEquipmentToSave = new UserEquipment();

        // Récup l'équipement attaché :
        Equipment sourceEquipment = equipmentRepository.findByEquipmentId(newUserEquipmentDTO.getLinkedId()).orElse(null);

        if(sourceEquipment == null){
            return ResponseModel.buildResponse("750","Aucun équipement avec cet id trouvé.", null);
        }

        String code = "";
        if(newUserEquipmentDTO.getLinkedId() != 1){
            code = checkAvailables(newUserEquipmentDTO, requester, sourceEquipment);
        }else{
            code = checkNewUserEquipmentWithoutLinkedEquipment(newUserEquipmentDTO, requester);
        }
        // TODO : Détailler l'origine des erreurs !
        if(!code.equals("200")){
            return ResponseModel.buildResponse(code,"Erreur dans le nouvel équipement. Nom déjà pris, categorie ou marque inexistante.", null);
        }

        populateUserEquipment(userEquipmentToSave, newUserEquipmentDTO, sourceEquipment, requester);

        UserEquipment newUserEquipment = saveNewUserEquipment(userEquipmentToSave);

        return ResponseModel.buildResponse("200", "Voici votre nouvel équipement.", newUserEquipment.toDTO());
    }

    @Transactional
    protected UserEquipment saveNewUserEquipment(UserEquipment userEquipment){

        //Save en BDD l'équipement
        UserEquipment savedUserEquipment = userEquipmentRepository.save(userEquipment);

        //Save une nouvelle instance de user_equipment_statistics associé avec cet équipement !
        UserEquipmentStatistics userEquipmentStatistics = new UserEquipmentStatistics();
        userEquipmentStatistics.setUserEquipment(savedUserEquipment);
        UserEquipmentStatistics newStats = userEquipmentStatisticsRepository.save(userEquipmentStatistics);

        savedUserEquipment.setUserEquipmentStatistics(newStats);

        return savedUserEquipment;
    }
    
//    private String checksBeforePersist(NewUserEquipmentDTO newUserEquipmentDTO, User requester, Equipment sourceEquipment){
//
//        if(newUserEquipmentDTO.getLinkedId() == 1){
//            //Si équipement générique
//                // -> Name, description et weight pas blank, blank et égale à 0.
//                // -> Name fourni unique
//                // -> CategoryId et brandId non null et existent
//
//            // Si équipement non générique
//                // -> Name, description et weight écrasés si blank ou 0
//                // -> Name fourni et celui de l'équipement unique
//                // -> CategoryId et brandId osef
//        }
//
//    }


    private String checkNewUserEquipmentWithoutLinkedEquipment(NewUserEquipmentDTO newUserEquipmentDTO, User requester) {

        String basicChecksCode = this.checkAvailables(newUserEquipmentDTO, requester, null);

        if(!basicChecksCode.equals("200")){
            return basicChecksCode;
        }

        boolean isNameValid = !newUserEquipmentDTO.getName().trim().isBlank();
        if(!isNameValid){
            return "903";
        }

        boolean isDescriptionValid = !newUserEquipmentDTO.getDescription().trim().isBlank();
        if(!isDescriptionValid){
            return "904";
        }

        boolean isWeightValid = newUserEquipmentDTO.getWeight() != 0;
        if(!isWeightValid){
            return "905";
        }

        return "200";
    }

    private String checkAvailables(NewUserEquipmentDTO newUserEquipmentDTO, User requester, Equipment linkedEquipment){

        boolean isNameAvailable = this.isNameAvailable(newUserEquipmentDTO.getName().trim(), requester);

        if(linkedEquipment != null && newUserEquipmentDTO.getName().trim().isBlank()){
            isNameAvailable &= this.isNameAvailable(linkedEquipment.getName().trim(), requester);
        }

        if(!isNameAvailable){
            return "900";
        }

        if(newUserEquipmentDTO.getCategoryId() != null){
            boolean isCategoryValid = this.doesCategoryExists(newUserEquipmentDTO.getCategoryId());
            if(!isCategoryValid){
                return "901";
            }
        }

        if(newUserEquipmentDTO.getBrandId() != null){
            boolean isBrandValid = this.doesBrandExists(newUserEquipmentDTO.getBrandId());
            if(!isBrandValid){
                return "902";
            }
        }


        return "200";
    }

    private void populateUserEquipment(UserEquipment userEquipmentToSave, NewUserEquipmentDTO newUserEquipmentDTO, Equipment equipment, User requester) {

        // Affecter les champs non personnalisables
        userEquipmentToSave.setEquipment(equipment);
        userEquipmentToSave.setInventory(requester.getInventory());
        userEquipmentToSave.setArchived(false);

        //Affecter les champs personnalisables
        if(newUserEquipmentDTO.getName().trim().isEmpty()){
            userEquipmentToSave.setName(equipment.getName());
        }else{
            userEquipmentToSave.setName(newUserEquipmentDTO.getName().trim());
        }

        if(newUserEquipmentDTO.getDescription().trim().isEmpty()){
            userEquipmentToSave.setDescription(equipment.getDescription());
        }else{
            userEquipmentToSave.setDescription(newUserEquipmentDTO.getDescription().trim());
        }

        if(newUserEquipmentDTO.getWeight() == 0L){
            userEquipmentToSave.setWeight(equipment.getWeight());
        }else{
            userEquipmentToSave.setWeight(newUserEquipmentDTO.getWeight());
        }

        if(newUserEquipmentDTO.getCategoryId() == null){
            userEquipmentToSave.setCategory(equipment.getCategory());
        }else{
            userEquipmentToSave.setCategory(categoryRepository.findById(newUserEquipmentDTO.getCategoryId()).get());
        }

        if(newUserEquipmentDTO.getBrandId() == null){
            userEquipmentToSave.setBrand(equipment.getBrand());
        }else{
            userEquipmentToSave.setBrand(brandRepository.findById(newUserEquipmentDTO.getBrandId()).get());
        }

        // Pas besoin de retour, on modifie le UserEquipment par référence !
    }

    private boolean isNameAvailable(String name, User requester){
        return userEquipmentRepository.findByNameAndInventory(name, requester.getInventory()).isEmpty();
    }
    private boolean doesCategoryExists(Long categoryId){
        return categoryRepository.findById(categoryId).isPresent();
    }
    private boolean doesBrandExists(Long brandId){
        return brandRepository.findById(brandId).isPresent();
    }

}
