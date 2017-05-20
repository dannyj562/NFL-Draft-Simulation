/* 
	Danny Rivera
	CSC 311
	Project 1 
	Dr. Amlan Chatterjee
	NFL DRAFT SIMULATION
	February 21, 2016

	Each spring brings hope for National Football League (NFL) teams, especially for those that had poor
	win-loss records in the previous season. The NFL draft is held in April and is the opportunity for teams
	to improve their roster by adding those college players considered to be the most talented in the world.
	NFL teams develop their player rosters through three methods: free-agent signings, trading their players
	for players from other teams, and drafting college players who have declared themselves eligible for the
	NFL draft. The NFL draft is a two-day event in which all 32 teams take their turns selecting these players.
	In this project we are going to simulate a limited version of the draft process. This simulated draft
	would involve 8 teams and 32 players. Pick any 8 teams from the actual NFL and 32 players of your choice.
	Be creative!
	1. Assign a random winning percentage to each of the teams for the previous season.
	2. Based on the winning percentage, rank the teams in order starting with the one that had the worst
		performance.
	3. Each team can now select players in the order they were ranked.
	4. Assign numbers 1 to 4 to the players randomly indicating the round in which they can be picked.
	5. In each round, the teams in order of their numbers will choose randomly from the players eligible for
	that round and select them.
	6. At the end of 4 rounds, each of the 8 teams will have 4 players they picked.
*/

import java.util.Arrays;
import java.util.Random;
import java.util.List;
import java.util.Collections;
import java.util.ArrayList;


public class NFLDriver 
{
	/* STATIC VARIABLES */
	
	// Array of 8 teams
	public static String teamNames[] = {"Denver Broncos", "Los Angeles Rams", "Dallas Cowboys", "Oakland Raiders", 
										"New York Jets", "San Francisco 49ers", "Arizona Cardinals", "Pittsburgh Steelers"};
	// Array of Players Name
	public static String playersName[] = {"Cam Newton", "Tom Brady", "J.J. Watt", "Antonio Brown", "Adrian Peterson", "Aaron Rodgers",
										"Luke Kuechly", "Julio Jones", "Rob Gronkowski", "Odell Beckham Jr", "Josh Norman", "Carson Palmer",
										"Khalil Mack", "Aaron Donald", "Von Miller", "A.J. Green", "Russell Wilson", "Patrick Peterson", 
										"DeAndre Hopkins", "Richard Sherman", "Ben Roethilisberger", "Todd Gurley", "Joe Thomas", "Darrelle Revis",
										"Brandon Marshall", "Justin Houston", "Larry Fitzgerald", "Tyrann Mathieu", "Geno Atkins", "Drew Brees",
										"Allen Robinson", "Kam Chancellor"};
	
	// Array of Team Object.
	public static Team teams[] = new Team[8];

	// Array for Winning percentage. Used for Team. Stores number between 1 - 100
	public static int winning_percentage[] = new int[8];

	// An Array List of players that contains players name and rounds. 
	public static List<Players> players = new ArrayList<Players>();

	/*  An array list of player rounds. This is used to remove random rounds of player
		 in order to be normally distributed amongst every Player */
	public static List<Integer> checkPlayerRounds = new ArrayList<Integer>();

	// Temporary integer for every players round
	public static int tempPlayersRound[] = new int[32];

	// Random generator
	public static Random rounds = new Random();
	// Stores Random number
	public static int randomNum;

	
	/* 2D Array for displaying output as a table */
	private static Object[][] teamTableLayout = new Object[8][3];
	private static Object[][] playerTableLayout = new Object[32][2];


	/*  ******** MAIN ******** */ 
	public static void main(String[] args)
	{
		/* TEAM */ 
		initializeTeam(); 
		
		/* PLAYER */
		initializePlayers();
		
		/* DISPLAY OUTPUT */
		displayPrompt();
	}

	/*  Initialize 8 teams using an object Array called Team */
	public static void initializeTeam()
	{
		for(int i=0; i<teams.length; i++)
		{
			teams[i] = new Team(teamNames[i]);	// Initialize team name for 8 Teams
		}
		setRandomWinningPercentage(); // calls method for assigning winning percentage of Team. 
	}

	/* Assign Random Winning percentage */
	public static void setRandomWinningPercentage()
	{
		for(int i=0; i<teams.length; i++)
		{
			int randomWinningPercentage = rounds.nextInt(101); // Generate Random number between 0 - 100
			// Store winning percentage in an array of size 8
			winning_percentage[i] = teams[i].setWinningPercentage(randomWinningPercentage);
		}
		/* Sort winning percentage in ascending order */
		Arrays.sort(winning_percentage);
		rankTeam(); // calls method for ranking the team 
	}

