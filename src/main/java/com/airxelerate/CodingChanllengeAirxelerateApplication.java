package com.airxelerate;

import com.airxelerate.dto.request.UserCreateRequest;
import com.airxelerate.entity.Role;
import com.airxelerate.entity.User;
import com.airxelerate.repository.UserRepository;
import com.airxelerate.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Set;

@RequiredArgsConstructor
@SpringBootApplication
public class CodingChanllengeAirxelerateApplication implements CommandLineRunner {
    private final UserService userService ;

    public static void main(String[] args) {
        SpringApplication.run(CodingChanllengeAirxelerateApplication.class, args);
    }


    @Override
    public void run(String... args) throws Exception {
        userService.create(new UserCreateRequest(
                "admin admin",
                "admin",
                Set.of(Role.USER, Role.ADMIN),
                "admin"
        ));
    }
}
