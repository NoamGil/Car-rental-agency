/**
 * This class represents a Car object
 * @author Noam Gil
 * @version (5.0)
 * Date of submission: 25/11/2022
 */

public class Car          // This class contains all the attributes and methods of the object Car.
{
    private int _id;        // The identification number of the car.
    private char _type;     // The type of the car.
    private String _brand;  // The brand of the car.
    private boolean _isManual;  // The gear of the car (manual or auto).

    /*----------------------------------Finals-----------------------------------*/
    private static final int DEFAULT_ID = 9999999;  // Default id. If the given id is not valid, this will be the car's id.

    private static final int ID_MAX_THRESHOLD =10000000;

    private static final int ID_MIX_THRESHOLD =999999;
    private static final char DEFAULT_TYPE = 'A';  // Default type. if the given type is not valid, this will be the car's type.

    /*----------------------------------Constructors--------------------------------------*/

    private Car()  // Constructor for internal use only.
    {
        _id = DEFAULT_ID;
        _type = DEFAULT_TYPE;
        _brand = "";
        _isManual = true;
    }
    /**
     * Creates a new car object
     * id should be a 7 digits number, otherwise set it to 9999999
     * type should be 'A','B','C' or 'D', otherwise set it to 'A'
     * @param id the id of the car (7 digits number)
     * @param type the type of the car ('A','B','C' or 'D')
     * @param brand the car's brand
     * @param isManual flag indicating if the car is manual
     */
   public Car(int id, char type, String brand, boolean isManual) // The constructor. Creates a new car object.
    {
      this();
      this.setId(id);
      this.setType(type);
      this.setBrand(brand);
      this.setIsManual(isManual);
    }

    /**
     * Copy constructor
     * @param other the car to be copied
     */
    public Car(Car other) // Copy constructor.
    {
        _id = other._id;
        _type = other._type;
        _brand = other._brand;
        _isManual = other._isManual;
    }
    /*---------------------------Getters------------------------*/
    /**
     * Gets the id
     * @return the id
     */

    public int getId() { return _id; }        // Returns the id number of the car.

    /**
     * Gets the type
     * @return the type
     */
    public char getType() { return _type; }   // Returns the type of the car.

    /**
     * Gets the brand
     * @return the brand
     */
    public String getBrand() { return _brand; } // Returns the brand of the car.

    /**
     * Gets the isManual flag
     * @return the isManual flag
     */
    public boolean isManual() { return _isManual; }  // Returns what gear does the car has. True for manual and false for auto.

    /*----------------------------Setters--------------------------*/

    /**
     * Sets the id (only if the given id is valid)
     * @param id  the id value to be set
     */
    public void setId(int id) {  if ((id  > ID_MIX_THRESHOLD) && (id < ID_MAX_THRESHOLD)) _id = id; }  // Checks if the given id number is valid and assigns it accordingly.

    /**
     *  Sets the type (only if the given type is valid)
     * @param type the type value to be set
     */
    public void setType(char type) { if ((type == 'A') || (type == 'B') || (type == 'C') || (type == 'D')) _type = type; }  // Checks if the type is valid, and sets it accordingly.

    /**
     *  Sets the brand of the car
     * @param brand the brand value to be set
     */
    public void setBrand(String brand) { _brand = brand; }        // assigns the given string to the car's brand.

    /**
     * Sets the isManual flag of the car
     * @param isManual the isManual flag to be set
     */
    public void setIsManual(boolean isManual) { _isManual = isManual; } // assigns the boolean value to the car's gear.

    /*---------------------More methods-----------------------------*/

    /**
     * Returns a String object that represents this car
     * @return String that represents this car in the following format:
     * id:1234567 type:B brand:Toyota gear:manual (or auto)
     */
    public String toString() // Return a string of the car's information.
    {
        String gear = (_isManual) ? "manual" : "auto";
        String info = "id:" + _id + " type:" + _type + " brand:" + _brand + " gear:" + gear;
        return info;
    }

    /**
     * Check if two cars are the same
     * Cars are considered the same if they have the same type, brand and gear
     * @param other the car to compare this car to
     * @return true if the cars are the same, otherwise false
     */
    public boolean equals(Car other) { return ((_type == other._type) && (_brand.equals(other._brand)) && (_isManual == other._isManual)); } // Checks if two cars are equals.

    /**
     * Check if this car is better than the other car
     * A car is considered better than another car if its type is higher.
     * If both cars have the same type, an automated car is better than a manual car.
     * @param other  car to compare this car to
     * @return true if this car is better than the other car, otherwise false
     */

    public boolean better(Car other) // Checks if the car is better than another car.
    {
        if (_type == other._type)
        {
            if ((_isManual == false) && (other._isManual == true))
                return true;
            else
                return false;
        }
        else
        {
            switch (_type)
            {
                case 'D':
                     if(other._type != 'D')
                         return true;
                case 'C':
                    if ((other._type != 'D') && (other._type != 'C'))
                        return true;
                case 'B':
                    if (other._type == 'A')
                        return true;
                default:
                    return false;
            }
        }
    }

    /**
     * Check if this car is worse than the other car
     * @param other car to compare this car to
     * @return true if this car is worse than the other car, otherwise false
     */
    public boolean worse (Car other) { return other.better(this); }  // Checks if the car is worse than another car by using the 'better' method.

}  //End class.


