

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.PS5Controller;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.Commands;
import edu.wpi.first.wpilibj2.command.button.Trigger;
import frc.robot.commands.Teleop.DriveCommand;
import frc.robot.subsystems.*;
import frc.robot.commands.Auto.*;

public class RobotContainer {
    private final Joystick joy = new Joystick(Constants.JOY_PORT);
    private final PS5Controller systemController = new PS5Controller(Constants.SYSTEM_CONTROLLER_PORT);
    private final DriveSubsystem subDrive = new DriveSubsystem();
    private final ArmSubsystem subArm = new ArmSubsystem();
    private final IntakeSubsystem subIntake = new IntakeSubsystem();
    private final AutoAling autoAling = new AutoAling(subDrive);

    public RobotContainer() {
        subDrive.setDefaultCommand(new DriveCommand(joy, subDrive));
        configureButtonBindings();
    }

    private void configureButtonBindings() {
        new Trigger(()-> systemController.getR2Axis() > 0.04)
        .whileTrue(Commands.run(()-> subArm.setUpAngular(0.4)))
        .whileFalse(Commands.run(()-> subArm.stopAngMotors()));

       new Trigger(()-> systemController.getL2Axis() > 0.04)
       .whileTrue(Commands.run(()-> subArm.setDownAngular(-0.4)))
       .whileFalse(Commands.run(()-> subArm.stopAngMotors()));
    }

    public Command getAutonomousCommand() {
        return autoAling;
    }
}