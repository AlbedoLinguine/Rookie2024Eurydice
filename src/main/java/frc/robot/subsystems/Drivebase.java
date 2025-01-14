package frc.robot.subsystems;

// import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

// import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
// import edu.wpi.first.wpilibj2.command.SubsystemBase;

// public class Drivebase extends SubsystemBase {
//     WPI_TalonSRX leftBack;
//     WPI_TalonSRX leftFront;
//     WPI_TalonSRX rightFront;
//     WPI_TalonSRX rightBack;
    
//     public Drivebase (WPI_TalonSRX leftBack, WPI_TalonSRX leftFront, WPI_TalonSRX rightFront, WPI_TalonSRX rightBack) {
//         this.leftBack = leftBack;
//         this.leftFront = leftFront;
//         this.rightBack = rightBack;
//         this.rightFront = rightFront;

//         leftFront.follow(leftBack);
//         leftFront.setInverted(false);
//         leftBack.setInverted(false);
//         rightFront.setInverted(false);
//         rightBack.setInverted(false);
//         rightFront.follow(rightBack);
//     }

//     @Override
//     public void periodic(){
//         SmartDashboard.putNumber("right front", rightFront.getMotorOutputPercent());
//         SmartDashboard.putNumber("right back", rightBack.getMotorOutputPercent());
//         SmartDashboard.putNumber("left front", leftFront.getMotorOutputPercent());
//         SmartDashboard.putNumber("left back", leftBack.getMotorOutputPercent());
//     }

//     public void leftSide(double speed) {
//         leftBack.set(speed * .5);
//     }

//     public void rightSide(double speed) {
//         rightBack.set(speed * .5);
//     }

//     public void banana(double move, double turn){

//         if (move > 0.15 || turn < -0.15){



//         }

//     }

// }


import frc.robot.Constants;

import com.ctre.phoenix.motorcontrol.NeutralMode;
import com.ctre.phoenix.motorcontrol.can.WPI_TalonSRX;

import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import edu.wpi.first.wpilibj2.command.SubsystemBase;

public class Drivebase extends SubsystemBase {
    private final WPI_TalonSRX frontLeft, backLeft, frontRight, backRight;

    public Drivebase(){
        frontLeft = new WPI_TalonSRX(Constants.DrivebaseConstants.leftFrontID);
        frontLeft.setNeutralMode(NeutralMode.Coast);

        backLeft = new WPI_TalonSRX(Constants.DrivebaseConstants.leftBackID);
        backLeft.setNeutralMode(NeutralMode.Coast);
        
        frontRight = new WPI_TalonSRX(Constants.DrivebaseConstants.rightFrontID);
        frontRight.setNeutralMode(NeutralMode.Coast);
        
        backRight = new WPI_TalonSRX(Constants.DrivebaseConstants.rightBackID);
        backRight.setNeutralMode(NeutralMode.Coast);

        backLeft.setInverted(true);
        frontLeft.setInverted(false);

    }

    public void driveLeft(final double val){
        frontLeft.set(val);
        backLeft.set(val);
    }

    public void driveRight(final double val){
        frontRight.set(val);
        backRight.set(val);
    }
    
    public void drive(final double left, final double right){
        driveLeft(left);
        driveRight(right);
        
    }

    @Override
    public void periodic(){
        SmartDashboard.putNumber("right front", frontRight.getMotorOutputPercent());
        SmartDashboard.putNumber("right back", backRight.getMotorOutputPercent());
        SmartDashboard.putNumber("left front", frontLeft.getMotorOutputPercent());
        SmartDashboard.putNumber("left back", backLeft.getMotorOutputPercent());
    }
    // Based on arcade drive pseudocode pdf found on CD
    public void driveArcade(final double forward, final double turn) {
        double max = Math.abs(forward);
        if (Math.abs(turn) > max) 
            max = Math.abs(turn);
        
        double sum = forward + turn;
        double dif = forward - turn;

        if (forward > 0) {
            if (turn >= 0) {
                driveLeft(max);
                driveRight(dif);
            } else {
                driveLeft(sum);
                driveRight(max);
            }
        } else {
            if (turn >= 0) {
                driveLeft(sum);
                driveRight(-max);
            } else {
                driveLeft(-max);
                driveRight(dif);
            }
        }
    }
}