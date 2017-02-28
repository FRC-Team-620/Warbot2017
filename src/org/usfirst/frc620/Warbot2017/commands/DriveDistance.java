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

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;

/**
 *
 */
public class DriveDistance extends Command {

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	private int m_distance, lidarInitial;;
	private double m_scalar;
	private PIDController distController;
	private static final double DIST_TOLERANCE = 2;
	private static final double DIST_P = 0.03;
	private static final double DIST_I = 0.00;
	private static final double DIST_D = 0.00;
	private static final double DIST_F = 0.00;
	private DummyPIDOutput distOutput;
	private PIDController turnController;
	private static final double TURN_TOLERANCE = 5;
	private static final double TURN_P = 0.03;
	private static final double TURN_I = 0.00;
	private static final double TURN_D = 0.00;
	private static final double TURN_F = 0.00;
	private DummyPIDOutput turnOutput;

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	public DriveDistance(int distance, double scalar) {

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		m_distance = distance;
		m_scalar = scalar;
		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
		requires(Robot.driveTrain);

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
	}

	// Called just before this Command runs the first time
	protected void initialize() {
		lidarInitial = Robot.lidar.getDistance();

		turnOutput = new DummyPIDOutput();
		turnController = new PIDController(TURN_P, TURN_I, TURN_D, TURN_F, Robot.navX.navX, turnOutput);
		turnController.setInputRange(-180.0, 180.0);
		turnController.setOutputRange(-1.0, 1.0);
		turnController.setAbsoluteTolerance(TURN_TOLERANCE);
		turnController.setContinuous(true);
		turnController.setSetpoint(Robot.navX.getYaw());

		distOutput = new DummyPIDOutput();
//		distController = new PIDController(DIST_P, DIST_I, DIST_D, DIST_F, Robot.lidar, turnOutput);
		distController = new PIDController(DIST_P, DIST_I, DIST_D, DIST_F, Robot.ultra, turnOutput);
		distController.setInputRange(0, 500);
		distController.setOutputRange(-1.0, 1.0);
		distController.setAbsoluteTolerance(DIST_TOLERANCE);
		distController.setContinuous(false);
		distController.setSetpoint(m_distance);

		turnController.enable();
		distController.enable();

	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {

//		System.out.println("Lidar:" + Robot.lidar.getDistance());
		Robot.driveTrain.mecanumDrive(0.0, distOutput.getOutput() * m_scalar, turnOutput.getOutput(), 0.0);
	}

	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return distController.onTarget();
	}

	// Called once after isFinished returns true
	protected void end() {
		distController.disable();
		turnController.disable();
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}
