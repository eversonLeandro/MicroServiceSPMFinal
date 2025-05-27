package co.edu.unicauca.apigateway;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.converter.Converter;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.ReactiveJwtAuthenticationConverterAdapter;
import org.springframework.security.web.server.SecurityWebFilterChain;
import reactor.core.publisher.Mono;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        .pathMatchers("/api/usuarios/", "/actuator/health").permitAll()
                        .pathMatchers("/actuator/health").permitAll()
                        .pathMatchers("/api/Students/**").hasRole("STUDENT")
                        .pathMatchers("/apiCompanies/**").hasRole("COMPANY")
                        .pathMatchers("/apiProject/**").hasRole("COORDINATOR")
                        .pathMatchers("/postulaciones/**").hasRole("STUDENT")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );
        //http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    private Converter<Jwt, Mono<AbstractAuthenticationToken>> jwtAuthenticationConverter() {
        JwtAuthenticationConverter jwtConverter = new JwtAuthenticationConverter();
        jwtConverter.setJwtGrantedAuthoritiesConverter(new KeycloakRoleConverter());
        return new ReactiveJwtAuthenticationConverterAdapter(jwtConverter);
    }

    public static class KeycloakRoleConverter implements Converter<Jwt, Collection<GrantedAuthority>> {
        @Override
        public Collection<GrantedAuthority> convert(Jwt jwt) {
            Map<String, Object> resourceAccess = jwt.getClaim("resource_access");

            if (resourceAccess == null) {
                return Collections.emptyList();
            }

            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get("system-desktop");

            if (clientRoles == null) {
                return Collections.emptyList();
            }

            Collection<String> roles = (Collection<String>) clientRoles.get("roles");

            return roles != null ?
                    roles.stream()
                            .map(role -> "ROLE_" + role.toUpperCase())
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList())
                    : Collections.emptyList();
        }
    }
}