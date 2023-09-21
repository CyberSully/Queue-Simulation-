/** Simulates a waiting line. 
 @author Brett Sullivan
 @Modified on Nov 22, 2022
 */
import java.util.Scanner;
public class WaitLine
{ //still need to add a display message for enqueue each time, what the customer is doing and how long it takes
    //figure out how to make the queue have exactly 10 people in it
    private QueueInterface<Customer> line;
    private int numberOfArrivals;
    private int numberServed;
    private int totalTimeWaited;
    private Customer currentCustomer = null;
    public WaitLine()
    {
        line = new LinkedQueue<>();
        reset();
    } // end default constructor
    /** Simulates a waiting line with one serving agent.
     @param duration  The number of minutes in the simulation
     @param arrivalProbability  A real number between 0 and 1, representing
     the probability that a customer arrives at
     a given time
     */

    public void simulate(int duration, double arrivalProbability)
    {
        int transactionTimeLeft = 0;
        int newTransactionTime = 0;
        boolean printed = false;
        currentCustomer = null;
        String userAction = null;
        for (int clock = 0; clock < duration; clock++)
        {
            if (Math.random() < arrivalProbability)
            {
                Scanner input = new Scanner (System.in);
                //returning is 2 clock ticks
                //super saver, ask how many items, which item they want for free, time is number of items + 1
                //purchasing, ask how many items they have, amount of items is the time it takes
                System.out.println("Enter 1 to return item");
                System.out.println("Enter 2 to user Super Saver");
                System.out.println("Enter 3 to purchase items");
                int customerChoice  = input.nextInt();
                if(customerChoice == 1){
                    System.out.println("Enter a number for item you are returning");
                    int returnedItem = input.nextInt();
                    newTransactionTime = 2;
                    userAction = "returning item";
                }
                else if (customerChoice == 2){
                    System.out.println("You chose Super Saver, how many items do you have?");
                    int numOfItems = input.nextInt();
                    System.out.println("Enter a number of the item you would like for free");
                    int freeItem = input.nextInt();
                    newTransactionTime = numOfItems + 1;
                    userAction = "using Super Saver";
                } else if (customerChoice == 3) {
                    System.out.println("You chose to purchase, how many items would you like to purchase?");
                    int numOfPurchase = input.nextInt();
                    newTransactionTime = numOfPurchase;
                    userAction = "purchasing item";
                }
                else {
                    System.out.println("You entered a wrong number...Try again");
                    //reset();
                }
                //  Add new customer to queue
                numberOfArrivals++;

                //Check this line below  to see if we need it at all
                int transactionTime = newTransactionTime;
                Customer nextArrival = new Customer(clock, transactionTime);
                line.enqueue(nextArrival);
                System.out.println("Customer " + nextArrival.getCustomerNumber() +
                        " enters line at time " + clock + " user is " + userAction +
                        ". Transaction time required is " + transactionTime + ".");
            } // end if
            if (transactionTimeLeft > 0)
                transactionTimeLeft--;
            else
            {
                if (!printed)
                {
                    if (currentCustomer != null)
                    {
                        System.out.println("Customer "
                                + currentCustomer.getCustomerNumber()
                                + " exits queue at time " + clock + ".");
                        printed = true;
                    }
                }
                if (!line.isEmpty())
                {
                    //  Process next customer in queue
                    currentCustomer = line.dequeue();
                    printed = false;
                    transactionTimeLeft = currentCustomer.getTransactionTime() - 1;
                    int timeWaited = clock - currentCustomer.getArrivalTime();
                    totalTimeWaited += timeWaited;
                    numberServed++;
                    System.out.println("Customer " + currentCustomer.getCustomerNumber()
                            +
                            " begins service at time " + clock +
                            ". Time waited is " + timeWaited + ".");
                }
            } // end if
        } // end for
        if (transactionTimeLeft > 0)
            System.out.println("Customer " + currentCustomer.getCustomerNumber() +
                            " is still being served but simulation has ended.");
                                numberServed--;
    } // end simulate
    /** Displays summary results of the simulation. */
    public void displayResults()
    {
        System.out.println();
        System.out.println("Number served = " + numberServed);
        System.out.println("Total time waited = " + totalTimeWaited);
        double averageTimeWaited = ((double)totalTimeWaited) / numberServed;
        System.out.format("Average time waited = %5.2f\n", averageTimeWaited);
        int leftInLine = numberOfArrivals - numberServed;
        System.out.println("Number left in line = " + leftInLine);
        if (!line.isEmpty())
        {
            System.out.println ("Customers left in line:");
            System.out.println ("   " + currentCustomer.getCustomerNumber());
            while (!line.isEmpty())
            {
                Customer tempCustomer = line.dequeue();
                System.out.println ("   " + tempCustomer.getCustomerNumber());
            }
        }
    } // end DisplayResults
    /** Initializes the simulation. */
    public final void reset()
    {
        line.clear();
        numberOfArrivals = 0;
        numberServed = 0;
        totalTimeWaited = 0;
    } // end reset
} // end WaitLine