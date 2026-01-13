package frc.robot.commands;

import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.wpilibj.DriverStation;

import java.util.HashMap;
import java.util.Map;

public class PhysicsCalc {
    public static double calc(Translation2d robotPose, DriverStation.Alliance alliance) {
        //Pose
        Translation2d hubPose;

        //Distance Vector Calculation
        Translation2d distance2d;

        //Distances
        double distanceX;
        double distanceY;
        double distance;

        //Angle
        double theta;

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

        theta = runRegression(distance);
        return theta;
    }
//    public static void lookupTable(){
//        /*
//        * Key: Distance From Hub (double)
//        * Value: Angle (double) (0-90)
//        * */
//        lookupTable.put(distance,runRegression(distance));
//
//    }
    public static Double runRegression(double distance){
        //Ensure that distance is positive
        distance=Math.abs(distance);
        return (double) 0.0; //Run the regression on the distance to get an output of theta and return it
    }

    public static void main(String[] args){
        double theta = PhysicsCalc.calc(new Translation2d(), DriverStation.Alliance.Red);
        System.out.println("Angle: "+theta);
    }
}
