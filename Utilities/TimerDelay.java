package Utilities;

public class TimerDelay
{
    public static void introduceDealy3Secs()
    {
        try
        {
            Thread.sleep(3000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Please Do not Interrupt");
        }
    }

    public static void introduceDealy1Secs()
    {
        try
        {
            Thread.sleep(1000);
        }
        catch (InterruptedException e)
        {
            System.out.println("Please Do not Interrupt");
        }
    }

    public static void hehehe()
    {
        System.out.println("   _    _      ____      _         ___      ____      __  __      ____   \r\n" + //
        "  F L  J J    F ___J    FJ       ,\"___\".   F __ ]    F  \\/  ]    F ___J  \r\n" + //
        " J J .. L L  J |___:   J |       FJ---L]  J |--| L  J |\\__/| L  J |___:  \r\n" + //
        " | |/  \\| |  | _____|  | |      J |   LJ  | |  | |  | |`--'| |  | _____| \r\n" + //
        " F   /\\   J  F L____:  F L_____ | \\___--. F L__J J  F L    J J  F L____: \r\n" + //
        "J___//\\\\___LJ________LJ________LJ\\_____/FJ\\______/FJ__L    J__LJ________L\r\n" + //
        "|___/  \\___||________||________| J_____F  J______F |__L    J__||________|\r\n" + //
        " ____    ____          _  _          __  __          ___                 \r\n" + //
        "/_  _\\  F __ ]        F L L]        F  \\/  ]        F __\".               \r\n" + //
        "[J  L] J |--| L      J   \\| L      J |\\__/| L      J (___|               \r\n" + //
        " |  |  | |  | |      | |\\   |      | |`--'| |      J\\___ \\               \r\n" + //
        " F  J  F L__J J      F L\\\\  J  __  F L    J J  __ .--___) \\              \r\n" + //
        "J____LJ\\______/F    J__L \\\\__LJ__LJ__L    J__LJ__LJ\\______J              \r\n" + //
        "|____| J______F     |__L  J__||__||__L    J__||__| J______F              ");
    }

}