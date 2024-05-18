package com.smartdev.cards.service.impl;

import com.smartdev.cards.constants.CardsConstants;
import com.smartdev.cards.dto.CardsDto;
import com.smartdev.cards.entity.Cards;
import com.smartdev.cards.exception.CardAlreadyExistsException;
import com.smartdev.cards.exception.ResourceNotFoundException;
import com.smartdev.cards.mapper.CardsMapper;
import com.smartdev.cards.repository.CardsRepository;
import com.smartdev.cards.service.ICardsService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Random;

@Service
@AllArgsConstructor
public class CardsServiceImpl implements ICardsService {

    private CardsRepository cardsRepository;


    @Override
    public void createCard(String mobileNumber) {
        Optional<Cards> optionalCards = cardsRepository.findByMobileNumber(mobileNumber);
        if (optionalCards.isPresent()) {
            throw new CardAlreadyExistsException("Card already registered with given mobileNumber " + mobileNumber);
        }
        cardsRepository.save(createNewCard(mobileNumber));
    }


    private Cards createNewCard(String mobileNumber) {
        Cards newCard = new Cards();
        long randomCardNumber = 100000000000L + new Random().nextInt(900000000);
        newCard.setCardNumber(Long.toString(randomCardNumber));
        newCard.setMobileNumber(mobileNumber);
        newCard.setCardType(CardsConstants.CREDIT_CARD);
        newCard.setTotalLimit(CardsConstants.NEW_CARD_LIMIT);
        newCard.setAmountUsed(0);
        newCard.setAvailableAmount(CardsConstants.NEW_CARD_LIMIT);
        return newCard;
    }


    @Override
    public CardsDto fetchCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        return CardsMapper.mapToCardsDto(cards, new CardsDto());
    }

    @Override
    public List<CardsDto> fetchCards() {
        List<CardsDto> cardsDtos = new ArrayList<>();
        cardsRepository.findAll().stream().forEach(card -> cardsDtos.add(CardsMapper.mapToCardsDto(card, new CardsDto())));
        return cardsDtos;
    }

    @Override
    public boolean updateCard(CardsDto cardsDto) {
        Cards cards = cardsRepository.findByCardNumber(cardsDto.getCardNumber()).orElseThrow(() -> new ResourceNotFoundException("Card", "CardNumber", cardsDto.getCardNumber()));
        CardsMapper.mapToCards(cardsDto, cards);
        cardsRepository.save(cards);
        return true;
    }

    @Override
    public boolean deleteCard(String mobileNumber) {
        Cards cards = cardsRepository.findByMobileNumber(mobileNumber).orElseThrow(() -> new ResourceNotFoundException("Card", "mobileNumber", mobileNumber));
        cardsRepository.deleteById(cards.getCardId());
        return true;
    }


}
