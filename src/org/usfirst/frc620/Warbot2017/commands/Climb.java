package org.usfirst.frc620.Warbot2017.commands;
//RobotBuilder Version: 2.0
//
//This file was generated by RobotBuilder. It contains sections of
//code that are automatically generated and assigned by robotbuilder.
//These sections will be updated in the future when you export to
//Java from RobotBuilder. Do not put any code or make any change in
//the blocks indicating autogenerated code or it will be lost on an
//update. Deleting the comments indicating the section will prevent
//it from being updated in the future.

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;

/**
*
*/
public class Climb extends Command {
	private double m_power;
	private XboxController xbox;
 // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

 // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS

 // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
 public Climb(XboxController xboxin) {
 // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
	 xbox=xboxin;
     // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

     // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
     // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
     requires(Robot.climber);

     // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
 }

 // Called just before this Command runs the first time
 protected void initialize() {
		}

 // Called repeatedly when this Command is scheduled to run
 protected void execute() {
	 m_power= xbox.getRawAxis(3);
	 System.out.println(xbox.getRawButton(6));
if(RobotMap.limit.get()){
	Robot.climber.climb(m_power);
}
else if(RobotMap.limit.get()&&xbox.getRawButton(5)){
	Robot.climber.climb(m_power);
}
 }

 // Make this return true when this Command no longer needs to run execute()
 protected boolean isFinished() {
	 return xbox.getRawButton(6);
	 //return false;
 }

 // Called once after isFinished returns true
 protected void end() {
 }

 // Called when another command which requires one or more of the same
 // subsystems is scheduled to run
 protected void interrupted() {
 }
}

