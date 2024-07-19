//package com.example.project.security.service;
//
//import com.example.project.security.entities.AppRole;
//import com.example.project.security.entities.AppUser;
//import com.example.project.security.repo.AppRoleRepostery;
//import com.example.project.security.repo.AppUserRepostery;
//import jakarta.transaction.Transactional;
//import lombok.AllArgsConstructor;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Service;
//
//import java.util.UUID;
//
//@Service
//@Transactional
//@AllArgsConstructor
//public class AccountServiceImpl  implements   AccountService{
//    private AppUserRepostery appUserRepostery;
//    private AppRoleRepostery appRoleRepository;
//
//
//
//    @Override
//    public AppUser addNewUser(String username, String password, String email, String confirmPassword) {
//        AppUser appUser=appUserRepostery.findByUsername(username);
//        if(appUser!=null) throw new RuntimeException("this user already exist !");
//        if(!password.equals(confirmPassword)) throw new RuntimeException("user not found !");
//
//        AppUser user=AppUser.builder()
//                .userId(UUID.randomUUID().toString())
//                .username(username)
//                .email(email)
//                .password(password)
//                .build();
//
//        return appUserRepostery.save(user);
//    }
//
//    @Override
//    public AppRole addNewRole(String role) {
//        AppRole appRole=appRoleRepository.findById(role).orElse(null);
//        if(appRole!=null) throw new RuntimeException("This role already exist !");
//        appRole=AppRole.builder().role(role).build();
//        return appRoleRepository.save(appRole);
//    }
//
//    @Override
//    public void addRoleToUser(String username, String role) {
//        AppUser appUser=appUserRepostery.findByUsername(username);
//        AppRole appRole=appRoleRepository.findByRole(role);
//
//        if(appRole ==null || appUser==null) throw new RuntimeException("Role or use not found !");
//        appUser.getRoles().add(appRole);
//
//    }
//
//    @Override
//    public void removeRoleFromUser(String username, String role) {
//        AppUser appUser=appUserRepostery.findByUsername(username);
//        AppRole appRole=appRoleRepository.findByRole(role);
//
//        if(appRole ==null || appUser==null) throw new RuntimeException("Role or use not found !");
//
//        appUser.getRoles().remove(appRole);
//    }
//
//    @Override
//    public AppUser loadUserByUsername(String username) {
//        return null;
//    }
//}