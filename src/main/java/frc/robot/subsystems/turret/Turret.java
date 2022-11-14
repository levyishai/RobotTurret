package frc.robot.subsystems.turret;


import com.ctre.phoenix.motorcontrol.ControlMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;
import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.utilities.Conversions;

public class Turret extends SubsystemBase {
    private final static Turret INSTANCE = new Turret();
    private final WPI_TalonFX motor = TurretConstants.MOTOR;

    private Turret() {

    }

    public static Turret getInstance() {
        return INSTANCE;
    }

    /**
     * Rotates the turret by the given degrees.
     * If the given degrees + the current degrees will hit the turret's limit,
     * the turret will turn to the same degrees in the opposite direction.
     *
     * @param degrees the degrees to turn the robot by
     */
    void rotateTo(double degrees) {
        setDegrees(degrees + getCurrentDegrees());
    }

    /**
     * Sets the turret's current degrees to the given degrees.
     * If the given degrees will hit the turret's limit,
     * the turret will turn to the same degrees in the opposite direction.
     *
     * @param degrees the wanted degrees
     */
    void setDegrees(double degrees) {
        if (willHitLimit(0, degrees)) {
            degrees = Conversions.rotationToOppositeRotation(degrees);
        }

        final double givenDegreesToPosition = Conversions.systemPositionToMotorPosition(Conversions.degreesToFalconTicks(degrees), TurretConstants.GEAR_RATIO);

        motor.set(ControlMode.Position, givenDegreesToPosition);
    }

    /**
     * @return the current degrees of the turret
     */
    double getCurrentDegrees() {
        return Conversions.motorPositionToSystemPosition(Conversions.falconTicksToDegrees(motor.getSelectedSensorPosition()), TurretConstants.GEAR_RATIO);
    }

    /**
     * Checks if the starting degrees + the degrees to add will hit the turret's limit.
     *
     * @param startingDegrees the starting degrees
     * @param degreesToAdd    the degrees to add to the starting degrees
     * @return if the starting degrees + the degrees to add will hit the turret's limit
     */
    boolean willHitLimit(double startingDegrees, double degreesToAdd) {
        return startingDegrees + degreesToAdd >= TurretConstants.DEGREES_LIMIT || startingDegrees + degreesToAdd < 0;
    }

    /**
     * Stops the turret's motor.
     */
    void stop() {
        motor.stopMotor();
    }

}

