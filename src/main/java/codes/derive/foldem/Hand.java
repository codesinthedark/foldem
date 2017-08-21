/*
 * This file is part of Fold'em, a Java library for Texas Hold 'em Poker.
 *
 * Fold'em is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * Fold'em is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with Fold'em.  If not, see <http://www.gnu.org/licenses/>.
 */
package codes.derive.foldem;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

/**
 * Represents a poker hand.
 */
public class Hand {

	/* The hole cards contained within this hand. */
	private final List<Card> cards = new ArrayList<>();

	/**
	 * Creates a new hand with the specified cards.
	 * 
	 * @param cards
	 *            The cards to use.
	 */
	public Hand(Card... cards) {
		boolean valid = false;
		for (Game game : Game.values()) {
			if (game.cards() == cards.length) {
				valid = true;
				break;
			}
		}
		if (!valid) {
			throw new IllegalArgumentException("Invalid number of cards");
		}
		this.cards.addAll(Arrays.asList(cards));
	}
	
	/**
	 * Creates a new hand with the specified string shorthand.
	 * 
	 * <p>
	 * This uses the same format as single cards. For example, "AsAc" would
	 * create a hand with the ace of spaces and the ace of clubs. For more
	 * information on this format please refer to
	 * {@link codes.derive.foldem.Card#Card(String)}.
	 * </p>
	 * 
	 * @param cards
	 *            The cards represented as a shorthand string.
	 * @see codes.derive.foldem.Card#Card(String)
	 */
	public Hand(String cards) {
		this(Poker.cards(cards).toArray(new Card[0])); // TODO own function for parse
	}

	/**
	 * Obtains an unmodifiable view of the cards within this hand.
	 * 
	 * @return An unmodifiable view of the cards within this hand.
	 */
	public Collection<Card> cards() {
		return Collections.unmodifiableCollection(cards);
	}
	
	/**
	 * Obtains whether or not the hand is suited.
	 * 
	 * @return <code>true</code> if both cards are the same suit, otherwise
	 *         <code>false</code>.
	 */
	public boolean suited() {
		boolean suited = true;
		for (Card c : cards) {
			if (!c.getSuit().equals(cards.get(0))) {
				suited = false;
				break;
			}
		}
		return suited;
	}
	
	@Override
	public String toString() {
		StringBuilder bldr = new StringBuilder();
		for (Card c : cards()) {
			bldr.append(c);
		}
		return bldr.toString();
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + cards.hashCode();
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Hand other = (Hand) obj;
		if (!cards.containsAll(other.cards))
			return false;
		return true;
	}
	
}
