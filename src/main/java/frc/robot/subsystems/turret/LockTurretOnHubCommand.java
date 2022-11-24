package frc.robot.subsystems.turret;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.util.Units;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/**
 * A command that locks the turret on the target.
 */
public class LockTurretOnHubCommand extends CommandBase {
    private final Turret turret = Turret.getInstance();
    private final DoubleSupplier targetSupplier;
    private final BooleanSupplier hasTargetSupplier;
    private final Supplier<Pose2d> turretPoseSupplier;
    private final Pose2d hubPose =
            new Pose2d(Units.inchesToMeters(54 * 12) / 2, Units.inchesToMeters(27 * 12) / 2, new Rotation2d());
    private int currentSign = 1;

    /**
     * Constructs a new LockTurretOnHubCommand.
     *
     * @param hasTargetSupplier whether the turret has a target
     * @param targetSupplier    the wanted degree supplier
     * @param robotPoseSupplier the position of the robot
     */
    public LockTurretOnHubCommand(BooleanSupplier hasTargetSupplier,
                                  DoubleSupplier targetSupplier, Supplier<Pose2d> robotPoseSupplier) {
        this.hasTargetSupplier = hasTargetSupplier;
        this.targetSupplier = targetSupplier;
        this.turretPoseSupplier = () -> new Pose2d(robotPoseSupplier.get().getTranslation(),
                Rotation2d.fromDegrees(turret.getCurrentDegrees()));

        addRequirements(turret);
    }

    @Override
    public void execute() {
        final Pose2d turretPoseRelativeToHubPose = turretPoseSupplier.get().relativeTo(hubPose);

        if (!hasTargetSupplier.getAsBoolean()) {
            if (turret.willHitLimit(turret.getCurrentDegrees(), currentSign * 10)) {
                currentSign = -1;
            }

            turret.rotateBy(currentSign * 10);
        }

        turret.rotateBy(turretPoseRelativeToHubPose.getRotation().getDegrees() + targetSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}
