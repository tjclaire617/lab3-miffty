package pkgCore;

import java.util.ArrayList;
import java.util.UUID;

public class Table {

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * TableID - UUID for the table.  Makes it unique
	 */
	private UUID TableID;
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * TableName - Name of the table
	 */
	private String TableName;
	
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 */
	private ArrayList<Player> TablePlayers = new ArrayList<Player>();

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * Table - Constructor
	 * 
	 * Create an instance of Table.  Set the TableID as a random UUID, set 
	 * the TableName attribute
	 * @param tableName
	 */
	public Table(String tableName) {
		TableID = UUID.randomUUID();
		TableName = tableName;
	}

	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * SetTablePlayers - set the TablePlayers ArrayList with given Players
	 * @param Players - set of Players to add to the table
	 */
	public void SetTablePlayers(ArrayList<Player> Players)
	{
		//FIXME: clear the table and add all the players.
	}
	
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * AddPlayerToTable - Add a single Player to the table.
	 * @param player - Player to be added
	 */
	public void AddPlayerToTable(Player player)
	{
		//FIXME: Add a player to the table
	}
	
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * RemovePlayerFromTable - Remove a single player from the Table.
	 * @param p
	 */
	public void RemovePlayerFromTable(Player p)
	{
		//FIXME: Remove a player from the table
	}
	
	/**
	 * @author BRG
	 * @version Lab #3
	 * @since Lab #3
	 * 
	 * getTablePlayers - return an ArrayList of players at the table
	 * @return
	 */
	public ArrayList<Player> getTablePlayers()
	{
		//FIXME: This should return the TablePlayers (don't return null).
		return null;
	}

}