/**
 * This class represents a Rent object
 * @author Noam Gil
 * @version (5.0)
 * Date of submission: 25/11/2022
 */
public class Rent           // This class contains all the attributes and methods of the object rent.
{

    private String _name;       // The name of the user.

    private Car _car;           // The car that the user booked.

    private Date _pickDate;     // The date that the user take the car from the agency.

    private Date _returnDate;  //  The date that the user return the car to the agency.

    /*----------------------------------------FINALS--------------------------------------------------*/

    private static final int PERIOD_OF_DISCOUNT = 7;  // The fixed period of time in days that the discount is applied.

    private static final int PERCENTAGE_OF_DISCOUNT = 10;
    private static final int RATE_OF_TYPE_A = 100; // The rate of a type A car

    private static final int RATE_OF_TYPE_B = 150; // The rate of a type B car

    private static final int RATE_OF_TYPE_C = 180; // The rate of a type C car

    private static final int RATE_OF_TYPE_D = 240; // The rate of a type D car

    private static final double DISCOUNT_OF_A =  ((RATE_OF_TYPE_A * PERIOD_OF_DISCOUNT) / 100.0) * PERCENTAGE_OF_DISCOUNT; // The discount of a car A type for one unit of discount period.

    private static final double DISCOUNT_OF_B = ((RATE_OF_TYPE_B * PERIOD_OF_DISCOUNT) / 100.0) * PERCENTAGE_OF_DISCOUNT; // The discount of a car B type for one unit of discount period.

    private static final double DISCOUNT_OF_C = ((RATE_OF_TYPE_C * PERIOD_OF_DISCOUNT) / 100.0) * PERCENTAGE_OF_DISCOUNT; // The discount of a car C type for one unit of discount period.

    private static final double DISCOUNT_OF_D = ((RATE_OF_TYPE_D * PERIOD_OF_DISCOUNT) / 100.0) * PERCENTAGE_OF_DISCOUNT; // The discount of a car D type for one unit of discount period.


    /*---------------------------------------Constructors---------------------------------------------*/

    /**
     * Creates a new Rent object
     * The return date must be at least one day after the pickup date, otherwise set it to one day after the pick up date.
     * @param name the client's name
     * @param car the rented car
     * @param pick the pickup date
     * @param ret the return date
     */
    public Rent (String name, Car car, Date pick, Date ret)  // The constructor. Creates rent object using the user input.
    {
        if (pick.before(ret))           // If the return date is not valid, it sets it to be the day after the pickup date.
            _returnDate = new Date(ret);
        else
            _returnDate = new Date(pick.tomorrow());
        _name = name;
        _car = new Car(car);
        _pickDate = new Date(pick);
    }

    /**
     * Copy constructor
     * @param other the rent to be copied
     */
    public Rent(Rent other)  // Copy constructor.
    {
        _name = other._name;
        _car = new Car(other._car);
        _pickDate = new Date(other._pickDate);
        _returnDate = new Date(other._returnDate);
    }


    /*-----------------------------------------Getters----------------------------------------------*/

    /**
     * Gets the car
     * @return the car
     */
    public Car getCar()
    {
        return new Car(_car);
    }

    /**
     * Gets the name
     * @return the name
     */
    public String getName() {
        return _name;
    }

    /**
     * Gets the pickup date
     * @return the pickup date
     */
    public Date getPickDate() {
        return new Date(_pickDate);
    }

    /**
     * Gets the return date
     * @return the return date
     */
    public Date getReturnDate() {
        return new Date(_returnDate);
    }

    /*----------------------------------------Setters-----------------------------------------------*/

    /**
     * Sets the rented car
     * @param car the rented car (You can assume that car is not null)
     */
    public void setCar(Car car)
    {
        _car = new Car(car);
    }

    /**
     * Sets the client name
     * @param name the client name (You can assume that the name is a valid name)
     */
    public void setName(String name)
    {
        _name = name;
    }

    /**
     * Sets the pickup date
     * The pickup date must be at least one day before the return date, otherwise - don't change the pickup date
     * @param pickDate the pickup date (You can assume that pick up date is not null)
     */
    public void setPickDate(Date pickDate)
    {
        if (pickDate.before(_returnDate))   // If the given pickup date is not valid it does not change it.
            _pickDate = new Date(pickDate);
    }

    /**
     * Sets the return date
     * The return date must be at least one day after the pickup date, otherwise - don't change the return date
     * @param returnDate the return date (You can assume that return date is not null)
     */
    public void setReturnDate(Date returnDate)
    {
        if (returnDate.after(_pickDate))   // If the given return date is not valid it does not change it.
            _returnDate = new Date(returnDate);
    }

    /*----------------------------------More methods-------------------------------------------*/
    /**
     * Check if 2 rents are the same
     * @param other the rent to compare this rent to
     * @return true if the rents are the same
     */
    public boolean equals(Rent other)   // Checks if two rents objects are equal.
    {
        return ((_name.equals(other._name)) &&
                (_car.equals(other._car)) &&
                _pickDate.equals(other._pickDate)
                && _returnDate.equals(other._returnDate));
    }

