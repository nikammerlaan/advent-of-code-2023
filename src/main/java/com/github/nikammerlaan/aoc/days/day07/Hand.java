package com.github.nikammerlaan.aoc.days.day07;

import java.util.*;

import static com.github.nikammerlaan.aoc.days.day07.Card.*;
import static com.github.nikammerlaan.aoc.days.day07.Hand.Type.*;
import static java.util.Collections.frequency;

public record Hand(
    List<Card> cards,
    int bid,
    Type type
) implements Comparable<Hand> {

    public Hand(List<Card> cards, int bid) {
        this(cards, bid, getType(cards));
    }

    public Hand replaceJacksWithJokers() {
        var newCards = cards.stream()
            .map(card -> card == JACK ? JOKER : card)
            .toList();
        return new Hand(newCards, bid);
    }

    @Override
    public int compareTo(Hand o) {
        var typeComparison = Integer.compare(type.ordinal(), o.type.ordinal());

        if(typeComparison != 0) {
            return typeComparison;
        }

        for(int i = 0; i < cards.size(); i++) {
            var card = cards.get(i);
            var otherCard = o.cards.get(i);
            var cardComparison = card.compareTo(otherCard);
            if(cardComparison != 0) {
                return cardComparison;
            }
        }

        return 0;
    }

    private static Type getType(List<Card> cards) {
        var counts = new HashMap<Card, Integer>();
        for(var card : cards) {
            counts.put(card, counts.getOrDefault(card, 0) + 1);
        }

        // If the list contains a JOKER, then we need to replace it with a real card. I'm sure there's a better
        // algorithm to determine what the best option is, but there's only 12 options.
        if(counts.getOrDefault(JOKER, 0) > 0) {
            return Arrays.stream(Card.values())
                .filter(card -> card != JOKER && card != JACK)
                .map(replacementCard -> {
                    var newCards = new ArrayList<>(cards);
                    newCards.replaceAll(card -> card == JOKER ? replacementCard : card);
                    return getType(newCards);
                })
                .max(Comparator.naturalOrder())
                .orElseThrow();
        }

        var values = counts.values();
        if(values.contains(5)) {
            return FIVE_OF_A_KIND;
        } else if(values.contains(4)) {
            return FOUR_OF_A_KIND;
        } else if(values.containsAll(List.of(3, 2))) {
            return FULL_HOUSE;
        } else if(values.contains(3)) {
            return THREE_OF_A_KIND;
        } else if(frequency(values, 2) == 2) {
            return TWO_PAIR;
        } else if(values.contains(2)) {
            return ONE_PAIR;
        } else {
            return HIGH_CARD;
        }
    }

    enum Type {
        HIGH_CARD,
        ONE_PAIR,
        TWO_PAIR,
        THREE_OF_A_KIND,
        FULL_HOUSE,
        FOUR_OF_A_KIND,
        FIVE_OF_A_KIND
    }

}
