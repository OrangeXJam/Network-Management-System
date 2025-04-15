package Utilities;

import Device_Components.NetworkComponent;
public class ConnectedDevices
{
    private NetworkComponent Device1;
    private NetworkComponent Device2;

    ConnectedDevices() {}

    public ConnectedDevices(NetworkComponent ConnectedDevice1, NetworkComponent ConnectedDevice2)
    {
        this.Device1 = ConnectedDevice1;
        this.Device2 = ConnectedDevice2;
    }

    public NetworkComponent getDevice1()
    {
        return Device1;
    }

    public NetworkComponent getDevice2()
    {
        return Device2;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        ConnectedDevices that = (ConnectedDevices) obj;
        return (Device1.equals(that.Device1) && Device2.equals(that.Device2)) || Device1.equals(that.Device2) && Device2.equals(that.Device1); // Check both directions
    }
}