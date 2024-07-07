package com.cashCard.repository;

import com.cashCard.CashCard;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface CashCardRepository extends CrudRepository<CashCard, Long>, PagingAndSortingRepository<CashCard,Long> {

}
