package co.edu.unicauca.microservicelogin.service;

import jakarta.ws.rs.core.Response;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.RoleRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.keycloak.representations.idm.ClientRepresentation;
import java.util.List;



@Service
public class KeycloakUserService {

    @Value("${keycloak.auth-server-url}")
    private String serverUrl;

    @Value("${keycloak.realm}")
    private String realm;

    @Value("${keycloak.resource}")
    private String clientId;

    @Value("${keycloak.credentials.secret}")
    private String clientSecret;

    private Keycloak getInstance() {
        return KeycloakBuilder.builder()
                .serverUrl(serverUrl)
                .realm(realm) //
                .clientId(clientId)
                .clientSecret(clientSecret)
                .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
                .build();
    }

    public void crearUsuarioEnKeycloak(String username, String password, String email, String rol) {
        Keycloak keycloak = getInstance();

        //  Verificar si el usuario ya existe en Keycloak
        List<UserRepresentation> existingUsers = keycloak.realm(realm).users().search(username);
        if (!existingUsers.isEmpty()) {
            System.out.println(" Usuario ya existe en Keycloak: " + username);
            return; // Evitar volver a crearlo
        }

        // Crear credenciales
        CredentialRepresentation credential = new CredentialRepresentation();
        credential.setType(CredentialRepresentation.PASSWORD);
        credential.setValue(password);
        credential.setTemporary(false);

        // Crear representación del usuario
        UserRepresentation user = new UserRepresentation();
        user.setUsername(username);
        user.setEmail(email);
        user.setFirstName(username);
        user.setLastName(username);
        user.setEmailVerified(true);
        user.setEnabled(true);
        user.setCredentials(List.of(credential));

        //  Intentar crear el usuario
        Response response = keycloak.realm(realm).users().create(user);

        if (response.getStatus() == 201) {
            String location = response.getHeaderString("Location");
            String userId = location.substring(location.lastIndexOf("/") + 1);

            // Obtener el ID interno del cliente 'system-desktop'
            ClientRepresentation clientRep = keycloak.realm(realm)
                    .clients()
                    .findByClientId("system-desktop")
                    .stream()
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Cliente 'system-desktop' no encontrado"));

            String clientUUID = clientRep.getId();

            //  Obtener el rol deseado
            RoleRepresentation clientRole = keycloak.realm(realm)
                    .clients()
                    .get(clientUUID)
                    .roles()
                    .get(rol)
                    .toRepresentation();

            //  Asignar el rol al usuario
            keycloak.realm(realm)
                    .users()
                    .get(userId)
                    .roles()
                    .clientLevel(clientUUID)
                    .add(List.of(clientRole));

        } else {
            throw new RuntimeException("Error al crear usuario en Keycloak: " + response.getStatus());
        }
    }
}
