package com.github.nikammerlaan.aoc.days.day07;

public enum Card {

    JOKER('X'),
    TWO('2'),
    THREE('3'),
    FOUR('4'),
    FIVE('5'),
    SIX('6'),
    SEVEN('7'),
    EIGHT('8'),
    NINE('9'),
    TEN('T'),
    JACK('J'),
    QUEEN('Q'),
    KING('K'),
    ACE('A');

    private final char c;

    Card(char c) {
        this.c = c;
    }

    public static Card getByChar(char c) {
        for(var card : Card.values()) {
            if(card.c == c) {
                return card;
            }
        }
        throw new IllegalArgumentException();
    }

}
