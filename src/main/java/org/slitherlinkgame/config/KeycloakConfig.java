package org.slitherlinkgame.config;

import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

public class KeycloakConfig {
    @Value("{keycloak.server-url}")
    private String serverUrl;

    @Value("{admin.password}")
    private String password;
    @Value("{admin.username}")
    private String username;
    @Value("{admin.client-id}")
    private String clientId;


    @Bean
    public Keycloak keycloak() {
        return KeycloakBuilder.builder().serverUrl(serverUrl).realm("master").clientId(clientId).username(username).password(password).build();
    }
}