	/* Rank the team using an Array of Team class */
	public static void rankTeam()
	{
		/*  Set ranks from Team class. 
			Rank is between 1 - 8 */
		for(int i=0; i<teams.length; i++)
		{
			teams[i].setRank(i+1);	
		} 
	}
	
	/* Initialize Players name using a Array List */
	public static void initializePlayers()
	{
		createRandomRoundsForPlayers(); // calls method for assigning random rounds to players.

		for(int i=0; i<tempPlayersRound.length; i++)
		{
			// Create an Array List of 32 players passing the players name and rounds.
			players.add(new Players(playersName[i], tempPlayersRound[i]));
		}

		//Sort player's name and rounds in the Array List of player.  
		Collections.sort(players);
	}

	/* Assign random rounds between 1-4 for every 32 Players
		Must be evenly distributed.  */
	public static void createRandomRoundsForPlayers()
	{
		// Assign rounds between 1 - 4 in an Array List of size 32
		for(int i=1; i<9; i++)
		{
			checkPlayerRounds.add(1);
			checkPlayerRounds.add(2);
			checkPlayerRounds.add(3);
			checkPlayerRounds.add(4);
		}

		// Remove elements from array list to check if random rounds is evenly distributed.
		for(int i=0; i<playersName.length; i++)
		{
			randomNum = rounds.nextInt(checkPlayerRounds.size()); // Generate random number of players between 0 - 31
			tempPlayersRound[i] = checkPlayerRounds.remove(randomNum);
		}
	}

	// Prompt information to user
	public static void displayPrompt()
	{

		System.out.println("\n\t \t ====== NFL Draft ======\n");
		System.out.println();	// Empty Line

		System.out.format("%20s %10s %20s\n", "TEAM", "RANK", "WINNING PERCENTAGE");

		/* Print out a table of the team, ranks, and winning percentage using a 2D Array */
		for(int i=0; i<teams.length; i++)
		{
			teamTableLayout[i] = new String[] { teams[i].getTeamName(), String.valueOf(teams[i].getRank()), String.valueOf(winning_percentage[i]) };
			System.out.format("%20s %10s %12s\n", teamTableLayout[i]);
		}

		System.out.println("\n");	// Empty Line
		/* A BREAK LINE USING ** */
		for(int i=0; i<30; i++)
		{
			System.out.print("* ");
			
		} 
		System.out.println("\n");	// Empty Line

		System.out.format("%20s %10s\n", "NFL PLAYERS ", "ROUNDS");
		for(int i=0; i<playersName.length; i++)
		{
			playerTableLayout[i] = new String[] { players.get(i).getPlayersName(), String.valueOf(players.get(i).getPlayersRandomRounds())};
			System.out.format("%20s %10s\n", playerTableLayout[i]);

		}

		System.out.println("\n");
		/* A BREAK LINE USING ** */
		for(int i=0; i<30; i++)
		{
			System.out.print("* ");
			
		} 	
		System.out.println("\n");

		NFLDraft(); // Calls method. Team chooses Players
	}

	public static void NFLDraft()
	{
		/* Temporary Array List that stores the players that were selected
			Size of Array List is 32 */
		List<String> playersSelected = new ArrayList<String>();

		for(int numberOfRounds=1; numberOfRounds<=4; numberOfRounds++)
		{
			for(int i=7; i>=0; i--)
			{
				if(i == 0)
				{
					playersSelected.add(players.remove(i).getPlayersName());
				}
				else
				{
					randomNum = rounds.nextInt(i);
					playersSelected.add(players.remove(randomNum).getPlayersName());
				}
				

			}
		} 

		// Temporary Index for the Array List that stored the players that were selected */
		int tempPlayersIndex = 0;

		for(int i=0; i<8; i++)
		{
			tempPlayersIndex = i;
			System.out.println("Round 1: Team : " + teams[i].getTeamName() + " selected " + playersSelected.get(tempPlayersIndex));
		}
		System.out.println(""); // Empty Line

		for(int i=0; i<8; i++)
		{
			tempPlayersIndex += 1; // Increment by 1 to get next Player
			System.out.println("Round 2: Team : " + teams[i].getTeamName() + " selected " + playersSelected.get(tempPlayersIndex));
			
		}
		System.out.println(""); // Empty Line

		for(int i=0; i<8; i++)
		{
			tempPlayersIndex += 1; // Increment by 1 to get next Player
			System.out.println("Round 3: Team : " + teams[i].getTeamName() + " selected " + playersSelected.get(tempPlayersIndex));

		}
		System.out.println(""); // Empty Line

		for(int i=0; i<8; i++)
		{
			tempPlayersIndex += 1; // Increment by 1 to get next Player
			System.out.println("Round 4: Team : " + teams[i].getTeamName() + " selected " + playersSelected.get(tempPlayersIndex));
		}
		System.out.println(""); // Empty Line
	}
}