package com.university.ilock;

import com.university.ilock.service.*;
import lombok.extern.slf4j.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.*;

@SpringBootApplication
@Slf4j
public class ILock {
    @Autowired
    public PINService pinService;

    public static void main(String[] args) {
        SpringApplication.run(ILock.class, args);
    }


}
