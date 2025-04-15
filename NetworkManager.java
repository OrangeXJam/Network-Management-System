import Device_Components.NetworkComponent;
import Device_Components.RouterDevice;
import Device_Components.SwitchDevice;
import Utilities.ConnectedDevices;
import Utilities.DifferentTypes;
import Utilities.TimerDelay;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

public class NetworkManager
{
    private ArrayList<ConnectedDevices> connectedDevices = new ArrayList<>();
    private ArrayList<NetworkComponent> devicesList = new ArrayList<>();
    private Scanner input = new Scanner(System.in);

    // Part 1 - Simple logging method Meow
    private void logAction(String action)
    {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Text_Files/logs.txt", true)))
        {
            String timestamp = java.time.LocalDateTime.now().toString();
            writer.write(timestamp + " - " + action + "\n");
        } catch (IOException e)
        {
            // Silently fail - we don't want logging to interrupt the program
        }
    }

    // Part 2 - Method to add a device (Router or Switch) Meow
    public void addDevice()
    {
        logAction("Started adding new device");
        int deviceType;
        long componentID;

        // Keep prompting the user until they select a valid device type (1 or 2)
        while (true)
        {
            System.out.println("Choose Device Type:");
            TimerDelay.introduceDealy1Secs();
            System.out.println("1. Router");
            System.out.println("2. Switch");
            TimerDelay.introduceDealy1Secs();
            System.out.print("Enter your choice: ");
        
            deviceType = input.nextInt();
            input.nextLine(); // Consume newline after nextInt()

            if (deviceType == 1 || deviceType == 2)
            {
                break; // Exit the loop if a valid device type (1 or 2) is selected
            }
            else
            {
                System.out.println("Invalid choice, Please enter 1 for Router or 2 for Switch.\n");
                TimerDelay.introduceDealy1Secs();
            }
        }

        while (true)
        {
            System.out.print("Enter Device ID: ");
            try
            {
                componentID = input.nextLong();
                input.nextLine(); // Consume newline

                if(componentID < 0)
                {
                    logAction("Error: Attempted to use negative ID: " + componentID);
                    throw new IllegalArgumentException("Component ID must be a non-negative number.");
                }

                // Check if the device ID already exists
                boolean exists = false;
                for (NetworkComponent device : devicesList)
                {
                    if (device.getComponentID() == componentID)
                    {
                        exists = true;
                        logAction("Error: Attempted to use duplicate ID: " + componentID);
                        break;
                    }
                }

                if (exists)
                {
                    System.out.println("This ID is already used, Please enter a unique ID.\n");
                    TimerDelay.introduceDealy1Secs();
                    continue;
                }
                
                break;  // Only break if ID is valid and unique
            }
            catch(InputMismatchException manIAmDead)
            { 
                logAction("Error: Invalid ID input format");
                System.out.println("Invalid Data Type, Enter Numbers only.\n"); 
                TimerDelay.introduceDealy1Secs();
                input.nextLine(); // Consume invalid input
            }
            catch(IllegalArgumentException youStupid)
            {
                System.out.println(youStupid.getMessage());
                TimerDelay.introduceDealy1Secs();
            }
        }

        String name;
        System.out.print("Enter Device Name: ");
        while(true)
        {
            name = input.nextLine();
            if (name.isEmpty())
            {
                System.out.println("Name Can't be Empty, Please try again.");
                System.out.println("Enter Device Name: ");
            }
            else
            {
            break;
            }
        }
        

    // Add Router or Switch based on user input
        while(true)
        {
            if (deviceType == 1)
            { // Router
                System.out.print("Enter Router Description: ");
                String description = input.nextLine();
                if(description.isEmpty())
                {
                    description = "No Description";
                }
                RouterDevice router = new RouterDevice(componentID, name, description);
                devicesList.add(router);
                System.out.println("Adding Router...");
                TimerDelay.introduceDealy3Secs();
                System.out.println("Router " + name + " added successfully.\n");
                TimerDelay.introduceDealy1Secs();
                logAction("Added Router - ID: " + componentID + ", Name: " + name);
                break;
            }
            else if (deviceType == 2)
            { // Switch
                SwitchDevice switchDevice = new SwitchDevice(componentID, name);
                devicesList.add(switchDevice);
                System.out.println("Adding Switch...");
                TimerDelay.introduceDealy3Secs();
                System.out.println("Switch " + name + " added successfully.\n");
                TimerDelay.introduceDealy1Secs();
                logAction("Added Switch - ID: " + componentID + ", Name: " + name);
                break;
            }
            else
            {
                System.out.println("Enter valid selection from the device list.\n");
                TimerDelay.introduceDealy1Secs();
            }
        }   
    }

