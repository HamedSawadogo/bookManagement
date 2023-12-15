package com.bookmanagement.gestionlivres;

import com.bookmanagement.gestionlivres.Dtos.UserDto;
import com.bookmanagement.gestionlivres.enums.UserRole;
import com.bookmanagement.gestionlivres.exceptions.UserNotFoundException;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.model.Compte;
import com.bookmanagement.gestionlivres.model.ComteOrangeMoney;
import com.bookmanagement.gestionlivres.model.User;
import com.bookmanagement.gestionlivres.repositories.BookRepository;
import com.bookmanagement.gestionlivres.service.CompteServiceImpl;
import com.bookmanagement.gestionlivres.service.UserServiceImpl;
import jakarta.transaction.Transactional;
import lombok.SneakyThrows;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@SpringBootApplication
public class GestionLivresApplication implements  CommandLineRunner{

    @Autowired
    private CompteServiceImpl compteService;


    public static void main(String[] args) {
        SpringApplication.run(GestionLivresApplication.class, args);
    }
    @Bean
    ModelMapper modelMapper(){
        return  new ModelMapper();
    }
    @Override
    public void run(String... args) throws Exception {
        try {
            this.compteService.addAcountToUsser("102cf334-5813-4df4-949d-0b1d8ccd361e","75a64317-75d1-4618-ae29-d12abbacd8d0");
        } catch (UserNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

}
