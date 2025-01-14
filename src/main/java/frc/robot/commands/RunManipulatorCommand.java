// Copyright (c) FIRST and other WPILib contributors.
// Open Source Software; you can modify and/or share it under the terms of
// the WPILib BSD license file in the root directory of this project.
// try commit

package frc.robot.commands;

import frc.robot.subsystems.Manipulator;
import frc.robot.subsystems.Manipulator.ManipulatorModes;
import edu.wpi.first.wpilibj2.command.Command;

public class RunManipulatorCommand extends Command {
  public Manipulator manipulator; 
  private ManipulatorModes mode;
  
  public RunManipulatorCommand (Manipulator manipulator, ManipulatorModes mode) {
    this.manipulator = manipulator;
    this.mode = mode;
  }
  
  @Override
  public void initialize() {
    manipulator.run(mode);
  }

  @Override
  public void end(boolean interrupted) {
      manipulator.runButActually(0);
  }

  
}
