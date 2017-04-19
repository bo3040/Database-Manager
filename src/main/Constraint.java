package main;

/**
 * Holds the logic for storing a constraint.
 * @author Brad Olah
 */
public class Constraint
{
	public String tableName, constraintType,constraintName,referencedTableName,referencedColumnName,columnName;

	/**
	 * Creates a new constraint with the given values.
	 * @param tableName - The name of the table the constraint is from.
	 * @param constraintType - The type of constraint.
	 * @param constraintName - The name of the constraint.
	 * @param referencedTableName - The table the constraint references.
	 * @param referencedColumnName - The column in the referenced table the constraint is linked with.
	 * @param columnName - The column in the table the key is linked to.
	 */
	public Constraint(String tableName, String constraintType, String constraintName, String referencedTableName, String referencedColumnName, String columnName)
	{
		this.tableName = tableName;
		this.constraintType = constraintType;
		this.constraintName = constraintName;
		this.referencedTableName = referencedTableName;
		this.referencedColumnName = referencedColumnName;
		this.columnName = columnName;
	}

	/**
	 * Returns the values in the constraint as a string.
	 */
	@Override
	public String toString()
	{
		return "Table Name: "+tableName+"Constraint Type: "+constraintType+"Constraint Name: "+constraintName+"Referenced Table Name: "+referencedTableName+"referencedColumnname: "+ referencedColumnName;

	}
}