    // Part 3 - Method to view all devices
    public void viewDevices()
    {
        if (devicesList.isEmpty())
        {
            System.out.println("No devices have been added yet.");
            TimerDelay.introduceDealy1Secs();
            return;
        }
        System.out.println("Added Devices:");
        displayDevices();
    }

    // Part 4 - Helper method to display devices along with their descriptions
    private void displayDevices()
    {
        for (int i = 0; i < devicesList.size(); i++)
        {
            NetworkComponent device = devicesList.get(i);
            String description = device.getComponentDescription(); // Retrieve the description of the device
            System.out.println((i + 1) + ". " + device.getComponentName() + " (ID: " + device.getComponentID() + ") - Description: " + description);
            TimerDelay.introduceDealy1Secs();
        }
    }

    // Part 5 - Method to view device configurations
    public void viewDeviceConfiguration()
        {
            if (devicesList.isEmpty())
            {
                System.out.println("No devices have been added yet.\n");
                TimerDelay.introduceDealy1Secs();
                return;
            }
    
            System.out.println("Available Devices:");
            displayDevices();
    
            System.out.print("Enter the ID of the device to view its configuration: ");
            long deviceId = input.nextLong();
            input.nextLine(); // Consume newline
    
            NetworkComponent device = findDeviceByID(deviceId);
            if (device == null)
            {
                System.out.println("No device found with ID: " + deviceId);
                return;
            }
    
            System.out.println("\nConfiguration for device: " + device.getComponentName() + " (ID: " + device.getComponentID() + ")");
            TimerDelay.introduceDealy1Secs();
    
            if (device instanceof RouterDevice)
            {
                RouterDevice router = (RouterDevice) device;
                System.out.println("Type: Router");
                System.out.println("Description: " + router.getComponentDescription());
                System.out.println("Configured Ports:");
                ArrayList<Long> ports = router.getPorts();
                if (ports.isEmpty())
                {
                    System.out.println("No ports configured");
                }
                else
                {
                    for (int i = 0; i < ports.size(); i++)
                    {
                        System.out.println("Port " + (i + 1) + " ID: " + ports.get(i));
                        TimerDelay.introduceDealy1Secs();
                    }
                }
            }
            else if (device instanceof SwitchDevice)
            {
                SwitchDevice switchDevice = (SwitchDevice) device;
                System.out.println("Type: Switch");
                System.out.println("Configured VLANs:");
                ArrayList<DifferentTypes> vlans = switchDevice.getSwitchList();
                if (vlans.isEmpty())
                {
                    System.out.println("No VLANs configured");
                }
                else
                {
                    for (DifferentTypes vlan : vlans)
                    {
                        System.out.println("\nVLAN ID: " + vlan.getID());
                        System.out.println("VLAN Name: " + vlan.getName());
                        System.out.println("Connected Devices: " + vlan.getNumberOfDevciesConnected());
                        TimerDelay.introduceDealy1Secs();
                    }
                }
            }
            System.out.println(); // Empty line for better readability
        }

    // Part 6 - Method to configure a specific device
    public void configureDevice()
    {
        if (devicesList.isEmpty())
        {
            System.out.println("No devices have been added yet.\n");
            TimerDelay.introduceDealy1Secs();
            return;
        }

        System.out.println("Select a device to configure:\n");
        displayDevices();

        System.out.print("Enter the number of the device: ");
        int deviceIndex = input.nextInt() - 1;

        if (deviceIndex >= 0 && deviceIndex < devicesList.size())
        {
            NetworkComponent selectedDevice = devicesList.get(deviceIndex);
            // Proceed with the configuration based on device type
            if (selectedDevice instanceof RouterDevice)
            {
                System.out.println("Configuring Router...\n");
                TimerDelay.introduceDealy3Secs();
                ((RouterDevice) selectedDevice).configureRouterPorts();
            }
            else if (selectedDevice instanceof SwitchDevice)
            {
                System.out.println("Configuring Switch...\n");
                TimerDelay.introduceDealy3Secs();
                ((SwitchDevice) selectedDevice).manageVLANS();
            }
            else
            {
                System.out.println("Unvalid device type, Cannot configure.\n");
            }
        }
        else
        {
            System.out.println("Invalid selection.\n");
        }
    }

