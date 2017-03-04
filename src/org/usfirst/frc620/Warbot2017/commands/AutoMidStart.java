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

/**
 * 284.48 cm to baseline
 * robot is 81.28 cm
 * 203.2 cm from front of robot to baseline
 * 
 * 80 in from front of robot to baseline
 */
public class AutoMidStart extends CommandGroup {
    public AutoMidStart() {
//    	System.out.println("Starting DriveTme(58)");
    	addSequential(new DriveTime(58));
//    	System.out.println("Starting DriveUntilDist(13, 1.0)");
    	addSequential(new DriveUntilDist(13, 1.0));
//    	addSequential(new DriveOnTooMuchCaffine(12, 1.0));
//    	System.out.println("Starting DepositGear()");
    	addSequential(new DepositGear());
//    	System.out.println("Starting RaseGearArm()");
    	addSequential(new RaiseGearArm());
//    	System.out.println("Starting Turn(-85)");
    	addSequential(new Turn(-85));
//    	System.out.println("Starting DriveTime(45)");
    	addSequential(new DriveTime(45));
//    	System.out.println("Starting Turn(85)");
    	addSequential(new Turn(85));
//    	System.out.println("Starting DriveTime(70)");
    	addSequential(new DriveTime(70));
    } 
}
