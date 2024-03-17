package ro.cub.btddigitalportals.service;

import io.jmix.core.UnconstrainedDataManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import ro.cub.btddigitalportals.entity.ClientExtUser;
import ro.cub.btddigitalportals.entity.ClientType;
import ro.cub.btddigitalportals.entity.CommunicationChannel;

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
        Optional<ClientExtUser> optional = unconstrainedDataManager.load(ClientExtUser.class)
                .query("select c from cub_ClientExtUser c where c.email = :email")
                .parameter("email", email)
                .optional();

        return optional.isPresent();
    }

    public String registerNewUser(ClientType clientType,
                                  String firstName,
                                  String lastName,
                                  String email,
                                  String password,
                                  String phoneNumber,
                                  Boolean acceptedTermsAndConditions,
                                  CommunicationChannel communicationChannel) {
        String randomString = RandomStringUtils.randomAlphabetic(10, 40);
        ClientExtUser clientExtUser = unconstrainedDataManager.create(ClientExtUser.class);
        clientExtUser.setType(clientType);
        clientExtUser.setFirstName(firstName);
        clientExtUser.setLastName(lastName);
        clientExtUser.setEmail(email);
        clientExtUser.setUsername(email);
        clientExtUser.setPassword(passwordEncoder.encode(password));
        clientExtUser.setPhone(phoneNumber);
        clientExtUser.setPreferredCommunicationChannel(communicationChannel);
        clientExtUser.setActive(false);
        clientExtUser.setActivationCode(Base64.encodeBase64String(randomString.getBytes()));
        // save new user to the database
        unconstrainedDataManager.save(clientExtUser);

        return clientExtUser.getActivationCode();
    }
}