    /**
     * Returns the number of rent days
     * @return the number of rent days
     */
    public int howManyDays() { return _pickDate.difference(_returnDate); }        // Calculates the gap from pickup to return time in days.

    /**
     * Returns the rent total price
     * @return the rent total price
     */
    public int getPrice()   // Returns the cost of the rent considering the car type, the period of the rent and the discount policy.
    {
        int priceRaw = 0; // The price before discount.
        double discount = 0; // The discount of the car for one unit of discount period.
        int discountPeriods = (this.howManyDays() / PERIOD_OF_DISCOUNT); // The amount of discount period units.
        switch (_car.getType()) {

            case ('A'):
                priceRaw = (this.howManyDays() * RATE_OF_TYPE_A);  // The price before discount.
                discount =  DISCOUNT_OF_A; // The discount of a car A type for one unit of discount period.
                break;
            case ('B'):
                priceRaw = (this.howManyDays() * RATE_OF_TYPE_B); // The price before discount.
                discount =  DISCOUNT_OF_B; // The discount of a car B type for one unit of discount period.
                break;
            case ('C'):
                priceRaw = (this.howManyDays() * RATE_OF_TYPE_C); // The price before discount.
                discount =  DISCOUNT_OF_C; // The discount of a car C type for one unit of discount period.
                break;
            case ('D'):
                priceRaw = (this.howManyDays() * RATE_OF_TYPE_D);
                discount =  DISCOUNT_OF_D; // The discount of a car D type for one unit of discount period.
                break;
        }
        return (int) (priceRaw - (discount * discountPeriods));
    }

    /**
     * Try to upgrade the car to a better car
     * If the given car is better than the current car of the rent, upgrade it and return the upgrade additional cost, otherwise - don't upgrade
     * @param car the car to upgrade to
     * @return the upgrade cost
     */
    public int upgrade(Car car)        // Gets a car object and returns the extra amount of money to be paid if it's a better car, otherwise it returns 0.
                                          // Might change to better car and still return 0 only if it changes it only from manual to auto gear.
                                          // Sets the better car to the rent object.
    {
        int upgradeCost = 0;
        Rent temp = new Rent(_name, car, _pickDate, _returnDate);
        if (car.better(_car))
        {
            upgradeCost = (temp.getPrice() - this.getPrice());
            this.setCar(car);
        }
        return upgradeCost;
    }

    /**
     * Check if there is a double listing of a rent for the same person and car with an overlap in the rental days
     * If there is - return a new rent object with the unified dates, otherwise - return null.
     * @param other the other rent
     * @return the unified rent or null
     */
    public Rent overlap(Rent other)      //Checks if the same user booked the same car for an overlapping period of time.
                                        // If so, it returns the merge of the two, otherwise it returns null.
    {
        Rent mergeDates = new Rent(this);
        if ( _car.equals(other._car) && _name.equals(other._name)) // Checks if it's the same name and car
        {
            if (((_pickDate.before(other._pickDate)) || (_pickDate.equals(other._pickDate))) && // The given period of time is shorter or equal to the period of the rent. Meaning one contains the other.
                    ((_returnDate.after(other._returnDate)) || (_returnDate.equals(other._returnDate))))
                return mergeDates;

            else if ((other._pickDate.before(_pickDate)) &&
                    ((other._returnDate.after(_returnDate)) || other._returnDate.equals(_returnDate))) // The given period of time is longer than the period of the rent. Meaning one contains the other.
                return other;

            else if (((other._pickDate.before(_pickDate)) || (other._pickDate.equals(_pickDate))) &&  // The given period of time starts before, or on the same time as the rent starts. And ends before the rent ends or on the same time that the rent starts.
                    ((other._returnDate.after(_pickDate) ||(other._returnDate.equals(_pickDate))) && (other._returnDate.before(_returnDate))))
            {
                mergeDates.setPickDate(other._pickDate);
                return mergeDates;

            }
            else if ((((other._pickDate.after(_pickDate)) || (other._pickDate.equals(_pickDate))) && // The given period of time starts after, or on the same time as the rent starts. And ends after the rent ends or on the same time that the rent starts.
                    ((other._pickDate.before(_returnDate)) || (other._pickDate.equals(_returnDate))) && (other._returnDate.after(_returnDate))))
            {
                mergeDates.setReturnDate(other._returnDate);
                return mergeDates;
            }
        }
        return null;   // Not overlapping.
    }

    /**
     * Returns a String that represents this rent
     * @return String that represents this rent in the following format:
     * Name:Rama From:30/10/2022 To:12/11/2022 Type:B Days:13 Price:1845
     */
    public String toString()  // Returns a string of the rent information.
    {
            String temp = new String();
            temp = "Name:" + _name + " " + "From:" + _pickDate + " To:" + _returnDate + " Type:" + _car.getType() +
                    " Days:" + this.howManyDays() + " Price:" + this.getPrice();
            return temp;
    }

}  // End class.



