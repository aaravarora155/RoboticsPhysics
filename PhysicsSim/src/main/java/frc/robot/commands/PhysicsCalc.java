package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;

public class PhysicsCalc {
    //Unused Constants
    private static final double gravity = 9.8;
    private static final double velocity=30; //(m/s)
    private static final double height = 1.8288; //(m)

    //Poses
    private static Translation2d hubPose;

    //Distance Vector Calculation
    private static Translation2d distance2d;

    //Distances
    private static double distanceX;
    private static double distanceY;
    private static double distance;

    public static void calc(Translation2d robotPose, DriverStation.Alliance alliance) {

        //Set hub pose based on alliance
        if (alliance==DriverStation.Alliance.Red){
            hubPose = new Translation2d(11.9,4.03);
        }
        else{
            hubPose = new Translation2d(4.63,4.03);
        }

        //Calculate the Straight line distance in (m) to the hub
        distance2d = hubPose.minus(robotPose);

        distanceX = distance2d.getX();
        distanceY = distance2d.getY();

        distance = (double) Math.round(Math.hypot(distanceX,distanceY)*100)/100;
    }
    public static void lookupTable(){

    }
    public static void runRegression(double distance){

    }

    public static void main(String[] args){
        PhysicsCalc.calc(new Translation2d(), DriverStation.Alliance.Red);
        System.out.println("Welcome to Physics Calculator");
        System.out.println("X:"+distanceX);
        System.out.println("Y:"+distanceY);
        System.out.println("Distance:"+distance);
    }
}
