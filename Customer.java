public class Customer
{
    private int number, arrivalTime, transactionTime;
    private static int customerCount = 0;

    //  Constructor
    Customer (int newArrivalTime, int newTransactionTime)
    {
        arrivalTime = newArrivalTime;
        transactionTime = newTransactionTime;
        number = ++customerCount;
    }

    public int getTransactionTime()
    {
        return transactionTime;
    }

    public int getArrivalTime()
    {
        return arrivalTime;
    }

    public int getCustomerNumber()
    {
        return number;
    }
}
