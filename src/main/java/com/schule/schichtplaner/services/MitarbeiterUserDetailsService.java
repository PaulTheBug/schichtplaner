package com.schule.schichtplaner.services;

import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.schule.schichtplaner.repositories.MitarbeiterRepository;

@Service
public class MitarbeiterUserDetailsService implements UserDetailsService {

    private final MitarbeiterRepository mitarbeiterRepository;

    public MitarbeiterUserDetailsService(
            MitarbeiterRepository mitarbeiterRepository) {
        this.mitarbeiterRepository = mitarbeiterRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        return mitarbeiterRepository.findByBenutzername(username)
                .map(mitarbeiter -> {
                    if (mitarbeiter.getPermission() == null
                            || mitarbeiter.getPermission().getName() == null
                            || mitarbeiter.getPermission().getName().isBlank()) {
                        throw new UsernameNotFoundException(
                                "Keine Permission für Benutzer: " + username);
                    }

                    String role = mitarbeiter.getPermission().getName().trim();
                    if (role.startsWith("ROLE_")) {
                        role = role.substring("ROLE_".length());
                    }

                    return User.withUsername(mitarbeiter.getBenutzername())
                            .password(mitarbeiter.getPasswort())
                            .roles(role)
                            .build();
                })
                .orElseThrow(() -> new UsernameNotFoundException(
                        "Mitarbeiter nicht gefunden: " + username));
    }
}