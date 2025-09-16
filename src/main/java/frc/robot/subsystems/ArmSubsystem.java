// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.revrobotics.RelativeEncoder;
import com.revrobotics.spark.SparkMax;
import com.revrobotics.spark.SparkBase.PersistMode;
import com.revrobotics.spark.SparkBase.ResetMode;
import com.revrobotics.spark.SparkLowLevel.MotorType;
import com.revrobotics.spark.config.SparkMaxConfig;
import com.revrobotics.spark.config.SparkBaseConfig.IdleMode;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class ArmSubsystem extends SubsystemBase {
  private SparkMax m_angular;
  private SparkMax m_extend;

  private SparkMaxConfig m_angularConfig;
  private SparkMaxConfig m_extendConfig;
  
  private RelativeEncoder m_angularEncoder;
  private RelativeEncoder m_extendEncoder;
  
  public ArmSubsystem() {
  initMotors();
  
  }
public void initMotors(){
  m_angular = new SparkMax(Constants.MOTOR_ANGULAR, MotorType.kBrushless);
  m_extend = new SparkMax(Constants.MOTOR_EXTEND, MotorType.kBrushless);

  m_angularConfig = new SparkMaxConfig();
  m_extendConfig = new SparkMaxConfig();

  m_angularConfig.idleMode(IdleMode.kBrake);
  m_extendConfig.idleMode(IdleMode.kBrake);

  m_angular.configure(m_angularConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);
  m_extend.configure(m_extendConfig, ResetMode.kNoResetSafeParameters, PersistMode.kPersistParameters);


}


public void setUpAngular(double spd){
  m_angular.set(spd);
}
public void setUpExtend(double spd){
  m_extend.set(spd);
}   
public void setDownAngular(double spd){
  m_angular.set(-spd);
}
public void setRetractExtend(double spd){
  m_extend.set(-spd);
}

public void stopAngMotors(){
  m_angular.set(0);
}
public void stopExtMotors(){
  m_extend.set(0);
}

  @Override
  public void periodic() {
    // This method will be called once per scheduler run
  }
}