    // Part 7 - Method to add a connection between two devices
    public void addConnection()
    {
        if (devicesList.isEmpty() || devicesList.size() == 1)
        {
            System.out.println("There are not enough Devices to form a connection, Please consider adding 2 devices at least.\n");
            logAction("Attempted to add connection but not enough devices exist.");
            TimerDelay.introduceDealy1Secs();
            return;
        }
    
        System.out.println("Select the 1st device to connect:\n");
        TimerDelay.introduceDealy1Secs();
        displayDevices();
        System.out.print("Enter 1st Device: ");
        int firstDevice = input.nextInt();
        if (firstDevice < 1 || firstDevice > devicesList.size())
        {
            System.out.println("Enter a valid 1st Device position.\n");
            logAction("Attempted to add connection with invalid 1st Device position: " + firstDevice);
            TimerDelay.introduceDealy1Secs();
            return;
        }
        System.out.println("Loading...");
        TimerDelay.introduceDealy3Secs();
        logAction("User selected 1st Device ID: " + devicesList.get(firstDevice - 1).getComponentID());

        System.out.println("Select the 2nd device to connect:\n");
        displayDevices();
        System.out.print("Enter 2nd Device: ");
        int secondDevice = input.nextInt();
        if (secondDevice < 1 || secondDevice > devicesList.size())
        {
            System.out.println("Enter a valid 2nd Device position.\n");
            logAction("Attempted to add connection with invalid 2nd Device position: " + secondDevice);
            TimerDelay.introduceDealy1Secs();
            return;
        }
    
        if (firstDevice == secondDevice)
        {
            System.out.println("You can't connect the same device to itself, Please try again.\n");
            logAction("Attempted to connect the same device to itself: Device ID " + devicesList.get(firstDevice - 1).getComponentID());
            TimerDelay.introduceDealy1Secs();
            return;
        }
        System.out.println("Loading...");
        TimerDelay.introduceDealy3Secs();


        // Get selected devices
        NetworkComponent selectedDevice1 = devicesList.get(firstDevice - 1);
        NetworkComponent selectedDevice2 = devicesList.get(secondDevice - 1);
    
        // Check if a connection already exists
        for (ConnectedDevices connection : connectedDevices)

        {
            if ((connection.getDevice1().equals(selectedDevice1) && connection.getDevice2().equals(selectedDevice2)) ||
                (connection.getDevice1().equals(selectedDevice2) && connection.getDevice2().equals(selectedDevice1)))
                {
                    System.out.println("These devices are already connected.\n");
                    logAction("Attempted to add connection but devices are already connected: Device ID " + selectedDevice1.getComponentID() + " and Device ID " + selectedDevice2.getComponentID());
                    TimerDelay.introduceDealy1Secs();
                    return;  // Return if already connected
                }
        }
    
        // Create new connection if not already connected
        System.out.println("Adding Devices...");
        TimerDelay.introduceDealy3Secs();
        ConnectedDevices newConnection = new ConnectedDevices(selectedDevice1, selectedDevice2);
        connectedDevices.add(newConnection);
        System.out.println("Devices successfully connected.");
        logAction("Added connection between Device ID: " + selectedDevice1.getComponentID() + " and Device ID: " + selectedDevice2.getComponentID());
    }

