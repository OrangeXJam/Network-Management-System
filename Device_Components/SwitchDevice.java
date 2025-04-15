package Device_Components;
import Utilities.DifferentTypes;
import Utilities.TimerDelay;
import java.util.ArrayList;
import java.util.Scanner;

public class SwitchDevice extends NetworkComponent
{
    private Scanner input = new Scanner(System.in);
    private int numberOfVLANSinSwitch;
    private ArrayList<DifferentTypes> SwitchList = new ArrayList<>();
    private int VLANid;
    private String VLANname;
    private int VLANconnectedDevices;

    //Constructor for switch
    public SwitchDevice(long componentID, String componentName)
    {
        super(componentID, componentName);
    }

    // Method to get VLANs for saving
    public ArrayList<DifferentTypes> getSwitchList()
    {
        return SwitchList;
    }

    // Method to add a VLAN for loading
    public void addVLAN(int vlanId, String vlanName, int connectedDevices)
    {
        if (!isVLANidUsed(vlanId))
        {
            SwitchList.add(new DifferentTypes(vlanId, vlanName, connectedDevices));
        }
    }

    private boolean isVLANidUsed(int vlanId)
    {
        for (DifferentTypes vlan : SwitchList)
        {
            if (vlan.getID() == vlanId)
            {
                return true;
            }
        }
        return false;
    }

    public void manageVLANS()
    {
        while (true)
        {
            System.out.print("Enter the number of VLANs in your Switch (Max is 5): ");
            numberOfVLANSinSwitch = input.nextInt();
            input.nextLine(); // Consume newline

            if (numberOfVLANSinSwitch > 5)
            {
                System.out.println("Max number of VLANs is 5. Please enter a valid amount.");
                TimerDelay.introduceDealy1Secs();
            }
            else if (numberOfVLANSinSwitch < 1)
            {
                System.out.println("Number of VLANs must be at least 1. Please enter a valid amount.");
                TimerDelay.introduceDealy1Secs();
            }
            else
            {
                break;
            }
        }

        for (int i = 0; i < numberOfVLANSinSwitch; i++)
        {
            while (true)
            {
                System.out.print("Enter VLAN " + (i + 1) + " ID: ");
                VLANid = input.nextInt();
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                input.nextLine(); // Consume newline
                if (VLANid < 0)
                {
                    System.out.println("VLAN ID must be not less than 0. Please try again.");
                    TimerDelay.introduceDealy1Secs();
                }
                else if (isVLANidUsed(VLANid))
                {
                    System.out.println("This VLAN ID already exists in this switch. Please enter a different ID.");
                    TimerDelay.introduceDealy1Secs();
                }
                else
                {
                    break;
                }
            }

            while (true)
            {
                System.out.print("Enter VLAN " + (i + 1) + " Name: ");
                VLANname = input.nextLine();
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                if (VLANname.isEmpty())
                {
                    System.out.println("VLAN Name cannot be empty. Please try again.");
                    TimerDelay.introduceDealy1Secs();
                }
                else
                {
                    break;
                }
            }

            while (true)
            {
                System.out.print("Enter Number of Devices Connected to VLAN " + (i + 1) + ": ");
                VLANconnectedDevices = input.nextInt();
                System.out.println("Loading...");
                TimerDelay.introduceDealy3Secs();
                input.nextLine(); // Consume newline
                if (VLANconnectedDevices < 0)
                {
                    System.out.println("Number of connected devices must be not less than 0. Please try again.");
                    TimerDelay.introduceDealy1Secs();
                }
                else
                {
                    System.out.println("Adding VLAN to Switch...");
                    TimerDelay.introduceDealy3Secs();
                    break;
                }
            }
            // Store VLAN data in SwitchList
            SwitchList.add(new DifferentTypes(VLANid, VLANname, VLANconnectedDevices));
        }
        System.out.println("VLANs are now configured for Switch " + getComponentName() + ".");
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        if (!super.equals(obj)) return false;
        SwitchDevice that = (SwitchDevice) obj;
        return SwitchList.equals(that.SwitchList);
    }
}