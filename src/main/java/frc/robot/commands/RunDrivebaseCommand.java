package frc.robot.commands;

import java.util.function.DoubleSupplier;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.Command;
import frc.robot.subsystems.Drivebase;

public class RunDrivebaseCommand extends Command {
    Drivebase drivebase;
    DoubleSupplier move;
    DoubleSupplier turn;

    public RunDrivebaseCommand(Drivebase drivebase, DoubleSupplier move, DoubleSupplier turn) {
        this.drivebase = drivebase;
        this.move = move;
        this.turn = turn;

        addRequirements(drivebase);

    }
    
    @Override
    public void execute() {
        drivebase.drive(move.getAsDouble(),turn.getAsDouble());
//        drivebase.leftSide(move.getAsDouble() + turn.getAsDouble());
//        drivebase.rightSide(move.getAsDouble() - turn.getAsDouble());  
    }

    @Override
    public void end(boolean interrupted){
        drivebase.driveLeft(0);
        drivebase.driveRight(0);
    }

}
