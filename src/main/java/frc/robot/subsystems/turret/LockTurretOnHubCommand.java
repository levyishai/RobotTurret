package frc.robot.subsystems.turret;

import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.wpilibj2.command.CommandBase;

import java.util.function.DoubleSupplier;
import java.util.function.Supplier;

/**
 * A command that locks the turret on the target.
 */
public class LockTurretOnHubCommand extends CommandBase {
    private final Turret turret = Turret.getInstance();
    private final DoubleSupplier targetSupplier;
    private final Supplier<Pose2d> turretPoseSupplier;
    private final Pose2d hubPose = TurretConstants.HUB_POSE;

    /**
     * Constructs a new LockTurretOnHubCommand.
     *
     * @param targetSupplier    the wanted degree supplier
     * @param robotPoseSupplier the position of the robot
     */
    public LockTurretOnHubCommand(DoubleSupplier targetSupplier, Supplier<Pose2d> robotPoseSupplier) {
        this.targetSupplier = targetSupplier;
        this.turretPoseSupplier = () -> new Pose2d(robotPoseSupplier.get().getTranslation(),
                Rotation2d.fromDegrees(turret.getCurrentDegrees()));

        addRequirements(turret);
    }

    @Override
    public void execute() {
        final Pose2d turretPoseRelativeToHubPose = turretPoseSupplier.get().relativeTo(hubPose);

        turret.rotateBy((turretPoseRelativeToHubPose.getRotation().getDegrees() * -1) + targetSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}
