package Utilities;

public class DifferentTypes 
{
    private int ID;
    private String Name;
    private int numberOfDevciesConnected;

    // Constructor for Switch or Router
    public DifferentTypes(int ID, String Name, int numberOfDevciesConnected)
    {
        this.ID = ID;
        this.Name = Name;
        this.numberOfDevciesConnected = numberOfDevciesConnected;
    }

    // Getters
    public int getID()
    {
        return ID;
    }
    public String getName()
    {
        return Name;
    }
    public int getNumberOfDevciesConnected()
    {
        return numberOfDevciesConnected;
    }

    // Setters (validation done in the calling code)
    public void setID(int ID)
    {
        this.ID = ID;
    }
    public void setName(String Name)
    {
        this.Name = Name;
    }
    public void setNumberOfDevciesConnected(int numberOfDevciesConnected)
    {
        this.numberOfDevciesConnected = numberOfDevciesConnected;
    }

    @Override
    public boolean equals(Object obj)
    {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        DifferentTypes that = (DifferentTypes) obj;
        return ID == that.ID &&
            numberOfDevciesConnected == that.numberOfDevciesConnected &&
            Name.equals(that.Name);
    }
}