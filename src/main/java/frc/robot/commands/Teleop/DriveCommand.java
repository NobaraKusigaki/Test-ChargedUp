package frc.robot.commands.Teleop;

import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.Constants;
import frc.robot.applications.Calcs;
import frc.robot.subsystems.DriveSubsystem;

public class DriveCommand extends Command {
    private final DriveSubsystem subDrive;
    private final Joystick joy;
    private double L_stickY, L_stickX, R_stickY, R_stickX, lt, rt;
    private double spd = 1;
    private int pov;
    private boolean a, b, x;

    public DriveCommand(Joystick joy, DriveSubsystem subDrive) {
        this.subDrive = subDrive;
        addRequirements(subDrive);
        this.joy = joy;
    }

    @Override
    public void initialize() {

    }

    @Override
    public void execute() {
        updateDashboard();
        readJoy();
        ifB();

        if (pov == -1)
            subDrive.driveCommand(lt, rt, L_stickY, L_stickX, R_stickY, R_stickX, spd);

        else
            subDrive.setMotors(Calcs.calcPov(pov, spd));

    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public void end(boolean interrupted) {

    }

    private void ifB() {
        if (b) {
            this.spd = 0.25;
        } else if (a) {
            this.spd = 0.5;
        } else if (x) {
            this.spd = 1;
        }
    }

    //get
    private void readJoy() {
        a = joy.getRawButton(Constants.BNT_A);
        b = joy.getRawButton(Constants.BNT_B);
        x = joy.getRawButton(Constants.BNT_X);

        this.R_stickX = joy.getRawAxis(Constants.RIGHT_X);
        this.R_stickY = joy.getRawAxis(Constants.RIGHT_Y);
        this.L_stickX = joy.getRawAxis(Constants.LEFT_X);
        this.L_stickY = joy.getRawAxis(Constants.LEFT_Y);
        this.lt = joy.getRawAxis(Constants.LT);
        this.rt = joy.getRawAxis(Constants.RT);
        this.pov = joy.getPOV();
    }

    private void updateDashboard() {
        SmartDashboard.putBoolean("bnt B", b);
        SmartDashboard.putBoolean("bnt A", a);
        SmartDashboard.putBoolean("bnt X", x);
        SmartDashboard.putNumber("spd", spd);
        SmartDashboard.putNumber("LT", lt);
        SmartDashboard.putNumber("RT", rt);
        SmartDashboard.putNumber("L_X", L_stickX);
        SmartDashboard.putNumber("L_Y", L_stickY);
        SmartDashboard.putNumber("R_X", R_stickX);
        SmartDashboard.putNumber("R_Y", R_stickY);
        SmartDashboard.putNumber("pov", pov);
    }
}