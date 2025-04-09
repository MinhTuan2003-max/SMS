package org.x70s.sms.utils;

import lombok.Getter;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.x70s.sms.entity.AppUser;
import org.x70s.sms.entity.Authority;
import org.x70s.sms.repository.AppUserDAO;
import org.x70s.sms.repository.AuthorityDAO;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class SmsHibernateUtils {
    @Getter
    public static final SessionFactory sessionFactory;

    static {
        sessionFactory = buildSessionFactory();
    }

    private static void loadData() {
        AuthorityDAO authorityDAO = new AuthorityDAO();
        AppUserDAO appUserDAO = new AppUserDAO();
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_ADMIN", "Administrator"));
        authorities.add(new Authority("ROLE_USER", "User"));
        authorities.add(new Authority("ROLE_STUDENT", "Student"));
        authorityDAO.saveAll(authorities);
        List<AppUser> appUsers = new ArrayList<>();
        AppUser appAdmin = AppUser.builder()
                .id(null)
                .username("admin")
                .password("123456789")
                .email("admin@gmail.com")
                .phone("0367133632")
                .authorities(List.of(new Authority("ROLE_ADMIN", "Administrator")))
                .build();
        AppUser appUser = AppUser.builder()
                .id(null)
                .username("user")
                .password("123456789")
                .email("user@gmail.com")
                .phone("0978296904")
                .authorities(List.of(new Authority("ROLE_USER", "User")))
                .build();
        AppUser appStudent = AppUser.builder()
                .id(null)
                .username("student")
                .password("123456789")
                .email("student@gmail.com")
                .phone("0986703022")
                .authorities(List.of(new Authority("ROLE_STUDENT", "Student")))
                .build();
        appUsers.add(appAdmin);
        appUsers.add(appUser);
        appUsers.add(appStudent);
        appUserDAO.saveAll(appUsers);
    }

    private static SessionFactory buildSessionFactory() {
        try {
            URL url = SmsHibernateUtils.class.getClassLoader().getResource("config/sms-hibernate.cfg.xml");
            return new Configuration().configure(url).buildSessionFactory();
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
}
