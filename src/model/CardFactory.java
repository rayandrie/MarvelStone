/**
 * 
 */
package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

import cell.SuperTeam;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

/**
 * Brief Description of Code
 * @author Raymond Andriejanssen
 * ITP 368, Fall 2018
 * Final Project
 * andrieja@usc.edu
 */
public class CardFactory {

	// Private Variables
	private List<Card> cardDeck;
	private ObservableList<SuperTeam> st;
	private final int MAX_DECK_SIZE = 15;
	private Random random;
	
	public CardFactory() {
		random = new Random();
	}
	
	/*
	 * Create the list of super teams for the list view
	 */
	public ObservableList<SuperTeam> getSuperTeams() {
		st = FXCollections.observableArrayList();
		st.add(new SuperTeam("Marvel Heroes", "marvel_super_heroes.png"));
		st.add(new SuperTeam("Marvel Villains", "marvel_villains.jpg"));
		st.add(new SuperTeam("The Avengers", "avengers.jpg"));
		st.add(new SuperTeam("Thanos and Friends", "thanos_villains.jpg"));
		st.add(new SuperTeam("X-Men", "xmen.png"));
		st.add(new SuperTeam("Brotherhood of Mutants", "brotherhood.jpg"));
		return st;
	}
	
	/*
	 * Get the SuperTeam associated with that name
	 */
	public SuperTeam getAssociatedTeam(int index) {
		if (st != null) {
			return new SuperTeam(st.get(index));
		} else {
			getSuperTeams();
			return new SuperTeam(st.get(index));
		}
	}
	
	/*
	 * Create a new deck with the heroes chosen. Max Size 15. 
	 */
	public List<Card> getPlayerStartingDeck(List<Card> chosenDeck, List<Card> powerUps) {
		Set<Card> heroesPicked = new HashSet<Card>();
		// Pick 12 heroes from the chosen deck
		int pickSize = 12;
		int i = 0;
		// Add heroes
		while (i < pickSize) {
			// Get random card from chosenDeck (0 - size-1)
			int randNum = random.nextInt(chosenDeck.size() - 1);
			HeroCard hc = (HeroCard) chosenDeck.get(randNum);
			if (!heroesPicked.contains(hc)) {
				heroesPicked.add(hc);
				i++;
			}
		}
		cardDeck = new ArrayList<Card>();
		// For all the cards in the set, make a new Card
		// and add it to the cardDeck
		for (Card c : heroesPicked) {
			HeroCard toAdd = new HeroCard((HeroCard) c);
			cardDeck.add(toAdd);
		}
		// Add power ups
		int cardsToAdd = MAX_DECK_SIZE - pickSize;
		for (int j = 0; j < cardsToAdd; j++) {
			PowerCard pc = (PowerCard) powerUps.get(j);
			PowerCard toAdd = new PowerCard(pc);
			System.out.println("Power Card added: " + toAdd.cardName);
			cardDeck.add(toAdd);
		}
		Collections.shuffle(cardDeck);
		return cardDeck;
	}
	
	/*
	 * Create a new deck with the heroes chosen. Max Size 15. 
	 * For Opponent
	 */
	public List<Card> getOppStartingDeck(List<Card> chosenDeck) {
		Set<Card> heroesPicked = new HashSet<Card>();
		// Pick 15 heroes from the chosen deck
		int pickSize = 15;
		int i = 0;
		// Add heroes
		cardDeck = new ArrayList<Card>();
		if (chosenDeck.size() == pickSize) {
			// if same size, just get all the cards
			for (Card c : chosenDeck) {
				HeroCard toAdd = new HeroCard((HeroCard) c);
				cardDeck.add(toAdd);
			}
		} else {
			// if not same size, get random cards
			while (i < pickSize) {
				// Get random card from chosenDeck (0 to size-1)
				int randNum = random.nextInt(chosenDeck.size() - 1);
				HeroCard hc = (HeroCard) chosenDeck.get(randNum);
				if (!heroesPicked.contains(hc)) {
					heroesPicked.add(hc);
					i++;
				}
			}
			// For all the cards in the set, make a new Card
			// and add it to the cardDeck
			for (Card c : heroesPicked) {
				HeroCard toAdd = new HeroCard((HeroCard) c);
				cardDeck.add(toAdd);
			}
		}
		Collections.shuffle(cardDeck);
		return cardDeck;
	}
	
