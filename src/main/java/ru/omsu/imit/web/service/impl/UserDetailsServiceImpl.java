package ru.omsu.imit.web.service.impl;

import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ru.omsu.imit.web.model.AppUser;
import ru.omsu.imit.web.repository.AppRoleRepository;
import ru.omsu.imit.web.repository.AppUserRepository;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserDetailsServiceImpl implements UserDetailsService {
    AppUserRepository appUserRepository;
    AppRoleRepository appRoleRepository;

    public UserDetailsServiceImpl(AppUserRepository appUserRepository,
                                  AppRoleRepository appRoleRepository) {
        this.appUserRepository = appUserRepository;
        this.appRoleRepository = appRoleRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        AppUser appUser = appUserRepository.findAppUserByUserName(userName);

        if (appUser == null) {
            log.error("User {} not found!", userName);
            throw new UsernameNotFoundException(String.format("User %s was not found in the database", userName));
        }
        log.debug("Found User {}", appUser);

        List<GrantedAuthority> grantList = new ArrayList<>();
        // [ROLE_USER, ROLE_ADMIN,..]
        appRoleRepository.findRoleNamesByUserId(appUser.getUserId())
                         .forEach(roleName -> grantList.add(new SimpleGrantedAuthority(roleName)));

        return new User(appUser.getUserName(), appUser.getEncryptedPassword(), grantList);
    }
}
