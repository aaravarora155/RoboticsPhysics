package frc.robot.subsystems;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class FuelIntake extends SubsystemBase {

    private final WeightSensor weightSensor;

    // Constants
    private static final int MAX_CAPACITY = 40;
    private static final double BALL_WEIGHT_KG = 0.227; // weight per ball

    public FuelIntake(WeightSensor weightSensor) {
        this.weightSensor = weightSensor;
    }

    @Override
    public void periodic() {
        SmartDashboard.putNumber("Fuel Balls in Storage", getStorageCount());
        SmartDashboard.putNumber("Remaining Capacity", getRemainingCapacity());
        SmartDashboard.putBoolean("Is Full?", isFull());
        SmartDashboard.putNumber("Total Fuel Weight (kg)", weightSensor.getWeight());
    }

    /**
     * Estimates the number of balls based on total weight.
     */
    public int getStorageCount() {
        double weight = weightSensor.getWeight();
        return (int) Math.ceil(weight / BALL_WEIGHT_KG);
    }

    /**
     * Returns remaining capacity before reaching max.
     */
    public int getRemainingCapacity() {
        return Math.max(0, MAX_CAPACITY - getStorageCount());
    }

    /**
     * True if storage is full.
     */
    public boolean isFull() {
        return getRemainingCapacity() == 0;
    }

    public static void main(String[] args) {
        WeightSensor weightSensor = new WeightSensor();
        FuelIntake fuelIntake = new FuelIntake(weightSensor);
    }

}