	// Set up Power Ups
	public List<Card> setUpPowerUps() {
		cardDeck = new ArrayList<Card>();
		String path = "/powerups/";
		cardDeck.add(new PowerCard(PowerUp.MANA_UP, "Mana Regen", path + "mana_up.png"));
		cardDeck.add(new PowerCard(PowerUp.HEALTH_REGEN, "Health Regen", path + "health_up.png"));
		cardDeck.add(new PowerCard(PowerUp.HEALTH_ATTACK, "Ragnarok", path + "health_attack.png"));
		
		return cardDeck;
	}
	
	// Set up all Marvel Heroes
	public List<Card> setUpMarvelHeroes() {
		cardDeck = new ArrayList<Card>();
		String path = "/marvel/heroes/";
		cardDeck.add(new HeroCard(6, 8, "Blade", path + "blade.jpg"));
		cardDeck.add(new HeroCard(4, 4, "Captain Britain", path + "captain_britain.jpg"));
		cardDeck.add(new HeroCard(3, 3, "Crystal", path + "crystal.jpg"));
		cardDeck.add(new HeroCard(5, 7, "Daredevil", path + "daredevil.jpg"));
		cardDeck.add(new HeroCard(11, 9, "Deadpool", path + "deadpool.jpg"));
		cardDeck.add(new HeroCard(5, 5, "Elektra", path + "elektra.jpg"));
		cardDeck.add(new HeroCard(4, 11, "Hercules", path + "hercules.jpg"));
		cardDeck.add(new HeroCard(2, 7, "Human Torch", path + "human_torch.jpg"));
		cardDeck.add(new HeroCard(9, 3, "Invisible Woman", path + "invisible_woman.jpg"));
		cardDeck.add(new HeroCard(2, 5, "Iron Fist", path + "iron_fist.jpg"));
		cardDeck.add(new HeroCard(3, 5, "Luke Cage", path + "luke_cage.jpg"));
		cardDeck.add(new HeroCard(6, 8, "Moon Knight", path + "moon_knight.jpg"));
		cardDeck.add(new HeroCard(7, 7, "Mr Fantastic", path + "mr_fantastic.jpg"));
		cardDeck.add(new HeroCard(4, 7, "Punisher", path + "punisher.jpg"));
		cardDeck.add(new HeroCard(9, 9, "Silver Surfer", path + "silver_surfer.jpg"));
		cardDeck.add(new HeroCard(6, 5, "Speed Ball", path + "speedball.jpg"));
		cardDeck.add(new HeroCard(4, 9, "Thing", path + "thing.jpg"));

		return cardDeck;
	}
	
	// Set up all Marvel Villains
	public List<Card> setUpMarvelVillains() {
		cardDeck = new ArrayList<Card>();
		String path = "/marvel/villains/";
		cardDeck.add(new HeroCard(9, 8, "Annihilus", path + "annihilus.jpg"));
		cardDeck.add(new HeroCard(4, 3, "Dark Hawk", path + "darkhawk.jpg"));
		cardDeck.add(new HeroCard(6, 6, "Death Lok", path + "deathlok.jpg"));
		cardDeck.add(new HeroCard(10, 8, "Dr Doom", path + "dr_doom.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Galactus", path + "galactus.jpg"));
		cardDeck.add(new HeroCard(5, 5, "Ghost Rider", path + "ghostrider.jpg"));
		cardDeck.add(new HeroCard(7, 3, "Kang", path + "kang.jpg"));
		cardDeck.add(new HeroCard(10, 5, "Lilith", path + "lilith.jpg"));
		cardDeck.add(new HeroCard(5, 6, "Mephisto", path + "mephisto.jpg"));
		cardDeck.add(new HeroCard(2, 3, "Mole Man", path + "mole_man.jpg"));
		cardDeck.add(new HeroCard(3, 2, "Sleep Walker", path + "sleepwalker.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Super Skrull", path + "super_skrull.jpg"));
		cardDeck.add(new HeroCard(5, 3, "Terrax", path + "terrax.jpg"));
		cardDeck.add(new HeroCard(3, 2, "Typhoid Mary", path + "typhoid_mary.jpg"));
		cardDeck.add(new HeroCard(5, 5, "Vengeance", path + "vengeance.jpg"));
		cardDeck.add(new HeroCard(10, 11, "Adam Warlock", path + "adam_warlock.jpg"));
		cardDeck.add(new HeroCard(5, 7, "Bullseye", path + "bullseye.jpg"));
		
		return cardDeck;
	}
	
