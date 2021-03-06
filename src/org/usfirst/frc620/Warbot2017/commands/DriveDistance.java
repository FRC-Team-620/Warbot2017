package org.usfirst.frc620.Warbot2017.commands;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.util.DummyPIDOutput;

import edu.wpi.first.wpilibj.PIDController;
import edu.wpi.first.wpilibj.command.Command;

/**
 *
 */
public class DriveDistance extends Command {
	private double dist;
	private int time;
	private PIDController distController;
	private DummyPIDOutput distOutput;
    public DriveDistance(double dist) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distOutput = new DummyPIDOutput();
    	distController = new PIDController(0.03, 0.01, 0.0, Robot.dragWheel, distOutput);
    	distController.setOutputRange(-0.45, 0.45);
    	distController.setAbsoluteTolerance(0.5);
    	this.dist = dist;
    	time=15;
    }
    public DriveDistance(double dist, int time) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distOutput = new DummyPIDOutput();
    	distController = new PIDController(0.03, 0.01, 0.0, Robot.dragWheel, distOutput);
    	distController.setOutputRange(-0.45, 0.45);
    	distController.setAbsoluteTolerance(0.5);
    	this.dist = dist;
    	this.time = time;
    }
    public DriveDistance(double dist, int time, double speed) {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	distOutput = new DummyPIDOutput();
    	distController = new PIDController(0.03, 0.01, 0.0, Robot.dragWheel, distOutput);
    	distController.setOutputRange(-speed, speed);
    	distController.setAbsoluteTolerance(0.5);
    	this.dist = dist;
    	this.time = time;
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	distController.setSetpoint(Robot.dragWheel.getDistance() + dist );
    	distController.enable();
    	// #tear
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    	Robot.driveTrain.mecanumDrive(0.0, distController.get(), 0.0, 0.0);
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return (distController.onTarget()||(time<timeSinceInitialized()));
    }

    // Called once after isFinished returns true
    protected void end() {
    	System.out.println("DRIVE DIST END");
    	distController.disable();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	end();
    }
}
