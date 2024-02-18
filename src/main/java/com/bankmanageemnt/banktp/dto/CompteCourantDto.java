package com.bankmanageemnt.banktp.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author Sawadogo hamed   email <kerb418@gmail.com>
 **/
 @NoArgsConstructor @AllArgsConstructor
 @Data
public class CompteCourantDto extends CompteDto{
    private Double decouvert;

 }
