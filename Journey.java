package javaxass1;

public class Journey { private int journeyID;
    private String transportMode;
    private int startOfJourney;
    private int endOfJourney;
    private int distanceOfJourney;


    // Getters and Setters
    public int getJourneyID() // used getter method to return the values of private variable journeyID
    {
        return journeyID;
    }

    public void setJourneyID(int journeyID) // used setter method to access the values of private variable journeyID
    {
        this.journeyID = journeyID;
    }

    public String getTransportMode() // used getter method to return the values of private variable getTransportMode
    {
        return transportMode;
    }

    public void setTransportMode(String transportMode) // used setter method to access the values of private variable getTransportMode
    {
        transportMode = transportMode.toLowerCase();
        if (!transportMode.equals("train") && !transportMode.equals("bus") && !transportMode.equals("tram"))
        {
            throw new IllegalArgumentException("Wrong mode of transport. Must be 'train', 'bus', or 'tram'.");
        }
        this.transportMode = transportMode;
    }

    public int getStartOfJourney() // used getter method to return the values of private variable getStartOfJourney
    {
        return startOfJourney;
    }

    public void setStartOfJourney(int startOfJourney) // used setter method to access the values of private variable getStartOfJourney
    {
        if (startOfJourney < 1 || startOfJourney > 10)
        {
            throw new IllegalArgumentException("Start of journey must be between 1 and 10.");
        }
        this.startOfJourney = startOfJourney;
    }

    public int getEndOfJourney() // used getter method to return the values of private variable getEndOfJourney
    {
        return endOfJourney;
    }

    public void setEndOfJourney(int endOfJourney) // used setter method to return the values of private variable getEndOfJourney
    {
        if (endOfJourney < 1 || endOfJourney > 10 || endOfJourney == this.startOfJourney)
        {
            throw new IllegalArgumentException("End of journey must be between 1 and 10 and different from the start of the journey.");
        }
        this.endOfJourney = endOfJourney;
    }

    public int getDistanceOfJourney() // used getter method to return the values of private variable getDistanceOfJourney

    {
        return distanceOfJourney;
    }

    public void calculateDistance() {
        this.distanceOfJourney = Math.abs(this.endOfJourney - this.startOfJourney);
    }
}
