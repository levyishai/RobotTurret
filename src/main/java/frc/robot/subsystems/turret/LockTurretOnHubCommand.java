package frc.robot.subsystems.turret;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.utilities.Conversions;

import java.util.function.BooleanSupplier;
import java.util.function.DoubleSupplier;

/**
 * A command that locks the turret on the target.
 */
public class LockTurretOnHubCommand extends CommandBase {
    private final Turret turret = Turret.getInstance();
    private final DoubleSupplier positionSupplier, targetSupplier;
    private final BooleanSupplier hasTargetSupplier;
    private int currentSign = 1;

    /**
     * Constructs a new LockTurretOnHubCommand.
     *
     * @param positionSupplier  the position of the target supplier (in pixels).
     * @param hasTargetSupplier whether the turret has a target.
     * @param targetSupplier    the wanted degree supplier.
     */
    public LockTurretOnHubCommand(DoubleSupplier positionSupplier, BooleanSupplier hasTargetSupplier,
                                  DoubleSupplier targetSupplier) {
        this.positionSupplier = positionSupplier;
        this.hasTargetSupplier = hasTargetSupplier;
        this.targetSupplier = targetSupplier;

        addRequirements(turret);
    }

    @Override
    public void execute() {
        if (!hasTargetSupplier.getAsBoolean()) {
            if (turret.willHitLimit(turret.getCurrentDegrees(), currentSign * 10)) {
                currentSign = -1;
            }

            turret.rotateBy(currentSign * 10);
        }

        turret.rotateBy(Conversions.pixelsToDegrees(positionSupplier.getAsDouble(), TurretConstants.PIXELS_PER_DEGREE) + targetSupplier.getAsDouble());
    }

    @Override
    public void end(boolean interrupted) {
        turret.stop();
    }

}
