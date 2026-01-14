package frc.robot.subsystems;

import edu.wpi.first.wpilibj.SerialPort;

public class WeightSensor {
    private final SerialPort serial;
    private double lastWeight = 0.0;

    public WeightSensor() {
        serial = new SerialPort(9600, SerialPort.Port.kUSB);
        serial.setTimeout(0.02);
        serial.enableTermination('\n');
    }

    public double getWeight() {
        try {
            if (serial.getBytesReceived() > 0) {
                String data = serial.readString().trim();
                lastWeight = Double.parseDouble(data);
            }
        } catch (Exception e) {
            // Ignore bad reads
        }
        return lastWeight;
    }
}
