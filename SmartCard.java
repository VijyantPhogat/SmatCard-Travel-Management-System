package javaxass1;

public class SmartCard {private int cID;
    private char type; // 'C', 'A', 'S' only
    private float balance;
    private Journey journey1;
    private Journey journey2; // Only for 'A' and 'S'
    private Journey journey3; // Only for 'S'


    public int getCardID()
    {
        return cID;
    }

    public void setCardID(int cID)
    {
        this.cID = cID;
    }

    public char getType()
    {
        return type;
    }

    public void setType(char type) {
        type = Character.toUpperCase(type);
        if (type == 'C' || type == 'A' || type == 'S')
        {
            this.type = type;
        }
        else
        {
            throw new IllegalArgumentException("Sorry Wrong Entry ! ! ! Must be 'C', 'A', or 'S'.");
        }
    }

    public float getBalance()
    {
        return balance;
    }

    public void setBalance(float balance) {
        if (balance < 5.0)
        {
            throw new IllegalArgumentException("Sorry, Your balance needs to be at least $5.0");
        }
        else
        {
            this.balance = balance;
        }
    }

    public Journey getJourney1()
    {
        return journey1;
    }

    public void setJourney1(Journey j1)
    {
        this.journey1 = j1;
    }

    public Journey getJourney2()
    {
        return journey2;
    }

    public void setJourney2(Journey j2) {
        if (this.type == 'A' || this.type == 'S')
        {
            this.journey2 = j2;
        }
        else
        {
            throw new IllegalStateException("Child card type cannot have a second journey.");
        }
    }

    public Journey getJourney3() // only for Senior smartcard
    {
        return journey3;
    }

    public void setJourney3(Journey j3) {
        if (this.type == 'S')
        {
            this.journey3 = j3;
        }
        else
        {
            throw new IllegalStateException("Child and Adult card type cannot have a third journey.");
        }
    }
}
