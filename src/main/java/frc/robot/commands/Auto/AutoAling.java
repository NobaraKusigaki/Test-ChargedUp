package frc.robot.commands.Auto;

import edu.wpi.first.networktables.NetworkTable;
import edu.wpi.first.networktables.NetworkTableInstance;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.DriveSubsystem;

public class AutoAling extends Command {

  NetworkTable limelight = NetworkTableInstance.getDefault().getTable("limelight-front");
  private DriveSubsystem SubSys;

  double tx, tv, ta, Rm, Lm, pose[];

  public AutoAling(DriveSubsystem SubSys) {

    this.SubSys = SubSys;
    addRequirements(SubSys);
  }

  @Override
  public void initialize() {}

  @Override
  public void execute() {
    limelight();
    SmartDashboard();
  }

  public void limelight() {
    tx = limelight.getEntry("tx").getDouble(0.0);
    ta = limelight.getEntry("ta").getDouble(0.0);
    tv = limelight.getEntry("tv").getDouble(0.0);
    pose = limelight.getEntry("botpose_wpired").getDoubleArray(new double[6]);

    // Ajustes finos
    double rot_percent = 0.01;
    double fwd_percent = 0.2;
    double targetArea = 5;

    if (tv == 0) {

      Rm = 0.25;
      Lm = -0.28;

    } else {

      double rot = rot_percent * tx;
      double forward = fwd_percent * (targetArea - ta);

      forward = Math.max(-0.6, Math.min(forward, 0.6));
      rot = Math.max(-0.4, Math.min(rot, 0.4));

      Lm = forward + rot;
      Rm = forward - rot;
    }
  }

  public void SmartDashboard() {
    SmartDashboard.putNumber("** - Tx", tx);
    SmartDashboard.putNumber("** - Ta", ta);
    SmartDashboard.putNumber("** - Tv", tv);
    SmartDashboard.putNumber("** - PoseX", pose[0]);
    SmartDashboard.putNumber("** - PoseY", pose[1]);
    SmartDashboard.putNumber("** - PoseZ", pose[2]);
  }
  @Override
  public void end(boolean interrupted) {}

  @Override
  public boolean isFinished() {
    return false;
  }
}
