package com.smartdev.cards.service;

import com.smartdev.cards.dto.CardsDto;

import java.util.List;

public interface ICardsService {
    void createCard(String mobileNumber);

    CardsDto fetchCard(String mobileNumber);

    List<CardsDto> fetchCards();

    boolean updateCard(CardsDto cardsDto);

    boolean deleteCard(String mobileNumber);
}
