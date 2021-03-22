package gameStore.store.config;

import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
public class ModelMapperConfig {

    private ModelMapper modelMapper;

    @Autowired
    public ModelMapperConfig(ModelMapper modelMapper) {
        this.modelMapper = modelMapper;
        this.initialize();
    }

    private void initialize() {
        this.userRegisterMapping();
    }

    private void userRegisterMapping() {

        // TODO: 3/22/2021 Fix mappings 
        TypeMap<UserRegisterBindingModel, User> typeMap = modelMapper.getTypeMap(UserRegisterBindingModel.class, User.class);
        if (typeMap != null) { // if not  already added
            modelMapper.getTypeMap(UserRegisterBindingModel.class, User.class)
                    .addMappings(mapper -> {
                        mapper.map(UserRegisterBindingModel::getEmail, User::setEmail);
                        mapper.map(UserRegisterBindingModel::getPassword, User::setPassword);
                        mapper.map(UserRegisterBindingModel::getFirstName, User::setFirstName);
                        mapper.map(UserRegisterBindingModel::getLastName, User::setLastName);
                    });
        } else {
            modelMapper.createTypeMap(UserRegisterBindingModel.class, User.class)
                    .addMappings(mapper -> {
                        mapper.map(UserRegisterBindingModel::getEmail, User::setEmail);
                        mapper.map(UserRegisterBindingModel::getPassword, User::setPassword);
                        mapper.map(UserRegisterBindingModel::getFirstName, User::setFirstName);
                        mapper.map(UserRegisterBindingModel::getLastName, User::setLastName);
                    });
        }
    }
}
