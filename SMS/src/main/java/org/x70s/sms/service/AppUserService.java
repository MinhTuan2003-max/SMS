package org.x70s.sms.service;

import org.x70s.sms.entity.AppUser;
import org.x70s.sms.repository.AppUserDAO;

import java.util.Optional;

public class AppUserService {
    private final AppUserDAO appUserDAO;

    public AppUserService() {
        this.appUserDAO = new AppUserDAO();
    }

    public AppUser loadUser(String username, String password) {
        Optional<AppUser> opt = appUserDAO.findByUserAndPassword(username, password);
        if (opt.isPresent()) {
            AppUser appUser = opt.get();
            appUser.setPassword(null);
            appUser.setPhone(null);
            return appUser;
        } else {
            return null;
        }
    }
}
