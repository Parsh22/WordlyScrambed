// Demonstration of the MyTimer class.  Use this to see how MyTimer
// works so that you can use it in your program.
import java.io.*;
import java.util.*;
public class TimerTest
{
    public static void main(String[] args)
    {
    	MyTimer T = new MyTimer();  // Create MyTimer object
    	Scanner inScan = new Scanner(System.in);
  		testTimer(inScan, T);	// Demonstrate MyTimer
  		testTimer(inScan, T);	// Demonstrate again
    }
    
    public static void testTimer(Scanner S, MyTimer T)
    {
    	int inVal, count = 0;
    	System.out.println("How long will the timer run (in ms)?");
    	int duration = S.nextInt();
    	double dur = (double) duration / 1000;
    	T.set(duration);
    	System.out.println("You have " + dur + " seconds to enter as many ints as you can");
    	System.out.println("Separate them by spaces or newlines");
    	T.start();
    	
    	// Note the two calls to check() here.  This is necessary since when the MyTimer
    	// expires it does not interrupt the execution.  Thus, T.check() could be true at
    	// the beginning of the loop but be false by the time the user actually enters the
    	// value.  Make sure you take this approach in your program as well.
    	while (T.check())
    	{
    		inVal = S.nextInt();
    		if (T.check())
    		{
    			System.out.println("You entered: " + inVal);
    			count++;
    		}
    		else
    			System.out.println("Sorry, time is up!");
    	}
    	System.out.println("You entered " + count + " numbers!");
 	}   	
}
	