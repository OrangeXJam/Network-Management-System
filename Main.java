import Utilities.TimerDelay;
import java.util.Scanner;

public class Main
{
    public static void main(String[] args)
    {
        //Part 1
        NetworkManager manager = new NetworkManager();
        manager.loadFromFile();
        Scanner input = new Scanner(System.in);
        TimerDelay.hehehe();

        //Part 2
        while (true)
        {
            System.out.println("\n--- Network Manager ---");
            TimerDelay.introduceDealy1Secs();
            System.out.println("1. Add Device");
            System.out.println("2. Delete Device");
            System.out.println("3. Configure Device");
            System.out.println("4. Add Connection");
            System.out.println("5. Check Connectivity");
            System.out.println("6. View Devices");
            System.out.println("7. View Device Configuration");
            System.out.println("8. Save Changes");
            System.out.println("9. Exit");
            TimerDelay.introduceDealy1Secs();
            System.out.print("Enter your choice: ");

            //Part 3
            int choice;
            try
            {
                choice = input.nextInt();
                input.nextLine(); // Consume newline
            }
            catch (Exception niggaYouDumbAsHell)
            {
                System.out.println("Invalid input. Please enter a number between 1 and 9.");
                input.nextLine(); // Clear invalid input
                continue;
            }

            //Part 4
            switch (choice)
            {
                case 1: // Add Device
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.addDevice();
                break;

                case 2: //Delete Device
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.deleteDevice();
                break;

                case 3: // Configure Device
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.configureDevice();
                break;

                case 4: // Add Connection
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.addConnection();
                break;

                case 5: // Check Connectivity
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.checkConnectivity();
                break;

                case 6: // View Devices
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.viewDevices();
                break;

                case 7: // View Device Configuration
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.viewDeviceConfiguration();
                break;

                case 8: // Save Changes
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                manager.saveToFile();
                break;

                case 9: // Exit
                System.out.println("Exiting the N.M.S, Goodbye!.");
                TimerDelay.introduceDealy3Secs();
                input.close();
                return;

                default:
                System.out.println("Invalid choice. Please enter a number between 1 and 9.");
            }
        }
    }
}