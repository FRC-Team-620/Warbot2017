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
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc620.Warbot2017.Robot;

/**
 *
 */
public class DriveDistance extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
    private int m_distance, lidarInitial;;
    private double m_scalar;
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveDistance(int distance, double scalar) {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        m_distance = distance;
        m_scalar=scalar;
        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	lidarInitial=Robot.lidar.getDistance();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {

		System.out.println("Lidar:"+Robot.lidar.getDistance());
    	if(m_distance>0)
    		Robot.driveTrain.mecanumDrive(0, m_scalar, 0, 0);
    	else if(m_distance<0)
    		Robot.driveTrain.mecanumDrive(0, -m_scalar, 0, 0);

    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	if(m_distance>0)return Robot.lidar.getDistance()<lidarInitial-m_distance;
    	if(m_distance<0)return Robot.lidar.getDistance()>lidarInitial-m_distance;
    	return false;//Robot.lidar.getDistance() <= m_distance;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
