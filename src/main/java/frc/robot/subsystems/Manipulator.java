// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.

package frc.robot.subsystems;

import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj2.command.SubsystemBase;
import frc.robot.Constants;

public class Manipulator extends SubsystemBase {
  WPI_TalonSRX leftMotor;
  WPI_TalonSRX rightMotor;
  public Manipulator(WPI_TalonSRX leftMotor, WPI_TalonSRX rightMotor) {
    this.leftMotor = leftMotor;
    this.rightMotor = rightMotor;

    this.rightMotor.follow(this.leftMotor);
  }

  public void run (ManipulatorModes mode) {
    if (mode == ManipulatorModes.INTAKE) {
      runButActually(Constants.ManipulatorConstants.intakeSpeed);
    }
    else if (mode == ManipulatorModes.SHOOT) {
      runButActually(Constants.ManipulatorConstants.shooterSpeed);
    }
  }
  
  public void runButActually (double speed) {
    leftMotor.set(speed);
  }
  
  
  public enum ManipulatorModes {
    INTAKE, 
    SHOOT
  }
}
