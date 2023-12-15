package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.Dtos.UserDto;
import com.bookmanagement.gestionlivres.controller.BookController;
import com.bookmanagement.gestionlivres.enums.TypeCompte;
import com.bookmanagement.gestionlivres.enums.UserRole;
import com.bookmanagement.gestionlivres.exceptions.*;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.model.Compte;
import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.model.User;
import com.bookmanagement.gestionlivres.repositories.BookRepository;
import com.bookmanagement.gestionlivres.repositories.ReservationDao;
import com.bookmanagement.gestionlivres.repositories.UserDao;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class UserServiceImpl implements UserService {


     ModelMapper modelMapper;
    //
    private final UserDao userDao;
    private  final BookServiceImpl bookService;
    private ReservationDao reservationDao;
    private ReservationServiceImpl reservationService;
    private CompteServiceImpl compteService;

    public  UserServiceImpl(UserDao userDao, BookServiceImpl bookService,
        ReservationDao reservationDao,
        ReservationServiceImpl reservationService,
        ModelMapper modelMapper,CompteServiceImpl compteService){

        this.userDao=userDao;
        this.bookService=bookService;
        this.reservationDao=reservationDao;
        this.reservationService=reservationService;
        this.modelMapper=modelMapper;
        this.compteService=compteService;
    }
    /**
     * Ajouter un utilisateur dans la base de donnée
     * @param user
     * @return
     * @throws UserNotFoundException
     */
    @Override
    public UserDto addUser(UserDto userDto) throws UserNotFoundException {
        if(Objects.isNull(userDto))throw new UserNotFoundException("ce utilisateur est invalide");

        User user=this.convertDtoToEntitie(userDto);

        user.setId(UUID.randomUUID().toString());
        user.setRole(UserRole.valueOf(userDto.getRole().toString()));
        user.setUsername(userDto.getUsername());
        user.setPassword(userDto.getPassword());

        return  this.convertEntitieToDto(this.userDao.save(user));
    }
    /**
     * Supprimer un utilisateur de la base de donnée
     * @param userId
     */
    @Override
    public void deleteUser(String userId) {
      this.userDao.deleteById(userId);
    }

    /**
     * Modifier un utilisateur dans la base de donnée
     * @param user
     * @param userId
     * @return
     */
    @Override
    public UserDto updateUser(UserDto userDto, String userId) {
        Optional<User>optionalUser=this.userDao.findById(userId);
        if(optionalUser.isPresent()) {
            User user=modelMapper.map(userDao,User.class);
            return this.convertEntitieToDto(this.userDao.save(user));
        }
        throw new EntityNotFoundException();
    }
    /**
     * Renvoie la liste des Utilisateurs de la base de donnée
     * @return
     */
    @Override
    public Iterable<UserDto> getAllUsers() {
        return this.userDao.findAll()
                .stream()
                .map(this::convertEntitieToDto)
                .collect(Collectors.toList());
    }
    /**
     * Rechercher un utilisateur de la base de donnée
     * @param userid
     * @return
     */
    @Override
    public UserDto findUserById(String userid) throws UserNotFoundException {
        return  this.convertEntitieToDto(this.userDao.findById(userid)
                .orElseThrow(()->new UserNotFoundException("Ce utilisateurn est introuvable")));
    }

    @Override
    public UserDto addReservation(ReservationDto reservation,String compteId, Long bookId, String userid) throws ReservationNotFoundException, InvalidBookException, UserNotFoundException, MontantInsufisantException {

            UserDto userDto=this.findUserById(userid);
            User user =this.convertDtoToEntitie(userDto);
            /**
             * On recupère la liste des reservations de l'Utilisateur
             */
            List<Reservation> reservations = user.getReservations();

            Reservation newReservation =this.reservationService.convertDtoToEntitie(this.reservationService.addReservation(reservation, bookId));

            //Le Livre de la Reservation
            Book reservationBook = newReservation.getBook();

            //Le prix total de la Reservation
            Double totalCost=newReservation.getNbLivre()*reservationBook.getPrice();

             //Recuperer le Compte de Paiement de la Reservation
             Compte userAcount=this.compteService.findCompteById(compteId);

             //Le reste de Livre disponibles
             int reste = reservationBook.getQuantite() - reservation.getNbLivre();
             if(reste<=0)throw new MontantInsufisantException("Votre solde est insuffisant veillez Recharger");

             Double resteMontCompte=userAcount.getMontant()-totalCost;

             reservationBook.setDisponible(reste>0);

             if(resteMontCompte>=0)
             {
                 //Modifier le Montant du Compte de Reservation
                 userAcount.setMontant(resteMontCompte);
                 this.compteService.addAcound(userAcount);

                 reservationBook.setQuantite(reste);
                 Book book = bookService.addBook(reservationBook);

                 //ajouter la nouvelle reservation a  la liste des reservations existantes
                 reservations.add(newReservation);
                 user.setReservations(reservations);

                 this.bookService.addBook(reservationBook);
                 return this.convertEntitieToDto(this.userDao.save(user));
             }else
             {
                 this.bookService.addBook(reservationBook);
                 throw new ReservationNotFoundException("Ce livre n'est plus disponible");
             }
    }

    @Override
    public User convertDtoToEntitie(UserDto dto) {
        return this.modelMapper.map(userDao,User.class);
    }

    @Override
    public UserDto convertEntitieToDto(User user) {
        return this.modelMapper.map(user,UserDto.class);
    }
}
