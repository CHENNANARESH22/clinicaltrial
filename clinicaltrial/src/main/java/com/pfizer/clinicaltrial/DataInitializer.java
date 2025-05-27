package com.pfizer.clinicaltrial;

import com.pfizer.clinicaltrial.model.Role;
import com.pfizer.clinicaltrial.model.User;
import com.pfizer.clinicaltrial.repository.RoleRepository;
import com.pfizer.clinicaltrial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {
        System.out.println("🔥 DataInitializer running...");

        // Step 1: Create ROLE_ADMIN if not exists
        Role adminRole = roleRepository.findByName("ROLE_ADMIN");
        if (adminRole == null) {
            adminRole = new Role();
            adminRole.setName("ROLE_ADMIN");
            roleRepository.save(adminRole);
            System.out.println("✅ Created ROLE_ADMIN");
        } else {
            System.out.println("ℹ️ ROLE_ADMIN already exists");
        }

        // Step 2: Create admin user if not exists
        if (userRepository.findByUsername("admin").isEmpty()) {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .roles(Collections.singleton(adminRole))
                    .build();

            userRepository.save(admin);
            System.out.println("✅ Admin user created: admin / admin123");
        } else {
            System.out.println("ℹ️ Admin user already exists");
        }
    }
}