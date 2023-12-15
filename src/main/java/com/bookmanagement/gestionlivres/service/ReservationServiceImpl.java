package com.bookmanagement.gestionlivres.service;

import com.bookmanagement.gestionlivres.Dtos.ReservationDto;
import com.bookmanagement.gestionlivres.exceptions.ReservationNotFoundException;
import com.bookmanagement.gestionlivres.model.Book;
import com.bookmanagement.gestionlivres.model.Reservation;
import com.bookmanagement.gestionlivres.repositories.ReservationDao;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Transactional
@Service
public class ReservationServiceImpl implements ReservationService, DtoInterface<Reservation, ReservationDto>{

    private  final ModelMapper modelMapper;
    private final ReservationDao reservationDao;

    private final BookServiceImpl bookRepository;

    public ReservationServiceImpl(ReservationDao reservationDao,BookServiceImpl bookRepository, ModelMapper modelMapper){
        this.reservationDao=reservationDao;
        this.modelMapper=modelMapper;
        this.bookRepository=bookRepository;
    }
    /**
     * Ajouter une Reservation
     * @param reservationDto
     * @param bookId
     * @return
     * @throws ReservationNotFoundException
     */
    @Override
    public ReservationDto addReservation(ReservationDto reservationDto, Long bookId) throws ReservationNotFoundException {

        Reservation reservation=this.convertDtoToEntitie(reservationDto);
        if(Objects.isNull(reservation))throw  new ReservationNotFoundException("Cette reservation est invalide");

        Optional<Book> optionalBook=this.bookRepository.findBookById(bookId);
        if(optionalBook.isPresent()){
            if(reservation.getReservationDate().compareTo(reservation.getDateRetourLivre())<0){
                reservation.setBook(optionalBook.get());
                return this.convertEntitieToDto(this.reservationDao.save(reservation));
            }else{
                throw new ReservationNotFoundException("La date de retour de la reservation est invalide");
            }
        }
        throw new ReservationNotFoundException("Impossible d'enregistrer cette Reservation");
    }

    public List<ReservationDto>listReservationsByUser(String userId){
        return this.reservationDao.getAllReservationsByUser(userId)
                .stream()
                .map(this::convertEntitieToDto)
                .collect(Collectors.toList());
    }
    /**
     * Supprimer Une reservation
     * @param reservationId
     */
    @Override
    public void deleteReservation(Integer reservationId) {
      this.reservationDao.deleteById(reservationId);
    }
    /**
     * Renvoie la liste des reservations
     * @return
     */
    @Override
    public Iterable<ReservationDto> getAllReservations() {
        return this.reservationDao.findAll()
                .stream().map(this::convertEntitieToDto)
                .collect(Collectors.toList());
    }
    /**
     * Retourne toutes les reservations ayant un Id donnée
     * @param id
     * @return
     */
    @Override
    public  ReservationDto findReservationById(Integer id) throws ReservationNotFoundException {

        Optional<Reservation> optionalReservation=reservationDao.findById(id);
        if(optionalReservation.isPresent()){
            Reservation reservation=optionalReservation.get();
            return this.convertEntitieToDto(reservation);
        }
       throw  new ReservationNotFoundException("Cette Reservation est introuvable");
    }

    @Override
    public Reservation convertDtoToEntitie(ReservationDto reservationDto) {
        return this.modelMapper.map(reservationDto,Reservation.class);
    }

    @Override
    public ReservationDto convertEntitieToDto(Reservation reservation) {
        return this.modelMapper.map(reservation,ReservationDto.class);
    }
}
