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
     * the turret will turn to the raw degrees.
     *
     * @param degrees the degrees to turn the robot by
     */
    void rotateBy(double degrees) {
        rotateTo(degrees + getCurrentDegrees());
    }

    /**
     * Rotates the turret to the given degrees.
     * If the given degrees will hit the turret's limit,
     * the turret will turn to the raw degrees.
     *
     * @param degrees the degrees to turn the turret to
     */
    void rotateTo(double degrees) {
        if (isMoreThanLimit(degrees)) {
            degrees = Conversions.degreesToRawDegrees(degrees);
        }

        final double givenDegreesToTicks = Conversions.systemPositionToMotorPosition(Conversions.degreesToFalconTicks(degrees), TurretConstants.GEAR_RATIO);

        motor.set(ControlMode.Position, givenDegreesToTicks);
    }

    /**
     * @return the current degrees of the turret
     */
    double getCurrentDegrees() {
        return Conversions.motorPositionToSystemPosition(Conversions.falconTicksToDegrees(motor.getSelectedSensorPosition()), TurretConstants.GEAR_RATIO);
    }

    /**
     * Checks if the given degrees are more than turret's limit.
     *
     * @param degrees the degrees to check
     * @return if the given degrees are more than the turret's limit
     */
    boolean isMoreThanLimit(double degrees) {
        return degrees >= TurretConstants.DEGREES_LIMIT || degrees < 0;
    }

    /**
     * Stops the turret's motor.
     */
    void stop() {
        motor.stopMotor();
    }

}

