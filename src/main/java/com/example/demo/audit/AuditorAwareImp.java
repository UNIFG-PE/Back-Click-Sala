package com.example.demo.audit;

import com.example.demo.entities.User;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

import java.security.Principal;
import java.util.Optional;

@Configuration
public class AuditorAwareImp implements AuditorAware<String> {

    @Override
    public Optional<String> getCurrentAuditor() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        if(!(auth.isAuthenticated()) || auth.getPrincipal().equals("anonymousUser")){
            return Optional.of("guestUser");
        }
        return Optional.of(auth.getName());
    }
}
