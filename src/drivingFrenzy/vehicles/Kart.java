package drivingFrenzy.vehicles;

import drivingFrenzy.race.Section;

public class Kart implements Vehicle{
    private int number;
    private String driver;
    private int currentSpeed;
    private int maxSpeed;
    private String description;

    public Kart(int number, String driver, int currentSpeed, int maxSpeed, String description) {
        this.number = number;
        this.driver = driver;
        this.currentSpeed = currentSpeed;
        this.maxSpeed = maxSpeed;
        this.description = description;
    }

    @Override
    public int getNumber() {
        return number;
    }

    @Override
    public String getDriver() {
        return driver;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public int getCurrentSpeed() {
        return currentSpeed;
    }

    @Override
    public int getMaxSpeed() {
        return maxSpeed;
    }

    @Override
    public String adaptSpeed(Section nextSection) {
        return null;
    }
}
