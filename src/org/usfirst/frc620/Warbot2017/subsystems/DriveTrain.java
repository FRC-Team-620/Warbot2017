// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc620.Warbot2017.subsystems;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;
import org.usfirst.frc620.Warbot2017.commands.DriveWithXbox;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.RobotDrive;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class DriveTrain extends Subsystem {
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS
	SpeedController pWMJagRL = RobotMap.driveTrainfrontRight;
	SpeedController pWMJagRR = RobotMap.driveTrainbackRight;
	SpeedController pWMJagFL = RobotMap.driveTrainfrontLeft;
	SpeedController pWMJagFR = RobotMap.driveTrainbackLeft;
	RobotDrive robotDrive = RobotMap.driveTrainRobotDrive;
	DummyPIDOutput turnOutput = new DummyPIDOutput();
	PIDController turnConroller = new PIDController(.01, 0, 0, Robot.navX, turnOutput);
	DummyPIDOutput strafeOutput = new DummyPIDOutput();
	PIDController strafeConroller = new PIDController(.3, 0, 0, Robot.navX, strafeOutput); // TODO
																							// SWITCH
																							// TO
																							// ENCODER

	final static double[] vals = { .3, 1 };

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	// GyroITG3200 gyro3200 = RobotMap.driveTrainGyro3200;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.
	public DriveTrain() {
		turnConroller.setAbsoluteTolerance(1);
		turnConroller.setContinuous(true);
		turnConroller.setInputRange(-180, 180);
		turnConroller.setOutputRange(-1, 1);
		strafeConroller.setAbsoluteTolerance(4);
		strafeConroller.setInputRange(-1000, 1000);
		turnConroller.setOutputRange(-.5, .5);
		turnConroller.enable();
	}

	public void initDefaultCommand() {
		DriveWithXbox DriveWithXbox = new DriveWithXbox();
		setDefaultCommand(DriveWithXbox);

		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	boolean isTurnCorrecting, isStrafeCorrecting = false;

	public void mecanumDrive(double strafe, double drive, double turn, double gyro) {
		// if (Math.abs(turn) < .25 && turn != 0)
		// turn = Math.signum(turn) * .25;

		if (turn == 0 && strafe == 0 && drive != 0) {
			// TODO turn correction
			if (isTurnCorrecting) {
				// TODO PID OUTPUT
//				if (turnOutput.isUpdated()) {
					turn = turnOutput.getOutput();
//				}

			} else {
				// TODO INIT
				turnConroller.setSetpoint(Robot.navX.getYaw());
				isTurnCorrecting = true;
//				turnOutput.setUpdated(false);
			}
		} else {
			isTurnCorrecting = false;
		}
		if (turn == 0 && drive == 0 && strafe != 0) {
			// TODO: encoder correction for strafe
		}
		System.out.println("sdfsdf " + strafe + " drive  " + drive + " turn " + turn);
		robotDrive.mecanumDrive_Cartesian(strafe, drive, turn, gyro);
	}

	// private static double linearize(double inValue)
	// {
	// int top = (int)Math.ceil(vals.length * Math.abs(inValue));
	// int bot = (int)Math.floor(vals.length * Math.abs(inValue));
	//
	// double val = Math.signum(inValue) * ((Math.abs(inValue) - vals[bot]) *
	// (vals[top] - vals[bot]) + vals[bot]);
	// if(val < .25)
	// return 0;
	// else
	// return val;
	// }
}
