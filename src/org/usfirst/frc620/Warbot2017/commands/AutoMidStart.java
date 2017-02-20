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

import edu.wpi.first.wpilibj.command.CommandGroup;

import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.subsystems.*;

/**
 * 284.48 cm to baseline
 * robot is 81.28 cm
 * 203.2 cm from front of robot to baseline
 */
public class AutoMidStart extends CommandGroup {
    public AutoMidStart() {
    	//addSequential(new DriveDistance(150, .5));
    	addSequential(new DriveUntilDist(30, .3));
    	//addSequential(new AlignForGearPeg());
    	addSequential(new DriveUntilDist(29,.3));
    	addSequential(new DriveUntilDist(25,.3));
    	System.out.println("DepositGear"+Robot.lidar.getDistance());
    	addSequential(new DepositGear());
    	addSequential(new DriveDistance(-100,.3));
    } 
}
