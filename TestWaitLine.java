
public class TestWaitLine
{
    public static void main (String [] args)
    {
        WaitLine customerLine = new WaitLine ();
        //  Simulate a waiting line with 20 minutes, 50% arrival probability,
        //  and 5 minute maximum transaction time
        customerLine.simulate(25, 0.5);

        customerLine.displayResults();
    }
}