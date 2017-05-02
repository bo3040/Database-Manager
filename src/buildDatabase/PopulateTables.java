// Author: Olah Brad and Owoseje Abiodun
// Instructor: Dr. Dudley Girard
// Course: Csc 570 ( Database Management Systems)
// Programming Assignment #2
// PopulateTables.java

package buildDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PopulateTables {

    /**s
     * This executes the PopulateTables execute method. It populates all the 5 tables selected from Homework 3.
     *
     * @return void
     */
    public static void execute(Connection m_dbConn) throws SQLException {
        // Populate the Player table
        PopulateTables.populateUserTable(m_dbConn);
		// Populate the Staff_Abilities table
        PopulateTables.populateStaff_AbilitiesTable(m_dbConn);
        // Populate the Location table
        PopulateTables.populateLocationTable(m_dbConn);
        // Populate the Entity table
        PopulateTables.populateEntityTable(m_dbConn);
        // Populate  the Ability table
        PopulateTables.populateAbilityTable(m_dbConn);
		// Populate the Generic_Item table
		PopulateTables.populateGeneric_ItemTable(m_dbConn);
		// Populate the Moderator_Account table
		PopulateTables.populateModerator_AccountTable(m_dbConn);
		//Populate the Manager_Account table
		PopulateTables.populateManager_AccountTable(m_dbConn);
		//Populate  the Player_Account table
		PopulateTables.populatePlayer_AccountTable(m_dbConn);
		//Populate the Staff_Abilities_Link table
		PopulateTables.populateStaff_Abilities_LinkTable(m_dbConn);
		//Populate the Location_Connections table
		PopulateTables.populateLocation_ConnectionsTable(m_dbConn);
		//Populate the Weapon table
		PopulateTables.populateWeaponTable(m_dbConn);
		//Populate the Armor table
		PopulateTables.populateArmorTable(m_dbConn);
		//Populate  the Container table
		PopulateTables.populateContainerTable(m_dbConn);
		//Populate the Stored_Items table
		PopulateTables.populateStored_ItemsTable(m_dbConn);
		//Populate  the Items_in_Location table
		PopulateTables.populateItems_in_LocationTable(m_dbConn);
		//Populate  the Game_Character table
		PopulateTables.populateGame_CharacterTable(m_dbConn);
		//Populate  the Creature table
		PopulateTables.populateCreatureTable(m_dbConn);
		//Populate  the Preferred_Locations table
		PopulateTables.populatePreferred_LocationsTable(m_dbConn);
		//Populate  the Liked_Entities table
		PopulateTables.populateLiked_EntitiesTable(m_dbConn);
		//Populate  the Hated_Entities table
		PopulateTables.populateHated_EntitiesTable(m_dbConn);
		//Populate  the Creature_Abilities table
		PopulateTables.populateCreature_AbilitiesTable(m_dbConn);

    }

    /**
     * This populates the User table.
     *
     * @return void
     */
    private static void populateUserTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO User ( First_Name, Last_Name ) VALUES "  +
            " ( 'John', 'Smith' ), " +
            " ( 'Brad', 'Olah' ),"
            + "('Jeff','Jeff') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("User table has been populated.");
    }

    /**
     * This populates the Staff_Abilities table.
     *
     * @return void
     */
    private static void populateStaff_AbilitiesTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Staff_Abilities ( ability ) VALUES "  +
            " ( 'Ban Player' ), " +
            " ( 'Ban Character' ), " +
			" ( 'Unban Player' ), " +
			" ( 'Unban Character' ), " +
			" ( 'Change Player Info'),"+
			" ( 'Create Moderator'),"+
			" ( 'Delete Moderator')"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Staff_Abilities table has been populated.");
    }

    /**
     * This populates the Location table.
     *
     * @return void
     */
    private static void populateLocationTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Location (Size, Type ) VALUES "  +
            " ( '10', 'Forest' ), " +
            " ( '20', 'Mountain' ), " +
            " ( '15', 'Jungle' ), "+
            " (	'5' , 'Forest')"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("LOCATION table has been populated.");
    }

    /**
     * This populates the Entity table.
     *
     * @return void
     */
    private static void populateEntityTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Entity ( Max_HP, Current_HP, Strength, Stamina, locationID ) VALUES "  +
            " ( '50', '50', '10', '15', '1' ), " +
            " ( '50', '50', '20', '20', '2' ), " +
            " ( '100', '100', '20', '20', '2' ) "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Entity table has been populated.");
    }

    /**
     * This populates the Ability table.
     *
     * @return void
     */
    private static void populateAbilityTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Ability ( Ability_Name, Type, Target_Stat, Effect_Amount, Duration, Frequency, Execution_Time ) VALUES "  +
            " ( 'Attack', '1', 'Current_HP', '-5', '1', '1', '1' ), " +
			" ( 'Heal', '2', 'Current_HP', '5', '1', '1', '1' ), " +
			" ( 'DoT Attack', '1', 'Current_HP', '-1', '5', '1', '1' ) "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Ability table has been populated.");
    }

    /**
     * This populates the Generic_Item table.
     *
     * @return void
     */
    private static void populateGeneric_ItemTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Generic_Item ( Item_Name, Volume, Weight ) VALUES "  +
            " ( 'Book', '1', '1' ), " +
			" ( 'Sword', '5', '5' ), " +
			" ( 'Backpack', '10', '20'  ), " +
			" ( 'Helmet', '5', '5'  ), "+
			"(	'Boots'	,	5	,	5),"+
			"(	'Pants'	,	3	,	3),"+
			"(	'Armor'	,	5	,	10),"+
			"(	'Shield'	,	6	,	3)"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Generic_Item table has been populated.");
    }

	 /**
     * This populates the Moderator_Account table.
     *
     * @return void
     */
    private static void populateModerator_AccountTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Moderator_Account ( userID, Email, Username, Password ) VALUES "  +
            " ( '1', 'blah@blah.com', 'Moderator_Man', '1234' ) "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Moderator_Account table has been populated.");
    }

	 /**
     * This populates the Manager_Account table.
     *
     * @return void
     */
    private static void populateManager_AccountTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Manager_Account (userID, Email, Username, Password ) VALUES "  +
            " ( '2', 'blah@gmail.com', 'Manager_Man', 'password123') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Manager_Account table has been populated.");
    }

	 /**
     * This populates the Player_Account table.
     *
     * @return void
     */
    private static void populatePlayer_AccountTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Player_Account (userID, Email, Username, Password ) VALUES "  +
            " ( '3', 'blah@yahoo.com', 'Player_Man', '1337') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Player_Account table has been populated.");
    }

	 /**
     * This populates the Staff_Abilities_Link table.
     *
     * @return void
     */
    private static void populateStaff_Abilities_LinkTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Staff_Abilities_Link (userID, abilityID ) VALUES "  +
            " ( '1', '1'), " +
			" ( '1', '2'), " +
			" ( '1', '3'), " +
			" ( '1', '4'), " +
			" ( '2', '1'), " +
			" ( '2', '2'), " +
			" ( '2', '3'), " +
			" ( '2', '4'), " +
			" ( '2', '5'), " +
			" ( '2', '6') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Staff_Abilities_Link table has been populated.");
    }

	/**
     * This populates the Location_Connections table.
     *
     * @return void
     */
    private static void populateLocation_ConnectionsTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Location_Connections (location_A_ID, location_B_ID ) VALUES "  +
            " ( '1', '2'), " +
			" ( '2', '3'), " +
			" ( '2', '4'), " +
			" ( '3', '4'), " +
			" ( '1', '4') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Location_Connections table has been populated.");
    }

	/**
     * This populates the Weapon table.
     *
     * @return void
     */
    private static void populateWeaponTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Weapon (itemID, abilityID ) VALUES "  +
            " ( '2', '1') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Weapon table has been populated.");
    }

	/**
     * This populates the Armor table.
     *
     * @return void
     */
    private static void populateArmorTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Armor (itemID, Defense_Amount ) VALUES "  +
            " ( '4', '5'), " +
			" ( '5', '2'), " +
			" ( '6', '2'), " +
			" ( '7', '10') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Armor table has been populated.");
    }

	/**
     * This populates the Container table.
     *
     * @return void
     */
    private static void populateContainerTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Container (itemID, Weight_Limit, Volume_Limit ) VALUES "  +
            " ( '3', '100', '200' ) "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Container table has been populated.");
    }

	/**
     * This populates the Stored_Items table.
     *
     * @return void
     */
    private static void populateStored_ItemsTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Stored_Items ( containerID, itemID ) VALUES "  +
            " ( '3', '1'), " +
			" ( '3', '2'), " +
			" ( '3', '4') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Stored_Items table has been populated.");
    }

	/**
     * This populates the Items_in_Location table.
     *
     * @return void
     */
    private static void populateItems_in_LocationTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Items_in_Location (itemID, locationID ) VALUES "  +
            " ( '1', '3'), " +
			" ( '2', '2'), " +
			" ( '3', '1') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Items_in_Location table has been populated.");
    }

	/**
     * This populates the Game_Character table.
     *
     * @return void
     */
    private static void populateGame_CharacterTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Game_Character (	characterID	,	Character_Name	,	ownerID	,	Left_Hand_itemID	,	Right_Hand_itemID	,	Head_itemID	,	Body_itemID	,	Leg_itemID	,	Boot_itemID	,	Container_itemID	) VALUES "  +
            " ( '1', 'Character_1', '3', '2', '8', '4', '7', '6', '5', '3' )"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Game_Character table has been populated.");
    }

	/**
     * This populates the Creature table.
     *
     * @return void
     */
    private static void populateCreatureTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Creature (creatureID, Defense_Amount ) VALUES "  +
            " ( '2', '5'), " +
			" ( '3', '10') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Creature table has been populated.");
    }

	/**
     * This populates the Preferred_Locations table.
     *
     * @return void
     */
    private static void populatePreferred_LocationsTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Preferred_Locations (creatureID, locationID ) VALUES "  +
            " ( '2', '3'), " +
			" ( '2', '4') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Preferred_Locations table has been populated.");
    }

	/**
     * This populates the Liked_Entities table.
     *
     * @return void
     */
    private static void populateLiked_EntitiesTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Liked_Entities (creatureID, entityID ) VALUES "  +
            " ( '2', '3'), " +
			" ( '3', '2') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Liked_Entities table has been populated.");
    }

	/**
     * This populates the Hated_Entities table.
     *
     * @return void
     */
    private static void populateHated_EntitiesTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Hated_Entities (creatureID, entityID ) VALUES "  +
            " ( '2', '1'), " +
			" ( '3', '1') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Hated_Entities table has been populated.");
    }

	/**
     * This populates the Creature_Abilities table.
     *
     * @return void
     */
    private static void populateCreature_AbilitiesTable(Connection m_dbConn) throws SQLException {
        String populateQuery =
            "INSERT INTO Creature_Abilities (creatureID, abilityID ) VALUES "  +
            " ( '2', '1'), " +
			" ( '3', '2'), " +
			" ( '3', '3') "
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(populateQuery);
        stmt.executeUpdate();

        System.out.println("Creature_Abilities table has been populated.");
    }
}
