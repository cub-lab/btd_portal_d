package ro.cub.btddigitalportals.service;

import com.nimbusds.jose.util.Base64;
import io.jmix.core.UnconstrainedDataManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.cub.btddigitalportals.entity.*;

import java.util.Optional;

@Component
public class ClientRegistrationService  {

    private final UnconstrainedDataManager unconstrainedDataManager;
    private final PasswordEncoder passwordEncoder;

    public ClientRegistrationService(UnconstrainedDataManager unconstrainedDataManager, PasswordEncoder passwordEncoder) {
        this.unconstrainedDataManager = unconstrainedDataManager;
        this.passwordEncoder = passwordEncoder;
    }

    public boolean emailAlreadyRegistered(String email) {
        Optional<User> optional = unconstrainedDataManager.load(User.class)
                .query("select c from cub_User c where c.email = :email")
                .parameter("email", email)
                .optional();

        return optional.isPresent();
    }

    public String registerNewClient(String providedEmail, String providedPassword, Client clientToRegister) {
        String randomString = RandomStringUtils.randomAlphabetic(10, 40);
        User newUser = unconstrainedDataManager.create(User.class);
        newUser.setUsername(providedEmail);
        newUser.setPassword(passwordEncoder.encode(providedPassword));
        switch (clientToRegister.getClientType()) {
            case INDIVIDUAL -> {
                newUser.setFirstName(clientToRegister.getIndividualClient().getFirstName());
                newUser.setLastName(clientToRegister.getIndividualClient().getLastName());
            }
            case LEGAL_ENTITY -> {
                newUser.setFirstName(clientToRegister.getLegalClient().getContactFirstName());
                newUser.setLastName(clientToRegister.getLegalClient().getContactLastName());
            }
        }
        newUser.setClient(clientToRegister);
        newUser.setActivationCode(randomString);
        newUser.setActive(false);

        unconstrainedDataManager.save(newUser);

        return randomString;
    }
}
