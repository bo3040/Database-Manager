package main;

/**
 * A class that holds the information of a tables fields.
 * @author bo3040
 *
 */
public class FieldDetails
{

	/**
	 * Initializes the fields to hold the initialization values.
	 */
	public String field,type,canBeNull,key,defaultValue,extra;
	/**
	 * Initializes a new column.
	 * @param field - the name of the field.
	 * @param type - The data type of the field.
	 * @param canBeNull - If the field can be null.
	 * @param key - What type of key the field is.
	 * @param defaultValue - The default value of the field.
	 * @param extra
	 */
	public FieldDetails(String field, String type, String canBeNull, String key, String defaultValue, String extra)
	{
		this.field=field;
		this.type = type;
		this.canBeNull = canBeNull;
		this.key = key;
		this.defaultValue = defaultValue;
		this.extra = extra;
	}

	/** (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString()
	{
		return field+" "+type+" "+canBeNull+" "+key+" "+defaultValue+" "+extra;
	}
}
