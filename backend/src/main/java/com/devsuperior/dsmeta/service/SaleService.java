package com.devsuperior.dsmeta.service;

import com.devsuperior.dsmeta.model.Sale;
import com.devsuperior.dsmeta.repository.SaleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;


@Service
public class SaleService {
    @Autowired
    private SaleRepository saleRepository;

    public Page<Sale> findSales(String minDate, String maxDate, Pageable pageable){
//        Pega a data atual com o fuso horário do sistema
        LocalDate today = LocalDate.ofInstant(Instant.now(), ZoneId.systemDefault());
        
//        Usa condição ternária: o que vem depois do "?" é se a condição for verdadeira
//        O que vem depois do ":" é se a condição for falsa
        LocalDate min = minDate.equals("") ? today.minusDays(365) : LocalDate.parse(minDate);
        LocalDate max = maxDate.equals("") ? today : LocalDate.parse(maxDate);
        return saleRepository.findSales(min, max, pageable);
    }
}
