package gameStore.store.services.interfaces;

public interface UserService {

    void registerUser(String email, String password, String confirmPassword, String firstName, String lastName);
}