	// Set up all MCU Heroes
	public List<Card> setUpMcuHeroes() {
		cardDeck = new ArrayList<Card>();
		String path = "/mcu/heroes/";
		cardDeck.add(new HeroCard(11, 11, "Black Panther", path + "black_panther.jpg"));
		cardDeck.add(new HeroCard(4, 3, "Black Widow", path + "black_widow.jpg"));
		cardDeck.add(new HeroCard(9, 7, "Captain America", path + "captain_america.jpg"));
		cardDeck.add(new HeroCard(11, 8, "Dr Strange", path + "dr_strange.jpg"));
		cardDeck.add(new HeroCard(2, 6, "Drax", path + "drax.jpg"));
		cardDeck.add(new HeroCard(5, 5, "Falcon", path + "falcon.jpg"));
		cardDeck.add(new HeroCard(4, 4, "Hawkeye", path + "hawkeye.jpg"));
		cardDeck.add(new HeroCard(8, 11, "Hulk", path + "hulk.jpg"));
		cardDeck.add(new HeroCard(10, 10, "Iron Man", path + "iron_man.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Nick Fury", path + "nick_fury.jpg"));
		cardDeck.add(new HeroCard(4, 3, "Night Thrasher", path + "night_thrasher.jpg"));
		cardDeck.add(new HeroCard(7, 3, "Nova", path + "nova.jpg"));
		cardDeck.add(new HeroCard(5, 8, "Scarlet Witch", path + "scarlet_witch.jpg"));
		cardDeck.add(new HeroCard(8, 11, "Spiderman", path + "spiderman.jpg"));
		cardDeck.add(new HeroCard(12, 12, "Thor", path + "thor.jpg"));
		cardDeck.add(new HeroCard(6, 6, "Thunderstrike", path + "thunderstrike.jpg"));
		cardDeck.add(new HeroCard(7, 4, "Vision", path + "vision.jpg"));
		cardDeck.add(new HeroCard(6, 4, "War Machine", path + "war_machine.jpg"));
		cardDeck.add(new HeroCard(3, 7, "Wasp", path + "wasp.jpg"));

		return cardDeck;
	}
	
	// Set up all MCU Villains
	public List<Card> setUpMcuVillains() {
		cardDeck = new ArrayList<Card>();
		String path = "/mcu/villains/";
		cardDeck.add(new HeroCard(5, 13, "Abomination", path + "abomination.jpg"));
		cardDeck.add(new HeroCard(4, 3, "Black Cat", path + "black_cat.jpg"));
		cardDeck.add(new HeroCard(5, 5, "Black Knight", path + "black_knight.jpg"));
		cardDeck.add(new HeroCard(4, 11, "Carnage", path + "carnage.jpg"));
		cardDeck.add(new HeroCard(7, 7, "Doctor Octopus", path + "doc_octopus.jpg"));
		cardDeck.add(new HeroCard(10, 5, "Dormammu", path + "dormammu.jpg"));
		cardDeck.add(new HeroCard(6, 8, "Electro", path + "electro.jpg"));
		cardDeck.add(new HeroCard(3, 3, "Enchantress", path + "enchantress.jpg"));
		cardDeck.add(new HeroCard(5, 6, "HobGoblin", path + "hobgoblin.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Lizard", path + "lizard.jpg"));
		cardDeck.add(new HeroCard(12, 10, "Loki", path + "loki.jpg"));
		cardDeck.add(new HeroCard(10, 6, "Mandarin", path + "mandarin.jpg"));
		cardDeck.add(new HeroCard(9, 4, "Mr Sinister", path + "mr_sinister.jpg"));
		cardDeck.add(new HeroCard(2, 3, "Puma", path + "puma.jpg"));
		cardDeck.add(new HeroCard(9, 8, "Red Skull", path + "red_skull.jpg"));
		cardDeck.add(new HeroCard(2, 6, "Scorpion", path + "scorpion.jpg"));
		cardDeck.add(new HeroCard(13, 14, "Thanos", path + "thanos.jpg"));
		cardDeck.add(new HeroCard(9, 10, "Ultron", path + "ultron.jpg"));
		cardDeck.add(new HeroCard(8, 9, "Venom", path + "venom.jpg"));
		cardDeck.add(new HeroCard(5, 4, "Vulture", path + "vulture.jpg"));

		return cardDeck;
	}
	
