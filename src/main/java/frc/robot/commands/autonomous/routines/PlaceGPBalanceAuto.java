package frc.robot.commands.autonomous.routines;

import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.SequentialCommandGroup;
import frc.robot.commands.drive.AutoBalanceCommand;
import frc.robot.commands.drive.AutoDriveCommand;
import frc.robot.commands.drive.AutoTurnToAngleCommand;
import frc.robot.commands.drive.DriveToTapeCommand;
import frc.robot.subsystems.DriveSubsystem;
import frc.robot.subsystems.ElementTransitSubsystem;

// NOTE:  Consider using this command inline, rather than writing a subclass.  For more
// information, see:
// https://docs.wpilib.org/en/stable/docs/software/commandbased/convenience-features.html
public class PlaceGPBalanceAuto extends SequentialCommandGroup {

  /** Creates a new Auto Command. */
  public PlaceGPBalanceAuto(ElementTransitSubsystem transitSystem, DriveSubsystem driveSubsystem) {
    addCommands(
        /*
         * When starting at position 2
         * Robot will be facing the center of the field
         */

        // Robot will turn around 180 degrees to face the node
        new AutoTurnToAngleCommand(180, driveSubsystem),

        // Robot will drive forward a slight calculated distance to get closer to the
        // node
        new AutoDriveCommand(0.2143125, driveSubsystem),

        // Robot will align to the node
        new DriveToTapeCommand(driveSubsystem),

        // Robot will outtake the game piece it started with
        // Commands.runOnce(transitSystem::openClaw, transitSystem),

        // Robot will be facing the node, and will drive backward the calculated
        // distance to go onto the station and balance
        new AutoDriveCommand(-1.143, driveSubsystem),

        // Robot will balance on the charge station
        new AutoBalanceCommand(driveSubsystem));
  }
}
