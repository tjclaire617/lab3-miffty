package pkgCore;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import pkgEnum.eCardCount;
import pkgEnum.eCardDestination;
import pkgEnum.eCardVisibility;
import pkgEnum.eGame;
import pkgEnum.eHandStrength;
import pkgEnum.eRank;
import pkgEnum.eSuit;
import pkgException.DeckException;
import pkgException.HandException;

public class HandCombinationTest {

	@Test
	public void FiveCardStudTest1() {

		ArrayList<HandPoker> lstHandPoker = new ArrayList<HandPoker>();
		Table t = new Table("my table");
		Player p1 = new Player("Bert");
		t.AddPlayerToTable(p1);
		Rule rle = new Rule(eGame.FiveStud);
		GamePlay gp = new GamePlay(t, rle);

		HandPoker HP = new HandPoker(p1, gp);
		Card c1 = new Card(eSuit.CLUBS, eRank.TWO);
		Card c2 = new Card(eSuit.CLUBS, eRank.TWO);
		Card c3 = new Card(eSuit.CLUBS, eRank.TWO);
		Card c4 = new Card(eSuit.CLUBS, eRank.THREE);
		Card c5 = new Card(eSuit.CLUBS, eRank.THREE);

		HP.AddCard(c1);
		HP.AddCard(c2);
		HP.AddCard(c3);
		HP.AddCard(c4);
		HP.AddCard(c5);

		try {
			lstHandPoker = HP.EvaluateHand();
		} catch (HandException e) {
			fail("Evaluation failed");
		}
		assertEquals(1, lstHandPoker.size());
		assertEquals(eHandStrength.FullHouse, lstHandPoker.get(0).getHandScorePoker().geteHandStrength());
	}

	@Test
	public void TexasHoldEm_Test1() {

		ArrayList<HandPoker> lstHandPoker = new ArrayList<HandPoker>();
		Table t = new Table("my table");
		Player p1 = new Player("Bert");
		t.AddPlayerToTable(p1);
		Rule rle = new Rule(eGame.TexasHoldEm);
		GamePlay gp = new GamePlay(t, rle);

		ArrayList<Card> CommonCards = new ArrayList<Card>();
		CommonCards.add(new Card(eSuit.DIAMONDS, eRank.SIX));
		CommonCards.add(new Card(eSuit.DIAMONDS, eRank.FOUR));
		CommonCards.add(new Card(eSuit.DIAMONDS, eRank.FIVE));
		CommonCards.add(new Card(eSuit.DIAMONDS, eRank.TEN));
		CommonCards.add(new Card(eSuit.SPADES, eRank.KING));

		gp = this.SetCommonCards(CommonCards, gp);

		/*
		 * - normally you would draw, but I can't because I need to manually set the
		 * cards. CardDraw CD = new CardDraw(eCardCount.Three, eCardDestination.COMMON,
		 * eCardVisibility.EVERYONE);
		 * 
		 * try { gp.Draw(null, CD); } catch (DeckException | HandException e1) {
		 * fail("Draw did not work"); }
		 */

		HandPoker HP = new HandPoker(p1, gp);
		Card c1 = new Card(eSuit.CLUBS, eRank.TWO);
		Card c2 = new Card(eSuit.CLUBS, eRank.THREE);

		HP.AddCard(c1);
		HP.AddCard(c2);

		try {
			lstHandPoker = HP.EvaluateHand();
		} catch (HandException e) {
			fail("Evaluation failed");
		}
		assertEquals(21, lstHandPoker.size());

		assertEquals(eHandStrength.Straight, lstHandPoker.get(0).getHandScorePoker().geteHandStrength());

		for (HandPoker hp : lstHandPoker) {
			System.out.print(hp.getHandScorePoker().geteHandStrength() + " ");
			System.out.print(hp.getHandScorePoker().getHiCard().geteSuit() + " "
					+ hp.getHandScorePoker().getHiCard().geteRank() + " ");
			if (hp.getHandScorePoker().getLoCard() != null) {

				System.out.print(hp.getHandScorePoker().getLoCard().geteSuit() + " "
						+ hp.getHandScorePoker().getLoCard().geteRank() + " ");
			}
			for (Card c : hp.getCards()) {
				System.out.print(c.geteSuit() + " " + c.geteRank() + " ");
			}
			System.out.println(" ");
		}

	}

	private GamePlay SetCommonCards(ArrayList<Card> setCards, GamePlay gp) {
		Object oGamePlay = null;
		try {
			// Load the Class into 'c'
			Class<?> c = Class.forName("pkgCore.GamePlay");
			// Load 'msetCardsInHand' with the 'Draw' method (no args);
			Method mSetCommonCards = c.getDeclaredMethod("setCommonCards", new Class[] { ArrayList.class });
			// Change the visibility of 'setCardsInHand' to true *Good Grief!*
			mSetCommonCards.setAccessible(true);
			// invoke 'msetCardsInHand'
			oGamePlay = mSetCommonCards.invoke(gp, setCards);

		} catch (ClassNotFoundException x) {
			fail("Class Not Found Exception Thrown");
		} catch (SecurityException e) {
			fail("Security Exception Thrown");
		} catch (IllegalArgumentException e) {
			fail("Illegal Arugment Exception Thrown");
		} catch (IllegalAccessException e) {
			fail("Illegal Access Exception Thrown");
		} catch (NoSuchMethodException e) {
			fail("No Such Method Exception Thrown");
		} catch (InvocationTargetException e) {
			fail("Invocation Exception Thrown");
		}
		return (GamePlay) oGamePlay;

	}
}
