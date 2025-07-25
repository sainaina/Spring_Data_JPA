package com.example.spring_jpa.init;

import com.example.spring_jpa.domain.Role;
import com.example.spring_jpa.domain.User;
import com.example.spring_jpa.repository.RoleRepository;
import com.example.spring_jpa.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
@RequiredArgsConstructor
public class SecurityInitialize {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @PostConstruct
    public void init(){
        if (roleRepository.count() == 0) {
            Role roleUser = new Role();
            roleUser.setName("USER");

            Role roleAdmin = new Role();
            roleAdmin.setName("ADMIN");

            Role roleStaff = new Role();
            roleStaff.setName("STAFF");

            Role roleCustomer = new Role();
            roleCustomer.setName("CUSTOMER");

            roleRepository.saveAll(List.of(roleUser, roleAdmin, roleStaff, roleCustomer));
            roleRepository.flush();

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin@pass"));
            admin.setIsEnabled(true);
            admin.setRoles(Set.of(roleUser, roleAdmin));

            User staff = new User();
            staff.setUsername("staff");
            staff.setPassword(passwordEncoder.encode("staff@pass"));
            staff.setIsEnabled(true);
            staff.setRoles(Set.of(roleUser, roleStaff));

            User customer = new User();
            customer.setUsername("customer");
            customer.setPassword(passwordEncoder.encode("customer@pass"));
            customer.setIsEnabled(true);
            customer.setRoles(Set.of(roleUser, roleCustomer));

            userRepository.saveAll(List.of(admin, staff, customer));
        }
    }

}