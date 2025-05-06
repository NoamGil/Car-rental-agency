/**
 * This class represents a Date object
 * @author Noam Gil
 * @version (5.0)
 * Date of submission: 25/11/2022
 */
public class Date
{                               // This class contains all the attributes and methods of the object Date.
    private int _day;           // The day number.
    private int _month;         // The month number.
    private int _year;          // The year number.


    /*-----------------------------------FINALS-------------------------------------------------------*/
    private  static final int January = 1; // Represent January.
    private  static final int February = 2; // Represent February.
    private  static final int March = 3; // Represent March.
    private  static final int April = 4; // Represent April.
    private  static final int May = 5; // Represent May.
    private  static final int June = 6; // Represent June.
    private  static final int July = 7; // Represent July.
    private  static final int August = 8; // Represent August.
    private  static final int September = 9; // Represent September.
    private  static final int October = 10; // Represent October.
    private  static final int November = 11; // Represent November.
    private  static final int December = 12; // Represent December.

    private static final int YEAR_MIX_THRESHOLD =1000; // The minimum value of a year.

    private static final int YEAR_MAX_THRESHOLD =9999; // The maximum value of a year.

    private static final int LAST_MONTH = 12;      // The number of the last month of every year.

    private static final int LEAP_YEAR_DIVIDER1 = 400;  // A divider of only leap years.

    private static final int LEAP_YEAR_DIVIDER2 = 4;  // Another divider of leap years, only if not dived by the next divider.

    private static final int LEAP_YEAR_NOT_DIVIDER2 = 100;  // The next divider.
    private static final int DEFAULT_YEAR = 2000; // The default year.

    private static final int DEFAULT_MONTH = 1;   // The default month.

    private static final int DEFAULT_Day = 1;     // The default day.

    private static final int DEFAULT_FEBRUARY_DAYS = 28;            // The amount of days on february (not on a leap year).

    private static final int DEFAULT_FEBRUARY_DAYS_LEAP_YEAR = 29;  // The amount of days on february (on a leap year).

    private static final int DEFAULT_MONTHS_4_6_9_11 = 30;          // The amount of days on April, June, September and November.

    private static final int DEFAULT_MONTHS_1_3_5_7_8_10_12 = 31;   // The amount of days on January, March, May, July, August, October and December.

    private static final int DEFAULT_MAX_DAYS = 31;                 // The maximum amount of days on every month.

    private  static final int FIRST_TWO_DIGITS_NATURAL_NUMBER = 10; // first two digit natural number.

    /*----------------------------------Constructors--------------------------------------*/

    private Date()    // Constructor for internal use only.
    {
        _year = DEFAULT_YEAR;
        _month = DEFAULT_MONTH;
        _day = DEFAULT_Day;
    }
    /**
     * If the given date is valid - creates a new Date object, otherwise creates the date 1/1/2000
     * @param day the day in the month (1-31)
     * @param month the month in the year (1-12)
     * @param year the year (4 digits)
     */
    public Date(int day, int month, int year) // The constructor. Creates a new Date object.
    {
        this();
        this.setYear(year);
        this.setMonth(month);
        this.setDay(day);
        if ((this.getYear() != year) || (this.getMonth() != month) || (this.getDay() != day)) // Checks if the date is valid, and sets it accordingly.
        {
            this.setYear(DEFAULT_YEAR);   //  If the given numbers can not form a valid date, the date will be the default date 01/01/2000.
            this.setMonth(DEFAULT_MONTH);
            this.setDay(DEFAULT_Day);
        }
    }

    /**
     * Copy constructor
     * @param other the date to be copied
     */
    public Date(Date other)  // Copy constructor.
    {
        _day = other._day;
        _month = other._month;
        _year = other._year;
    }

    /*--------------------------------Getters-------------------------------*/

    /**
     * Gets the day
     * @return the day
     */
    public int getDay() { return _day; }    // Returns the day number of the date.

    /**
     * Gets the month
     * @return the month
     */
    public int getMonth() { return _month; }  // Returns the month number of the date.

    /**
     * Gets the year
     * @return the year
     */
    public int getYear() { return _year; }  // Returns the year number of the date.

    /*----------------------------------Setters----------------------------*/

    /**
     * Sets the year (only if date remains valid)
     * @param yearToSet the year value to be set
     */
    public void setYear(int yearToSet)                   // Sets a year number to the date.
    {
        if ((yearToSet >= YEAR_MIX_THRESHOLD) && (yearToSet <= YEAR_MAX_THRESHOLD))  // Checks if the year is valid.
            _year = yearToSet;                           // The input is correct, so the given year is assign to the date.
    }

    /**
     * Set the month (only if date remains valid)
     * @param monthToSet the month value to be set
     */
    public void setMonth(int monthToSet)                //Sets a month number to the date.
    {
        if ((monthToSet >= 1) && (monthToSet <= LAST_MONTH))   // Checks if the month is valid.
            _month = monthToSet;                       // The input is correct, so the given month is assign to the date.
    }

