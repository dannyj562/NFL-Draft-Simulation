/* 
	Danny Rivera
	CSC 311
	Project 1 
	Dr. Amlan Chatterjee
	NFL DRAFT SIMULATION
	February 21, 2016
*/
public class Players implements Comparable<Players>
{
	private String currentPlayer;
	private int randomRounds;

	/* Public constructor of Players class
		Takes 2 parameters. players name and the random round */
	public Players(String currentPlayer, int randomRounds)
	{
		this.currentPlayer = currentPlayer;
		this.randomRounds = randomRounds;

	}

	/* SETTER METHOD
		Sets Random rounds of player */
	public int setRandomRounds(int randomRounds)
	{
		// Error checking. Must be between 1 - 4
		if(randomRounds <= 0 || randomRounds > 4)
		{
			System.out.println("ERROR!!! Invalid Number of Rounds. Program Terminating");
			System.exit(0); // EXIT PROGRAM
		}
	
		this.randomRounds = randomRounds;
		return(this.randomRounds);
	}

	/* GETTER METHOD
		Gets players name */
	public String getPlayersName()
	{
		return this.currentPlayer;
	}

	/* GETTER METHOD
		Gets players rounds */
	public int getPlayersRandomRounds()
	{
		return(this.randomRounds);
	}

	/* Compares Player with the next Player
		Method is used to compare player's round */
	public int compareTo(Players chosenPlayer)
	{
		return Integer.compare(this.getPlayersRandomRounds(), chosenPlayer.getPlayersRandomRounds() );
	}
}