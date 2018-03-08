import java.io.IOException;
import java.time.LocalTime;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class shutdownCountdown {
	public static void main(String[] args) throws InterruptedException, IOException {
		Scanner input = new Scanner(System.in);
		Runtime runtime = Runtime.getRuntime();
		LocalTime currentTime;
	    	String timeUntilShutdown;
		Scanner delim;
		int hours;
		int minutes;
		LocalTime shutdownTime;

		System.out.println("                         Countdown to Shutdown");		
		System.out.println("-----------------------------------------------------------------------");
		System.out.println("Enter the amount of time until shutdown (formatted as 'Hours:Minutes').");
		timeUntilShutdown = input.nextLine();
		
	    	currentTime = LocalTime.now();
		currentTime = currentTime.truncatedTo(ChronoUnit.MINUTES);
		delim = new Scanner(timeUntilShutdown);
		input.close();
		delim.useDelimiter(":");
		hours = Integer.parseInt(delim.next());
		minutes = Integer.parseInt(delim.next());
		delim.close();
		shutdownTime = currentTime.plusHours(hours).plusMinutes(minutes);
		System.out.println("Your computer will shutdown in " + hours + "hrs " + minutes + "mins, at " + shutdownTime);
		
		while (hours > 0) {
			Thread.sleep(3600000);
			hours -= 1;
		}
		while (minutes > 0) {
			Thread.sleep(60000);
			minutes -= 1;

			if (minutes <= 0) {
				System.out.println("Shutting down");
				runtime.exec("shutdown -s -t 0");
			}
		}	
	}

}
