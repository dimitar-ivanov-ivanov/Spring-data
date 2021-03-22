package gameStore.store.config;

import gameStore.store.models.entity.User;
import gameStore.store.models.dto.UserRegisterBindingModel;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Configuration
public class ModelMapperConfig {

    @Autowired
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
        this.modelMapper.createTypeMap(UserRegisterBindingModel.class, User.class)
                .addMappings(mapper -> {
                    mapper.map(UserRegisterBindingModel::getEmail, User::setEmail);
                    mapper.map(UserRegisterBindingModel::getFirstName, User::setFirstName);
                    mapper.map(UserRegisterBindingModel::getLastName, User::setLastName);
                });
    }
}
