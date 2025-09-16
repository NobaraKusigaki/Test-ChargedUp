// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.TimedRobot;
import edu.wpi.first.wpilibj2.command.Command;
import edu.wpi.first.wpilibj2.command.CommandScheduler;
import frc.robot.subsystems.DriveSubsystem;


public class Robot extends TimedRobot {
    private Command autonomousCommand;
    // private LimelightSubsystem limesub;
    private RobotContainer robotContainer;
    private Joystick joy;
    private DriveSubsystem subDrive;


    @Override
    public void robotInit() {
        // limesub = new LimelightSubsystem();
        subDrive = new DriveSubsystem();
        robotContainer = new RobotContainer();
        joy = new Joystick(0);

        // autonomousCommand = robotContainer.getAutonomousCommand();

    }


    @Override
    public void robotPeriodic()
    {
        // Runs the Scheduler.  This is responsible for polling buttons, adding newly-scheduled
        // commands, running already-scheduled commands, removing finished or interrupted commands,
        // and running subsystem periodic() methods.  This must be called from the robot's periodic
        // block in order for anything in the Command-based framework to work.
        CommandScheduler.getInstance().run();
    }


    /** This method is called once each time the robot enters Disabled mode. */
    @Override
    public void disabledInit() {}


    @Override
    public void disabledPeriodic() {}


    /** This autonomous runs the autonomous command selected by your {@link RobotContainer} class. */
    @Override
    public void autonomousInit()
    {
        //autonomousCommand = robotContainer.getAutonomousCommand();

        // schedule the autonomous command (example)
        if (autonomousCommand != null)
        {
            autonomousCommand.schedule();
        }
    }


    /** This method is called periodically during autonomous. */
    @Override
    public void autonomousPeriodic() {}


    @Override
    public void teleopInit()
    {
        // This makes sure that the autonomous stops running when
        // teleop starts running. If you want the autonomous to
        // continue until interrupted by another command, remove
        // this line or comment it out.
        if (autonomousCommand != null)
        {
            autonomousCommand.cancel();
        }
    }


    /** This method is called periodically during operator control. */
    @Override
    public void teleopPeriodic() {

    }

    @Override
    public void testInit()
    {
        // Cancels all running commands at the start of test mode.
        CommandScheduler.getInstance().cancelAll();
    }


    /** This method is called periodically during test mode. */
    @Override
    public void testPeriodic() {}


    /** This method is called once when the robot is first started up. */
    @Override
    public void simulationInit() {}


    /** This method is called periodically whilst in simulation. */
    @Override
    public void simulationPeriodic() {}
}