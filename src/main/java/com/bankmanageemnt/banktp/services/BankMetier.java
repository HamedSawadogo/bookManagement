package com.bankmanageemnt.banktp.services;

public interface BankMetier{
    void retirer(String account,Double amount);
    void deposer(String account,Double amount);
    void transferer(String sender,String receiver,Double amount);
}
