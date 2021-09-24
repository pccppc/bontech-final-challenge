package org.bontech.finalproject;

import org.bontech.finalproject.model.ScheduledSense;
import org.bontech.finalproject.repositories.ScheduledSenseRepository;
import org.bontech.finalproject.service.ScheduledTask;
import org.bontech.finalproject.service.base.SensorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalTime;
import java.time.temporal.TemporalField;
import java.util.List;
import java.util.Timer;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
@EnableJpaAuditing
public class FinalApplication {

    private @Autowired ScheduledSenseRepository repository;
    private @Autowired SensorService service;

    public static void main(String[] args) {
        SpringApplication.run(FinalApplication.class, args);
    }

    @Bean
    CommandLineRunner run(){
        List<ScheduledSense> all = repository.findAll();
        Timer timer = new Timer();
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(5);
        return args -> {
            for (ScheduledSense s : all) {
                long initialDelay;
                if (LocalTime.now().isAfter(s.getPeriod())){
                    initialDelay = (86400000000000L - LocalTime.now().toNanoOfDay()) + s.getPeriod().toNanoOfDay();
                }else{
                    initialDelay = s.getPeriod().toNanoOfDay() - LocalTime.now().toNanoOfDay();
                }
                Long period = 86400000000000L; // 24 hour
                scheduler.scheduleAtFixedRate(new ScheduledTask(service,s.getSensor().getId()),
                    initialDelay,
                    period,
                    TimeUnit.NANOSECONDS
                );
            };
        };
    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

}
