// RobotBuilder Version: 2.0
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in the future.

package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.PIDOutput;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveTime extends Command implements PIDOutput {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private double startAngle;
	private long startTime;
	private long time;
	private double speed;
	private static final double TURN_TOLERANCE = 1;
	private static final double P = 0.03;
	private static final double I = 0.00;
	private static final double D = 0.00;
	private static final double F = 0.00;
	private PIDController turnController;
	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	/**
	 * The time it takes to drive one foot
	 */
	private final static double DIST_SCALAR = .06;
	
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public DriveTime(double dist) {
		this(dist * DIST_SCALAR, 0.3);
	}
	
	public DriveTime(double time, double speed) {
		this.speed = speed;
		this.time = (long) (time * 1000);
		startTime = System.currentTimeMillis();
		requires(Robot.driveTrain);
	}
	
	public DriveTime(double dist, double speed, boolean doesntDoAnything/*We just needed a 3 arg constructor*/) {
		this(dist * DIST_SCALAR * .3 / speed, speed);
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		System.out.println("Starting DriveTime");
		startTime = System.currentTimeMillis();
		startAngle = Robot.navX.getYaw();
		turnController = new PIDController(P, I, D, F, Robot.navX, this);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-.5, .5);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		turnController.setSetpoint(startAngle);
		turnController.enable();
	}
 
	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		
//		System.out.println("NavX:" + Robot.navX.getYaw());
	}

	// Make this return true when this Command no longer needs to rune execute()
	protected boolean isFinished() {
		return timeSinceInitialized() >= time / 1000;
	}

	// Called once after isFinished returns true
	protected void end() {
		System.out.println("DriveTime finished");
		turnController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
		turnController.disable();
	}

	@Override
	public void pidWrite(double output) {
		Robot.driveTrain.mecanumDrive(0, speed, output, 0);
	}
}
