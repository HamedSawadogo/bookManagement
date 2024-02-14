package com.bankmanageemnt.banktp.dao;

import com.bankmanageemnt.banktp.model.CompteCourant;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CompteCourantDao extends JpaRepository<CompteCourant,String> {
}
