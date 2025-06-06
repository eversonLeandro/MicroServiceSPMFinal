package co.edu.unicauca.studentmicroservice.Infra.Config;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.web.SecurityFilterChain;

import java.util.Collection;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/actuator/health").permitAll()
                        .requestMatchers("/api/Students/crear").permitAll()
                        .requestMatchers("/api/Students/**").hasAnyRole("STUDENT","COORDINATOR") // o hasAuthority("ROLE_STUDENT")
                        .anyRequest().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter()) // ¡Paréntesis cerrados correctamente!
                        )
                )
                .csrf(csrf -> csrf.disable()); // Deshabilitar CSRF (opcional para APIs)
        return http.build();
    }
    // Bean para decodificar JWT con Keycloak
    @Bean
    public JwtDecoder jwtDecoder() {
        return NimbusJwtDecoder.withJwkSetUri("http://localhost:8080/realms/MicroserviceSPM/protocol/openid-connect/certs").build();
    }
    // KeycloakRoleConverter (igual para todos)
    public static class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");
            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get("system-desktop");
            Collection<String> roles = (Collection<String>) clientRoles.get("roles");
            return roles.stream()
                    .map(role ->"ROLE_"+role.toUpperCase())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }
    private JwtAuthenticationConverter jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter()); // ¡Inyección!
        return jwtConverter;
    }
}
