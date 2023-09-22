package com.luxfacta.planetshoes;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;


@SpringBootApplication
@EnableScheduling
public class PlanetShoes {

    public static void main(String[] args) {
    	
    	
    	
        SpringApplication.run(PlanetShoes.class, args);
    }
}
