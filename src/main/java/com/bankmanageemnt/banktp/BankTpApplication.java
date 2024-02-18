package com.bankmanageemnt.banktp;
import com.bankmanageemnt.banktp.dao.CompteCourantDao;
import com.bankmanageemnt.banktp.dao.CompteEpargneDao;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.model.CompteCourant;
import com.bankmanageemnt.banktp.model.CompteEpargne;
import com.bankmanageemnt.banktp.services.ClientServiceImpl;
import com.bankmanageemnt.banktp.services.CompteEpargneImpl;
import org.hibernate.annotations.Bag;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import springfox.documentation.swagger2.annotations.EnableSwagger2WebMvc;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.UUID;
import java.util.stream.Stream;


@SpringBootApplication
public class BankTpApplication {
    public static void main(String[] args) {
        SpringApplication.run(BankTpApplication.class,args);
    }
    CommandLineRunner addCompteEpargne(CompteEpargneDao compteEpargneDao){
        return args ->{
            for (int i = 0; i < 10; i++) {
                CompteEpargne compteEpargne=new CompteEpargne();
                compteEpargne.setTaux(Math.random()*899+50);
                compteEpargne.setNumCompte(UUID.randomUUID().toString());
                compteEpargne.setSolde(Double.valueOf((int)Math.random()*50000+2000+i*10));
                compteEpargne.setCreationDate(new Date());
                compteEpargneDao.save(compteEpargne);
            }
        };
    }
    CommandLineRunner addCompteCourant(CompteCourantDao compteCourantDao){
        return args ->{
            for (int i = 0;i<10;i++) {
                CompteCourant compteCourant=new CompteCourant();
                compteCourant.setDecouvert(0d);
                compteCourant.setNumCompte(UUID.randomUUID().toString());
                compteCourant.setSolde(Double.valueOf(
                        (int)Math.random()*50000+2000+i*10));
                compteCourant.setCreationDate(new Date());
                compteCourantDao.save(compteCourant);
            }
        };
    }
    CommandLineRunner start(ClientServiceImpl clientService){
        return args -> {

            Stream.of("Hamed","Toe","Ali","Jean","paul",
                            "Mars","Yousfi","Mahamad","Abdoul","Kader",
                            "Anice","Aida","Osias","Parfait")
                    .forEach(name->{
                        Client client=new Client();
                        client.setCode(UUID.randomUUID().toString());
                        client.setName(name);
                        clientService.addClient(client);
                    });
        };
    }
}
