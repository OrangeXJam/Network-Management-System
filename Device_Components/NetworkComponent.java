package Device_Components;

public abstract class NetworkComponent
{
    //Attributes
    private long componentID;
    private String componentName;
    private String componentDescription;

    // Constructor for Router
    public NetworkComponent(long componentID, String Name, String Description)
    {
        this.componentID = componentID;
        this.componentName = Name;
        this.componentDescription = Description;
    }

    // Constructor for Switch
    public NetworkComponent(long componentID, String Name)
    {
        this.componentID = componentID;
        this.componentName = Name;
        this.componentDescription = "No Description";
    }

    // Getters and Setters
    public long getComponentID()
    {
        return componentID;
    }

    public String getComponentName()
    {
        return componentName;
    }

    public String getComponentDescription()
    {
        return componentDescription;
    }

    public void setComponentID(long componentID)
    {
        this.componentID = componentID;
    }

    public void setComponentName(String name)
    {
        this.componentName = name;
    }

    public void setComponentDescription(String description)
    {
        this.componentDescription = description;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass())
        {
            return false;
        }
        NetworkComponent that = (NetworkComponent) obj;
        return componentID == that.componentID &&
        componentName.equals(that.componentName) &&
        componentDescription.equals(that.componentDescription);
    }
}