    // Part 8 - Method to check connectivity between two devices
    public void checkConnectivity()
    {
        if (devicesList.isEmpty() || devicesList.size() == 1)
        {
            System.out.println("There are not enough Devices to form a connection, Please consider adding 2 devices at least.\n");
            logAction("Attempted to check connectivity but not enough devices exist.");
            TimerDelay.introduceDealy1Secs();
            return;
        }
    
        System.out.println("Select the 1st device to check connectivity:\n");
        displayDevices();
        System.out.print("Enter 1st Device: ");
        int firstDevice = input.nextInt();
    
        if (firstDevice < 1 || firstDevice > devicesList.size())
        {
            System.out.println("Enter a valid 1st Device position.\n");
            logAction("Attempted to check connectivity with invalid 1st Device position: " + firstDevice);
            TimerDelay.introduceDealy1Secs();
            return;
        }
    
        System.out.println("Select the 2nd device to check connectivity:\n");
        displayDevices();
        System.out.print("Enter 2nd Device: ");
        int secondDevice = input.nextInt();
    
        if (secondDevice < 1 || secondDevice > devicesList.size())
        {
            TimerDelay.introduceDealy1Secs();
            System.out.println("Enter a valid 2st Device position.\n");
            logAction("Attempted to check connectivity with invalid 2nd Device position: " + secondDevice);
            return;
        }
    
        if (firstDevice == secondDevice)
        {
            System.out.println("You can't check connectivity between the same device, Please try again.\n");
            logAction("Attempted to check connectivity between the same device: Device ID " + devicesList.get(firstDevice - 1).getComponentID());
            TimerDelay.introduceDealy1Secs();
            return;
        }
    
        // Get selected devices
        NetworkComponent selectedDevice1 = devicesList.get(firstDevice - 1);
        NetworkComponent selectedDevice2 = devicesList.get(secondDevice - 1);
    
        // Check if the devices are connected
        boolean isConnected = false;  // Flag to track if devices are connected
        for (ConnectedDevices connection : connectedDevices)
        {
            // Use .equals() (The overriden Method) for comparing objects
            if ((connection.getDevice1().equals(selectedDevice1) && connection.getDevice2().equals(selectedDevice2)) ||
                (connection.getDevice1().equals(selectedDevice2) && connection.getDevice2().equals(selectedDevice1)))
                {
                    isConnected = true;
                    break;  // Exit loop once a connection is found
                }
        }
    
        // Print the appropriate message after checking all connections
        if (isConnected)
        {
            System.out.println("Checking Connection...");
            TimerDelay.introduceDealy3Secs();
            System.out.println("The devices are connected.\n");
            logAction("Checked connectivity: Devices ID " + selectedDevice1.getComponentID() + " and ID " + selectedDevice2.getComponentID() + " are connected.");
        }
        else
        {
            System.out.println("Checking Connection...");
            TimerDelay.introduceDealy3Secs();
            System.out.println("The devices are NOT connected.\n");
            logAction("Checked connectivity: Devices ID " + selectedDevice1.getComponentID() + " and ID " + selectedDevice2.getComponentID() + " are NOT connected.");
        }
    }

    // Part 9 - Method to delete an existing device and it's connections 
    public void deleteDevice()
    {
        if (devicesList.isEmpty())
        {
            logAction("Attempted to delete device but no devices exist");
            System.out.println("No devices have been added yet, Nothing to delete.\n");
            TimerDelay.introduceDealy1Secs();
            return;
        }

        System.out.println("Select the device to delete:\n");
        displayDevices();

        System.out.print("Enter the number of the device to delete: ");
        int deviceIndex = input.nextInt() - 1;

        if (deviceIndex < 0 || deviceIndex >= devicesList.size())
        {
            System.out.println("Invalid selection, Please try again.\n");
            TimerDelay.introduceDealy1Secs();
            return;
        }

        NetworkComponent deviceToDelete = devicesList.get(deviceIndex);

        // Remove the device from the devicesList
        devicesList.remove(deviceIndex);

        // Remove any existing connections to/from this device
        connectedDevices.removeIf(connection -> connection.getDevice1().equals(deviceToDelete) || connection.getDevice2().equals(deviceToDelete));
        System.out.println("Deleting Device...");
        TimerDelay.introduceDealy3Secs();
        System.out.println("Device " + deviceToDelete.getComponentName() + " (ID: " + deviceToDelete.getComponentID() + ") deleted successfully.\n");
        logAction("Deleted device - ID: " + deviceToDelete.getComponentID() + ", Name: " + deviceToDelete.getComponentName());
    }

