package com.bankmanageemnt.banktp.config;
import com.bankmanageemnt.banktp.dao.ClientDao;
import com.bankmanageemnt.banktp.model.Client;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
@Service
@AllArgsConstructor
public class ClientService implements UserDetailsService {
    private final ClientDao clientDao;
    /**
     * Charger le user en base de donnée
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client=this.clientDao.findClientByName(username);
        if(client==null)throw new EntityNotFoundException("this client is not found");
        return new User(client.getUsername(),client.getPassword(),client.getAuthorities());
    }
}
