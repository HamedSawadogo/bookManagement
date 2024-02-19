package com.bankmanageemnt.banktp.config;
import com.bankmanageemnt.banktp.model.Client;
import com.bankmanageemnt.banktp.services.ClientServiceImpl;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
public class ClientDetailsService implements UserDetailsService {
    private final ClientServiceImpl clientService;

    public ClientDetailsService(ClientServiceImpl clientService) {
        this.clientService = clientService;
    }
    /**
     * Charger le user en base de donnée
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client=this.clientService.findCLientByName(username);
        return new User(client.getUsername(),client.getPassword(),client.getAuthorities());
    }

}