	// Set up all X-Men Heroes
	public List<Card> setUpXMenHeroes() {
		cardDeck = new ArrayList<Card>();
		String path = "/Xmen/heroes/";
		cardDeck.add(new HeroCard(5, 4, "Arch Angel", path + "archangel.jpg"));
		cardDeck.add(new HeroCard(6, 8, "Beast", path + "beast.jpg"));
		cardDeck.add(new HeroCard(6, 6, "Bishop", path + "bishop.jpg"));
		cardDeck.add(new HeroCard(3, 6, "Cannonball", path + "cannonball.jpg"));
		cardDeck.add(new HeroCard(7, 7, "Colossus", path + "colossus.jpg"));
		cardDeck.add(new HeroCard(4, 9, "Cyclops", path + "cyclops.jpg"));
		cardDeck.add(new HeroCard(8, 9, "Forge", path + "forge.jpg"));
		cardDeck.add(new HeroCard(6, 7, "Gambit", path + "gambit.jpg"));
		cardDeck.add(new HeroCard(2, 6, "Havok", path + "havok.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Iceman", path + "iceman.jpg"));
		cardDeck.add(new HeroCard(8, 10, "Jean Grey", path + "jean_grey.jpg"));
		cardDeck.add(new HeroCard(3, 2, "Longshot", path + "longshot.jpg"));
		cardDeck.add(new HeroCard(4, 9, "Night Crawler", path + "nightcrawler.jpg"));
		cardDeck.add(new HeroCard(11, 11, "Professor X", path + "profX.jpg"));
		cardDeck.add(new HeroCard(5, 8, "QuickSilver", path + "quicksilver.jpg"));
		cardDeck.add(new HeroCard(4, 3, "Serpentina", path + "serpentina.jpg"));
		cardDeck.add(new HeroCard(2, 4, "Shatterstar", path + "shatterstar.jpg"));
		cardDeck.add(new HeroCard(6, 6, "Skullfire", path + "skullfire.jpg"));
		cardDeck.add(new HeroCard(9, 9, "Storm", path + "storm.jpg"));
		cardDeck.add(new HeroCard(14, 14, "Wolverine", path + "wolverine.jpg"));

		return cardDeck;
	}
	
	// Set up all X-Men Villains
	public List<Card> setUpXMenVillains() {
		cardDeck = new ArrayList<Card>();
		String path = "/Xmen/villains/";
		cardDeck.add(new HeroCard(12, 10, "Apocalypse", path + "apocalypse.jpg"));
		cardDeck.add(new HeroCard(2, 8, "Blob", path + "blob.jpg"));
		cardDeck.add(new HeroCard(8, 8, "Cable", path + "cable.jpg"));
		cardDeck.add(new HeroCard(5, 4, "Cyber", path + "cyber.jpg"));
		cardDeck.add(new HeroCard(6, 8, "Feral", path + "feral.jpg"));
		cardDeck.add(new HeroCard(8, 10, "Juggernaut", path + "juggernaut.jpg"));
		cardDeck.add(new HeroCard(11, 11, "Magneto", path + "magneto.jpg"));
		cardDeck.add(new HeroCard(4, 5, "Mojo", path + "mojo.jpg"));
		cardDeck.add(new HeroCard(8, 5, "Mystique", path + "mystique.jpg"));
		cardDeck.add(new HeroCard(6, 4, "Omega Red", path + "omega_red.jpg"));
		cardDeck.add(new HeroCard(10, 13, "Phoenix", path + "phoenix.jpg"));
		cardDeck.add(new HeroCard(3, 5, "Polaris", path + "polaris.jpg"));
		cardDeck.add(new HeroCard(5, 7, "Psylocke", path + "psylocke.jpg"));
		cardDeck.add(new HeroCard(9, 11, "Sabretooth", path + "sabretooth.jpg"));
		cardDeck.add(new HeroCard(5, 8, "Sauron", path + "sauron.jpg"));
		cardDeck.add(new HeroCard(6, 6, "Stryfe", path + "stryfe.jpg"));
		cardDeck.add(new HeroCard(3, 9, "Weapon Omega", path + "weapon_omega.jpg"));
		cardDeck.add(new HeroCard(8, 10, "DeathBird", path + "deathbird.jpg"));

		return cardDeck;
	}

}
