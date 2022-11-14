package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TurretConstants {
    static final double VOLTAGE_COMPENSATION_SATURATION = 10;
    static final int MOTOR_ID = 1;
    static final boolean MOTOR_INVERTED = false;
    static final double PIXELS_PER_DEGREE = 10;
    static final double
            P = 1,
            I = 1,
            D = 1;
    static final double GEAR_RATIO = 5;
    static final WPI_TalonFX MOTOR = new WPI_TalonFX(MOTOR_ID);
    static final double DEGREES_LIMIT = 400;

    static {
        MOTOR.setInverted(MOTOR_INVERTED);

        MOTOR.config_kP(1, P);
        MOTOR.config_kP(1, I);
        MOTOR.config_kP(1, D);

        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
        MOTOR.enableVoltageCompensation(true);
    }

}
