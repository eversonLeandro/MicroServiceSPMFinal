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
import java.util.Map;
import java.util.stream.Collectors;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

    @Bean
    public SecurityWebFilterChain securityWebFilterChain(ServerHttpSecurity http) {
        http
                .authorizeExchange(exchanges -> exchanges
                        // Endpoints públicos específicos
                        .pathMatchers("/api/usuarios/validar", "/actuator/health").permitAll()
                        .pathMatchers("/apiCompanies/guardar", "/api/Students/crear").permitAll()

                        // Otros endpoints de usuarios SÍ requieren autenticación
                        .pathMatchers("/api/usuarios/crear", "/api/usuarios/actualizar").authenticated()

                        // El resto de rutas protegidas
                        .pathMatchers("/api/Students/**").hasAnyRole("STUDENT","COORDINATOR")
                        .pathMatchers("/apiCompanies/**").hasAnyRole("COMPANY","STUDENT","COORDINATOR")
                        .pathMatchers("/apiProject/**").hasAnyRole("COORDINATOR","STUDENT","COMPANY")
                        .pathMatchers("/postulaciones/**").hasRole("STUDENT")
                        .anyExchange().authenticated()
                )
                .oauth2ResourceServer(oauth2 -> oauth2
                        .jwt(jwt -> jwt
                                .jwtAuthenticationConverter(jwtAuthenticationConverter())
                        )
                );

        http.csrf(csrf -> csrf.disable());

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
            Map<String, Object> clientRoles = (Map<String, Object>) resourceAccess.get("system-desktop");
            Collection<String> roles = (Collection<String>) clientRoles.get("roles");

            return roles.stream()
                    .map(role ->"ROLE_"+role.toUpperCase())
                    .map(SimpleGrantedAuthority::new)
                    .collect(Collectors.toList());
        }
    }
}