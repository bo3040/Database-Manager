
// Author: Olah Brad and Owoseje Abiodun
// Instructor: Dr. Dudley Girard
// Course: Csc 570 ( Database Management Systems)
// Programming Assignment #2
// CreateTables.java
package buildDatabase;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CreateTables {

    /**
     * This executes the CreateTables execute method. It creates the tables selected from Homework 3.
     *
     * @return void
     */
    public static void execute(Connection m_dbConn) throws SQLException {
        // Create the User table
        CreateTables.createUserTable(m_dbConn);
        // Create the Staff_Abilities table
        CreateTables.createStaff_AbilitiesTable(m_dbConn);
        // Create the Location table
        CreateTables.createLocationTable(m_dbConn);
        // Create the Entity table
        CreateTables.createEntityTable(m_dbConn);
        // Create the Ability table
        CreateTables.createAbilityTable(m_dbConn);
		// Create the Generic_Item table
		CreateTables.createGeneric_ItemTable(m_dbConn);
		// Create the Moderator_Account table
		CreateTables.createModerator_AccountTable(m_dbConn);
		//Create the Manager_Account table
		CreateTables.createManager_AccountTable(m_dbConn);
		//Create the Player_Account table
		CreateTables.createPlayer_AccountTable(m_dbConn);
		//Create the Staff_Abilities_Link table
		CreateTables.createStaff_Abilities_LinkTable(m_dbConn);
		//Create the Location_Connections table
		CreateTables.createLocation_ConnectionsTable(m_dbConn);
		//Create the Weapon table
		CreateTables.createWeaponTable(m_dbConn);
		//Create the Armor table
		CreateTables.createArmorTable(m_dbConn);
		//Create the Container table
		CreateTables.createContainerTable(m_dbConn);
		//Create the Stored_Items table
		CreateTables.createStored_ItemsTable(m_dbConn);
		//Create the Items_in_Location table
		CreateTables.createItems_in_LocationTable(m_dbConn);
		//Create the Game_Character table
		CreateTables.createGame_CharacterTable(m_dbConn);
		//Create the Creature table
		CreateTables.createCreatureTable(m_dbConn);
		//Create the Preferred_Locations table
		CreateTables.createPreferred_LocationsTable(m_dbConn);
		//Create the Liked_Entities table
		CreateTables.createLiked_EntitiesTable(m_dbConn);
		//Create the Hated_Entities table
		CreateTables.createHated_EntitiesTable(m_dbConn);
		//Create the Creature_Abilities table
		CreateTables.createCreature_AbilitiesTable(m_dbConn);
    }

    /**
     * This creates the User table.
     *
     * @return void
     */
    private static void createUserTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE User( "  +
            "    userID INT NOT NULL AUTO_INCREMENT, " +
            "    First_Name VARCHAR(32) NOT NULL, " +
            "    Last_Name VARCHAR(32) NOT NULL, " +
            "    PRIMARY KEY (userID) " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("User table has been created.");
    }


    /**
     * This creates the Staff_Abilities table.
     *
     * @return void
     */
    private static void createStaff_AbilitiesTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Staff_Abilities ( "  +
            "    abilityID INT NOT NULL AUTO_INCREMENT, " +
            "    ability VARCHAR(32) NOT NULL, " +
			"	 PRIMARY KEY (abilityID) " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Staff_Abilities table has been created.");
    }

    /**
     * This creates the Location  table.
     *
     * @return void
     */
    private static void createLocationTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Location ( "  +
            "    locationID INT NOT NULL AUTO_INCREMENT, " +
            "    Size INT NOT NULL, " +
            "    Type VARCHAR(16) NOT NULL, " +
            "    PRIMARY KEY (locationID) " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("LOCATION table has been created.");
    }

    /**
     * This creates the Entity table.
     *
     * @return void
     */

	 private static void createEntityTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Entity ( "  +
            "    entityID INT NOT NULL AUTO_INCREMENT, " +
            "    Max_HP INT NOT NULL, " +
            "    Current_HP INT NOT NULL, " +
            "    Strength INT NOT NULL, " +
            "    Stamina INT NOT NULL, " +
			"	 locationID INT NOT NULL, " +
            "    PRIMARY KEY (entityID), " +
			"	 CONSTRAINT location_ID " +
			"	 FOREIGN KEY (locationID) REFERENCES Location (locationID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Entity table has been created.");
    }

	/**
     * This creates the Ability table.
     *
     * @return void
     */
    private static void createAbilityTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Ability ( "  +
            "    abilityID INT NOT NULL AUTO_INCREMENT, " +
            "    Ability_Name VARCHAR(32) NOT NULL, " +
            "    Type INT NOT NULL, " +
            "    Target_Stat VARCHAR(16) NOT NULL, " +
			"	 Effect_Amount INT NOT NULL, " +
			"	 Duration INT, " +
			"	 Frequency INT, " +
			"	 Execution_Time INT NOT NULL, " +
            "    PRIMARY KEY (abilityID) " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Ability table has been created.");
    }

    /**
     * This creates the Generic_Item table.
     *
     * @return void
     */
    private static void createGeneric_ItemTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Generic_Item ( "  +
            "    itemID INT NOT NULL AUTO_INCREMENT, " +
            "    item_Name  VARCHAR(32) NOT NULL, " +
            "    Volume INT NOT NULL, " +
            "    Weight INT NOT NULL, " +
            "    PRIMARY KEY (itemID) " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Generic_Item table has been created.");
    }
	 /**
     * This creates the Moderator_Account table.
     *
     * @return void
     */
    private static void createModerator_AccountTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Moderator_Account ( "  +
            "    userID INT NOT NULL, " +
            "    Email VARCHAR(32) NOT NULL UNIQUE, " +
            "    Username VARCHAR(16) NOT NULL, " +
            "    Password VARCHAR(16) NOT NULL, " +
			"	 CONSTRAINT moderator_owner_ID " +
			"	 FOREIGN KEY(userID)REFERENCES 	User(userID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Moderator_Account table has been created.");
    }

	/**
     * This creates the Manager_Account table.
     *
     * @return void
     */
    private static void createManager_AccountTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Manager_Account ( "  +
            "    userID INT NOT NULL, " +
			"	 Email VARCHAR(32) NOT NULL UNIQUE, " +
			"	 Username VARCHAR(16) NOT NULL, " +
			"	 Password VARCHAR(16) NOT NULL, " +
			"	 CONSTRAINT manager_owner_ID " +
			"	 FOREIGN KEY(userID)REFERENCES 	User(userID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Manager_Account table has been created.");
    }

	/**
     * This creates the Player_Account table.
     *
     * @return void
     */
    private static void createPlayer_AccountTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Player_Account ( "  +
            "    userID INT NOT NULL, " +
			"	 Email VARCHAR(32) NOT NULL UNIQUE, " +
			"	 Username VARCHAR(16) NOT NULL, " +
			"	 Password VARCHAR(16) NOT NULL, " +
			"	 CONSTRAINT player_owner_ID " +
			"	 FOREIGN KEY(userID)REFERENCES 	User(userID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Player_Account table has been created.");
    }

	/**
     * This creates the Staff_Abilities_Link table.
     *
     * @return void
     */
    private static void createStaff_Abilities_LinkTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Staff_Abilities_Link ( "  +
            "    userID INT NOT NULL, " +
			"	 abilityID INT NOT NULL, " +
			"	 CONSTRAINT staff_ID " +
			"	 FOREIGN KEY(userID)REFERENCES 	User(userID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT staff_ability_ID " +
			"	 FOREIGN KEY(abilityID)REFERENCES Staff_Abilities(abilityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Staff_Abilities_Link table has been created.");
    }

	/**
     * This creates the Location_Connections table.
     *
     * @return void
     */
    private static void createLocation_ConnectionsTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Location_Connections ( "  +
            "    location_A_ID INT NOT NULL, " +
			"	 Location_B_ID INT NOT NULL, " +
			"	 CONSTRAINT location_A_ID " +
			"	 FOREIGN KEY(location_A_ID)REFERENCES 	Location(locationID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT location_B_ID " +
			"	 FOREIGN KEY(location_B_ID)REFERENCES Location(locationID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Location_Connections table has been created.");
    }

	/**
     * This creates the Weapon table.
     *
     * @return void
     */
    private static void createWeaponTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Weapon ( "  +
            "    itemID INT NOT NULL, " +
			"	 abilityID INT NOT NULL, " +
			"	 CONSTRAINT weapon_item_ID " +
			"	 FOREIGN KEY(itemID)REFERENCES 	Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT weapon_ability_ID " +
			"	 FOREIGN KEY(abilityID)REFERENCES Ability(abilityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Weapon table has been created.");
    }

	/**
     * This creates the Armor table.
     *
     * @return void
     */
    private static void createArmorTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Armor ( "  +
            "    itemID INT NOT NULL, " +
			"	 Defense_Amount INT NOT NULL, " +
			"	 CONSTRAINT armor_item_ID " +
			"	 FOREIGN KEY(itemID)REFERENCES 	Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Armor table has been created.");
    }
	/**
     * This creates the Container table.
     *
     * @return void
     */
    private static void createContainerTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Container ( "  +
            "    itemID INT NOT NULL, " +
			"	 Weight_Limit INT NOT NULL, " +
			"	 Volume_Limit INT NOT NULL, " +
			"	 CONSTRAINT container_item_ID " +
			"	 FOREIGN KEY(itemID)REFERENCES 	Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Container table has been created.");
    }
	/**
     * This creates the Stored_Items table.
     *
     * @return void
     */
    private static void createStored_ItemsTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Stored_Items ( "  +
            "    containerID INT NOT NULL, " +
			"	 itemID INT NOT NULL, " +
			"	 CONSTRAINT container_ID " +
			"	 FOREIGN KEY(containerID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT stored_item_ID " +
			"	 FOREIGN KEY(itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Stored_Items table has been created.");
    }
	/**
     * This creates the Items_in_Location table.
     *
     * @return void
     */
    private static void createItems_in_LocationTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Items_in_Location ( "  +
            "    itemID INT NOT NULL, " +
			"	 locationID INT NOT NULL, " +
			"	 CONSTRAINT location_item_ID " +
			"	 FOREIGN KEY(itemID) REFERENCES	Generic_Item(itemID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT item_location_ID " +
			"	 FOREIGN KEY(locationID)REFERENCES Location(locationID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Items_in_Location table has been created.");
    }

	/**
     * This creates the Game_Character table.
     *
     * @return void
     */
    private static void createGame_CharacterTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Game_Character ( "  +
            "    characterID INT NOT NULL, " +
			"	 Character_Name VARCHAR(32) NOT NULL UNIQUE, " +
			"	 ownerID INT NOT NULL, " +
			"	 Left_Hand_itemID INT, " +
			"	 Right_Hand_itemID INT, " +
			"	 Head_itemID INT, " +
			"	 Body_itemID INT, " +
			"	 Leg_itemID INT, " +
			"	 Boot_itemID INT, " +
			"	 Container_itemID INT, " +
			"	 CONSTRAINT game_character_ID " +
			"	 FOREIGN KEY(characterID)REFERENCES Entity(entityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT character_owner_ID " +
			"	 FOREIGN KEY(ownerID)REFERENCES Player_Account(userID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT left_hand_Item " +
			"	 FOREIGN KEY(Left_Hand_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT right_hand_Item " +
			"	 FOREIGN KEY(Right_Hand_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT head_item " +
			"	 FOREIGN KEY(Head_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT body_item " +
			"	 FOREIGN KEY(Body_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT leg_item " +
			"	 FOREIGN KEY(Leg_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT boot_item " +
			"	 FOREIGN KEY(Boot_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT container_item " +
			"	 FOREIGN KEY(container_itemID)REFERENCES Generic_Item(itemID) " +
			"	 ON DELETE SET NULL " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Game_Character table has been created.");
    }

	 /* This creates the Creature table.
     *
     * @return void
     */
    private static void createCreatureTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Creature ( "  +
            "    creatureID INT NOT NULL, " +
			"	 Defense_Amount INT NOT NULL, " +
			"	 CONSTRAINT creature_ID " +
			"	 FOREIGN KEY(creatureID) REFERENCES	Entity(entityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Creature table has been created.");
    }

	/* This creates the Preferred_Locations table.
     *
     * @return void
     */
    private static void createPreferred_LocationsTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Preferred_Locations ( "  +
            "    creatureID INT NOT NULL, " +
			"	 locationID INT NOT NULL, " +
			"	 CONSTRAINT location_creature_ID " +
			"	 FOREIGN KEY(creatureID) REFERENCES	Creature(creatureID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT creature_location_ID " +
			"	 FOREIGN KEY(locationID) REFERENCES	Location(locationID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Preferred_Locations table has been created.");
    }
	/* This creates the Liked_Entities table.
     *
     * @return void
     */
    private static void createLiked_EntitiesTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Liked_Entities ( "  +
            "    creatureID INT NOT NULL, " +
			"	 entityID INT NOT NULL, " +
			"	 CONSTRAINT liked_creature_ID " +
			"	 FOREIGN KEY(creatureID) REFERENCES	Creature(creatureID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT liked_entity_ID " +
			"	 FOREIGN KEY(entityID) REFERENCES Entity(entityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Liked_Entities table has been created.");
    }

	/* This creates the Hated_Entities table.
     *
     * @return void
     */
    private static void createHated_EntitiesTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Hated_Entities ( "  +
            "    creatureID INT NOT NULL, " +
			"	 entityID INT NOT NULL, " +
			"	 CONSTRAINT hated_creature_ID " +
			"	 FOREIGN KEY(creatureID) REFERENCES	Creature(creatureID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT hated_entity_ID " +
			"	 FOREIGN KEY(entityID) REFERENCES Entity(entityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Hated_Entities table has been created.");
    }

	/* This creates the Creature_Abilities table.
     *
     * @return void
     */
    private static void createCreature_AbilitiesTable(Connection m_dbConn) throws SQLException {
        String createQuery =
            "CREATE TABLE Creature_Abilities ( "  +
            "    creatureID INT NOT NULL, " +
			"	 abilityID INT NOT NULL, " +
			"	 CONSTRAINT ability_creature_ID " +
			"	 FOREIGN KEY(creatureID) REFERENCES	Creature(creatureID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE, " +
			"	 CONSTRAINT creature_ability_ID " +
			"	 FOREIGN KEY(abilityID) REFERENCES Ability(abilityID) " +
			"	 ON DELETE CASCADE " +
			"	 ON UPDATE CASCADE " +
            ")"
        ;

        PreparedStatement stmt = m_dbConn.prepareStatement(createQuery);
        stmt.execute();

        System.out.println("Creature_Abilities table has been created.");
    }


}

