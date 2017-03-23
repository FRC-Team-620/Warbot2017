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

import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;


/**
 *
 */
public class Climber extends Subsystem {

    private final SpeedController climbMotor = RobotMap.climberclimbMotor;


    public void initDefaultCommand() {
    }
    public void climb(double x){
    	climbMotor.set(x);
    }
    public void kill(){
    	climbMotor.stopMotor();
    }
    public boolean isUp()
    {
    	return !RobotMap.climberUpLimit.get();
    }
    public boolean isDown()
    {
    	return !RobotMap.climberDownLimit.get();
    }
    public boolean isInContact()
    {
    	return !RobotMap.climberContactLimit.get();
    }
}

