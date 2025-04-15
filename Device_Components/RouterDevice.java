package Device_Components;
import Utilities.TimerDelay;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class RouterDevice extends NetworkComponent
{
    //Attributes
    private int reEnterNameIDAndDesc;
    private ArrayList<Long> Ports = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // Constructor for Router
    public RouterDevice(long componentID, String name, String description)
    {
        super(componentID, name, description);
    }

    // Method to get ports for saving
    public ArrayList<Long> getPorts()
    {
        return Ports;
    }

    // Method to add a port for loading
    public void addPort(long portID)
    {
        if (!Ports.contains(portID))
        {
            Ports.add(portID);
        }
    }

    //Method to Configure the ports in the router
    public void configureRouterPorts()
    {
        int numberOfPortsToConfig;
        while (true)
        {
            try
            {
                System.out.print("Enter the number of ports to configure: ");
                numberOfPortsToConfig = input.nextInt();
                input.nextLine(); // Consume newline
                if (numberOfPortsToConfig < 0)
                {
                    throw new IllegalArgumentException("Value Can't be Less than 0");
                }
                break;
            }
            catch(InputMismatchException e)
            {
                System.out.println("Enter Valid Data Type\n");
                TimerDelay.introduceDealy1Secs();
            }
            catch(IllegalArgumentException e)
            {
                System.out.println(e.getMessage());
                TimerDelay.introduceDealy1Secs();
            }
        }

        System.out.println("Do you want to Re-Enter the device ID, name & description ?");
        TimerDelay.introduceDealy1Secs();
        System.out.println("1.Yes");
        System.out.println("2.No");
        System.out.print("Enter your choice: ");
        reEnterNameIDAndDesc = input.nextInt();
        if(reEnterNameIDAndDesc == 1)
        {
        long componentID;
        while (true)
        {
            System.out.print("Enter ID for the Router: ");
            componentID = input.nextLong();
            input.nextLine(); // Consume newline
            if (componentID < 0)
            {
                System.out.println("Component ID must be a non-negative number.");
                TimerDelay.introduceDealy1Secs();
            }
            else
            {
                break;
            }
        }
        setComponentID(componentID);

        String name;
        while (true)
        {
            System.out.print("Enter the Name of the Router: ");
            name = input.nextLine();
            if (name.isEmpty())
            {
                System.out.println("Name cannot be empty. Please enter a valid name.");
                TimerDelay.introduceDealy1Secs();
            }
            else
            {
                break;
            }
        }
        setComponentName(name);

        String description;
        while (true)
        {
            System.out.print("Enter the description of the Router (Enter Key = Skip): ");
            description = input.nextLine();
            if (description.isEmpty())
            {
                description = "No Description";
                break;
            }
            else
            {
                break;
            }
        }
        setComponentDescription(description);
    }

        System.out.println("Enter Port IDs");
        for (int i = 0; i < numberOfPortsToConfig; i++)
        {
            long tempIdHolder;
            while (true)
            {
                System.out.print("Enter the ID for Port " + (i + 1) + ": ");
                tempIdHolder = input.nextLong();
                if (tempIdHolder < 0)
                {
                    System.out.println("Please enter a valid port ID (greater than 0).");
                    TimerDelay.introduceDealy1Secs();
                }
                else if (Ports.contains(tempIdHolder))
                {
                    System.out.println("This port ID already exists. Please enter a unique port ID.");
                    TimerDelay.introduceDealy1Secs();
                }
                else
                {
                    System.out.println("Loading...");
                    TimerDelay.introduceDealy1Secs();
                    Ports.add(tempIdHolder);
                    break;
                }
            }
        }
        System.out.println("All ports configured for Router " + getComponentName() + " (ID: " + getComponentID() + ").");
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        RouterDevice that = (RouterDevice) obj;
        return Ports.equals(that.Ports);
    }
}