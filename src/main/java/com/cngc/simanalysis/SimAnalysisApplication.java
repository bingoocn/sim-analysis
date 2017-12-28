package com.cngc.simanalysis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

/**
 * 
 * @author kyler
 *
 */
@SpringBootApplication
@EnableAsync
public class SimAnalysisApplication {

    public static void main(String[] args) {
        SpringApplication.run(SimAnalysisApplication.class, args);
    }
}