    // Part 10 - Save devices and connections to a file
    public void saveToFile()
    {
        logAction("Attempting to save to network_config.txt");
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("Text_Files/network_config.txt")))
        {
            // Write devices to file
            for (NetworkComponent device : devicesList)
            {
                if (device instanceof RouterDevice)
                {
                    RouterDevice router = (RouterDevice) device;
                    writer.write("Router: " + router.getComponentID() + "," + router.getComponentName() + "," + router.getComponentDescription() + "\n");
                    // Save router ports
                    writer.write("RouterPorts: " + router.getComponentID());
                    for (Long portID : router.getPorts())
                    {
                        writer.write("," + portID);
                    }
                    writer.write("\n");
                } 
                else if (device instanceof SwitchDevice)
                {
                    SwitchDevice switchDevice = (SwitchDevice) device;
                    writer.write("Switch: " + switchDevice.getComponentID() + "," + switchDevice.getComponentName() + "\n");
                    // Save switch VLANs
                    for (DifferentTypes vlan : switchDevice.getSwitchList())
                    {
                        writer.write("SwitchVLAN: " + switchDevice.getComponentID() + "," + 
                        vlan.getID() + "," + vlan.getName() + "," + 
                        vlan.getNumberOfDevciesConnected() + "\n");
                    }
                }
            }
            
            // Write connections to file
            for (ConnectedDevices connection : connectedDevices)
            {
                writer.write("Connection: " + connection.getDevice1().getComponentID() + "," + connection.getDevice2().getComponentID() + "\n");
            }

            System.out.println("Data saved successfully!");
            logAction("Successfully saved to network_config.txt");
        }
        catch (IOException e)
        {
            logAction("Error saving to file: " + e.getMessage());
            System.out.println("Error saving data to file: " + e.getMessage());
        }
    }

    // Part 11 - Read data from file and load devices and connections
    public void loadFromFile()
    {
        logAction("Attempting to load from network_config.txt");
        try (BufferedReader reader = new BufferedReader(new FileReader("Text_Files/network_config.txt")))
        {
            String line;
            while ((line = reader.readLine()) != null)
            {
                String[] parts = line.split(": ", 2);  // Split into type and data
                if (parts.length != 2)
                {
                    continue;
                }
                String type = parts[0];
                String[] data = parts[1].split(",");
                
                switch (type)
                {   
                    case "Router":
                        if (data.length >= 3)
                        {
                            long idForRouter = Long.parseLong(data[0]);
                            RouterDevice router = new RouterDevice(idForRouter, data[1], data[2]);
                            devicesList.add(router);
                        }
                        break;
                        case "RouterPorts":
                        if (data.length >= 1)
                        {
                            long routerId = Long.parseLong(data[0]);
                            RouterDevice router = (RouterDevice) findDeviceByID(routerId);
                            if (router != null)
                            {
                                for (int i = 1; i < data.length; i++)
                                {
                                    router.addPort(Long.parseLong(data[i]));
                                }
                            }
                        }
                        break;

                    case "Switch":
                        if (data.length >= 2)
                        {
                            long idForSwitch = Long.parseLong(data[0]);
                            SwitchDevice switchDevice = new SwitchDevice(idForSwitch, data[1]);
                            devicesList.add(switchDevice);
                        }
                        break;

                    case "SwitchVLAN":
                        if (data.length >= 4)
                        {
                            long switchId = Long.parseLong(data[0]);
                            SwitchDevice switchDevice = (SwitchDevice) findDeviceByID(switchId);
                            if (switchDevice != null)
                            {
                                int vlanId = Integer.parseInt(data[1]);
                                String vlanName = data[2];
                                int connectedDevicesInTheVlan = Integer.parseInt(data[3]);
                                switchDevice.addVLAN(vlanId, vlanName, connectedDevicesInTheVlan);
                            }
                        }
                        break;
                        
                    case "Connection":
                        if (data.length >= 2)
                        {
                            long device1ID = Long.parseLong(data[0]);
                            long device2ID = Long.parseLong(data[1]);
                            
                            NetworkComponent device1 = findDeviceByID(device1ID);
                            NetworkComponent device2 = findDeviceByID(device2ID);
                            
                            if (device1 != null && device2 != null)
                            {
                                connectedDevices.add(new ConnectedDevices(device1, device2));
                            }
                        }
                        break;
                }
            }
            System.out.println("Data loaded successfully from network_config.txt");
            logAction("Successfully loaded from network_config.txt");
        }
        catch (IOException e)
        {
            logAction("Error loading file: " + e.getMessage());
            System.out.println("No saved data found or error reading data: " + e.getMessage());
        }
        catch (NumberFormatException e)
        {
            logAction("Error parsing numbers from file: " + e.getMessage());
            System.out.println("Error parsing numbers from file: " + e.getMessage());
        }
    }

    // Part 12 - Find a device by its ID
    private NetworkComponent findDeviceByID(long componentID)
    {
        for (NetworkComponent device : devicesList)
        {
            if (device.getComponentID() == componentID)
            {
                return device;
            }
        }
        return null;
    }
}