    /**
     * Set the day (only if date remains valid)
     * @param dayToSet the day value to be set
     */
    public void setDay(int dayToSet)            //Sets a different day to the date.
    {
        if ((dayToSet >= 1) && (dayToSet <= DEFAULT_MAX_DAYS)) // Checks if the day is valid.
        {
            int howManyDays = 0;                    // The total number of days in the current month and year.
            boolean leapYear = false;             // This variable is set to be true when the year is a leap year.

            if (((_year % LEAP_YEAR_DIVIDER1) == 0) || ((_year % LEAP_YEAR_DIVIDER2 == 0) && (_year % LEAP_YEAR_NOT_DIVIDER2 != 0))) // checks if the year is leap year.
                leapYear = true;         // The given year is indeed a leap year.

            switch (_month) { // Determines how many days the given month has.
                case (January):
                case (March):
                case (May):
                case (July):
                case (August):
                case (October):
                case (December):
                    howManyDays = DEFAULT_MONTHS_1_3_5_7_8_10_12; // The total number of days in the months above.
                    break;
                case (April):
                case (June):
                case (September):
                case (November):
                    howManyDays = DEFAULT_MONTHS_4_6_9_11; // The total number of days in the months above.
                    break;

                case (February):     // Represent February.
                    if (leapYear)               // If it's a leap year February has 29 day, and if it's not it has 28 days.
                        howManyDays = DEFAULT_FEBRUARY_DAYS_LEAP_YEAR;
                    else
                        howManyDays = DEFAULT_FEBRUARY_DAYS;
                    break;
            }
            if (dayToSet <= howManyDays)    // Checks if the given day is a number that can represent a day. It is limited by the total days in the specific month and year.
                _day = dayToSet;            // The input is correct, so the given day is set to the new date.
        }
    }

    /*--------------------------More methods------------------------------*/

    /**
     * Check if 2 dates are the same
     * @param other the date to compare this date to
     * @return true if the dates are the same, otherwise false
     */
    public boolean equals(Date other) //Checks if two dates are the same.
    {
        return ((_day == other._day) &&
                (_month == other._month) &&
                (_year == other._year));
    }

    /**
     * Check if this date is before other date
     * @param other date to compare this date to
     * @return true if this date is before other date, otherwise false
     */
    public boolean before(Date other)  // Checks if the date is before the given date.
    {
        if (_year < other._year)  // Checks if the year of the date is before the given one.
            return true;
         if ((_year == other._year) && //Checks if the two dates are on the same year, but the month is before the given month.
                 (_month < other._month))
            return true;
         if ((_year == other._year) &&  // Checks the last case, same year and month but the day is before the given day.
                 (_month == other._month)
                 && (_day < other._day))
            return true;
         return false; // None of the cases above, so the date is not before the given date.
    }

    /**
     * Check if this date is after other date
     * @param other date to compare this date to
     * @return true if this date is after other date, otherwise false
     */
    public boolean after(Date other) { return other.before(this); } // Checks if a date is after a given date by using the before method.

    private int calculateDate(int day, int month, int year) // Calculates how many days have passed since the start of the Christian count.
    {                                                       // This method was given.
        if (month < 3) {
            year--;
            month = month + 12;
        }
        return 365 * year + year / 4 - year / 100 + year / 400 + ((month + 1) * 306) / 10 + (day - 62);
    }

    /**
     * Calculates the difference in days between two dates
     * @param other the date to calculate the difference between
     * @return the number of days between the dates (non negative value)
     */
    public int difference(Date other) //Calculates how many days have passed between two dates.
    {
        int dayPassedFirst = this.calculateDate(_day, _month, _year);   // The amount ot days passed since this date to the start of the Christian count.
        int dayPassedSecond = other.calculateDate(other._day, other._month, other._year);    // The amount ot days passed since the other date to the start of the Christian count.
        return  Math.abs(dayPassedFirst - dayPassedSecond);  //  Returns a positive number that represent the amount of time in days between the two dates.
    }

    /**
     * Returns a String that represents this date
     * @return String that represents this date in the following format: day (2 digits) / month(2 digits) / year (4 digits) for example: 02/03/1998
     */
    public String toString() // Return a string of the date. The string format is: day/month/year.
    {
        String dayAdd0 = "";
        String monthAdd0 = "";
        if (_day < FIRST_TWO_DIGITS_NATURAL_NUMBER)
            dayAdd0 = "0";
        if (_month < FIRST_TWO_DIGITS_NATURAL_NUMBER)
            monthAdd0 = "0";
        String date = dayAdd0 + _day + "/" + monthAdd0 + _month + "/" + _year;
        return date;
    }

    /**
     * Calculate the date of tomorrow
     * @return the date of tomorrow
     */
    public Date tomorrow()              // Returns the date of the next day.
    {
        Date nextDate = new Date(this);      // Creates a new date using the current date data ( later on, returning it).
        if (_day < DEFAULT_FEBRUARY_DAYS)                            // If the current date's day is lower than 28, it means that the next date will not have a different month or year. So the next date day will be bigger by one.
        {
            nextDate._day = (_day + 1);
            return nextDate;
        }
        else                                     // The given day is 28, 29, 30 or 31.
        {
            nextDate.setDay((_day + 1));       // Tries to set the next day.
            if (nextDate._day != this._day)  // Checks if the day is valid. if it doesn't it will stay the same.
                return nextDate;
            else                             //Date should change (to the first day of the next month).  maybe year also.
            {
                if ((_month == LAST_MONTH)) //  Checks if the year should change as well to the next year.
                {
                    nextDate._day = 1;
                    nextDate._month = 1;
                    nextDate._year = (_year + 1);
                    return nextDate;

                }
                else                    // The year shouldn't change.
                {
                    nextDate._day = 1;
                    nextDate._month = ((_month + 1));
                    return nextDate;
                }
            }
        }
    }
} //End class.