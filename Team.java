/* 
	Danny Rivera
	CSC 311
	Project 1 
	Dr. Amlan Chatterjee
	NFL DRAFT SIMULATION
	February 21, 2016
*/
public class Team
{
	private String  currentTeamName;
	private int winningPercentage;
	private int currentRank;

	/* Public Constructor of Team class 
		Takes 1 parameter of the current team name */
	public Team(String currentTeamName)
	{
		this.currentTeamName = currentTeamName;
	}

	/* SETTER METHOD */
	/* Sets the winning percentage of the team */
	public int setWinningPercentage(int randomWinningPercentage)
	{
		/* Error checking for percentage out of bounds between negative and over 100 % */
		if(randomWinningPercentage < 0 || randomWinningPercentage > 100)
		{
			System.out.println("ERROR!!! PERCENTAGE INVLALID. PROGRAM TERMINATING . . . .");
			System.exit(0);	// EXIT PROGRAM
		}

		this.winningPercentage = randomWinningPercentage;
		return this.winningPercentage;
	}

	/* SETTER METHOD */
	/* Sets the rank of the team */
	public int setRank(int currentRank)
	{
		/* Error checking for rank out of bounds */
		if(currentRank < 1 || currentRank > 8)
		{
			System.out.println("ERROR!!! RANK INVALID. PROGRAM TERMINATING . . . .");
			System.exit(0);	// EXIT PROGRAM
		}
		
		this.currentRank = currentRank; 
		return this.currentRank;
			
	}

	/* GETTER METHOD 
		Gets Winning Percentage of Team */
	public int getWinningPercentage()
	{
		return this.winningPercentage;
	}

	/* GETTER METHOD 
		Gets rank of Team */
	public int getRank()
	{
		return this.currentRank;
	}
	
	/* GETTER METHOD 
		Gets Team name */
	public String getTeamName()
	{
		return this.currentTeamName;
	}
}