package frc.robot.subsystems.turret;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonFX;

public class TurretConstants {
    static final double VOLTAGE_COMPENSATION_SATURATION = 10;
    static final int MOTOR_ID = 1;
    static final boolean MOTOR_INVERTED = false;
    static final double
            P = 1,
            I = 1,
            D = 1;
    static final double GEAR_RATIO = 5;
    static final WPI_TalonFX MOTOR = new WPI_TalonFX(MOTOR_ID);
    static final double DEGREES_LIMIT = 400;
    static final double
            HUB_X = 8.2296,
            HUB_Y = 0.5121;

    static {
        MOTOR.setInverted(MOTOR_INVERTED);

        MOTOR.config_kP(1, P);
        MOTOR.config_kI(1, I);
        MOTOR.config_kD(1, D);

        MOTOR.configVoltageCompSaturation(VOLTAGE_COMPENSATION_SATURATION);
        MOTOR.enableVoltageCompensation(true);
    }

}
