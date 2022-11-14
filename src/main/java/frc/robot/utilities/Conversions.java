package frc.robot.utilities;

public class Conversions {
    private static final int DEGREES_PER_REVOLUTIONS = 360;
    private static final double FALCON_TICKS_PER_REVOLUTION = 2048;

    /**
     * Converts a system position to a motor position by multiplying the system position by the gear ratio.
     *
     * @param position  the system position
     * @param gearRatio the gear ratio
     * @return the motor position
     */
    public static double systemPositionToMotorPosition(double position, double gearRatio) {
        return position * gearRatio;
    }

    /**
     * Converts a motor position to a system position by dividing the motor position by the gear ratio.
     *
     * @param position  the motor position
     * @param gearRatio the gear ratio
     * @return the system position
     */
    public static double motorPositionToSystemPosition(double position, double gearRatio) {
        return position / gearRatio;
    }

    /**
     * Converts degrees to revolutions by dividing the degrees by the degrees per revolution.
     *
     * @param degrees the degrees
     * @return the revolutions
     */
    public static double degreesToRevolutions(double degrees) {
        return degrees / DEGREES_PER_REVOLUTIONS;
    }

    /**
     * Converts revolutions to degrees by multiplying the revolutions by the degrees per revolution.
     *
     * @param revolutions the revolutions
     * @return the degrees
     */
    public static double revolutionsToDegrees(double revolutions) {
        return revolutions * DEGREES_PER_REVOLUTIONS;
    }

    /**
     * Converts degrees to falcon ticks by multiplying the degrees by the falcon ticks per revolution.
     *
     * @param degrees the degrees
     * @return the falcon ticks
     */
    public static double degreesToFalconTicks(double degrees) {
        return degreesToRevolutions(degrees) * FALCON_TICKS_PER_REVOLUTION;
    }

    /**
     * Converts falcon ticks to degrees by dividing the falcon ticks by the falcon ticks per revolution.
     *
     * @param falconTicks the falcon ticks
     * @return the degrees
     */
    public static double falconTicksToDegrees(double falconTicks) {
        return revolutionsToDegrees(falconTicks) / FALCON_TICKS_PER_REVOLUTION;
    }

    /**
     * Converts a rotation to an opposite rotation,
     * by subtracting from it the degrees per revolution multiplied by the sign of the rotation.
     *
     * @param rotation the rotation
     * @return the opposite rotation
     */
    public static double rotationToRawRotation(double rotation) {
        return rotation % DEGREES_PER_REVOLUTIONS;
    }

    /**
     * Converts pixels to degrees by dividing the pixels by the pixels per degree.
     *
     * @param pixels          the pixels
     * @param pixelsPerDegree the pixels per degree
     * @return the pixels in degrees
     */
    public static double pixelsToDegrees(double pixels, double pixelsPerDegree) {
        return pixels / pixelsPerDegree;
    }

}
