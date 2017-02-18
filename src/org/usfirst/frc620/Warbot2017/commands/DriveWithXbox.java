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
import org.usfirst.frc620.Warbot2017.subsystems.Lidar;
import org.usfirst.frc620.Warbot2017.subsystems.NavX;
import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;

/**
 *
 */
public class DriveWithXbox extends Command {

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
 
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_DECLARATIONS
	//UsbCamera test = new UsbCamera("test",0 );
	XboxController xbox;
	DriveDistance driveDist;
	Climb climb;
	LowerGearArm lowergeararm;
	RaiseGearArm raisegeararm;
	DepositGear depgear;
	Turn turn;
	double x;
	double y;
	double z;
	double LTrigger;
	int pov;
	boolean front;
	boolean Lbumper, camerafront;
	private float startingAngle;

    // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
    public DriveWithXbox() {

    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTRUCTOR
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=VARIABLE_SETTING
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.driveTrain);

        // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        
        
    }
    protected void initialize() {
		xbox = new XboxController(0);
		front=true;
	}

	// Called repeatedly when this Command is scheduled to run
	protected void execute() {
		System.out.println(Robot.navX.getYaw());
		LTrigger = xbox.getRawAxis(2);
		Lbumper = xbox.getRawButton(5);
		//System.out.println("NavX:"+Robot.navX.getYaw());
		//System.out.println("Lidar:"+Robot.lidar.getDistance());
		
		//Robot.climber.climb(xbox.getRawAxis(3));
		if(xbox.getRawButton(6)){
			climb = new Climb(xbox);
			Scheduler.getInstance().add(climb);
		}
		if(xbox.getRawButton(4)){
			System.out.println("lower");
			lowergeararm=new LowerGearArm();
			Scheduler.getInstance().add(lowergeararm);
		}
		if(xbox.getRawButton(3)){
			System.out.println("raise");
			raisegeararm= new RaiseGearArm();
			Scheduler.getInstance().add(raisegeararm);
		}
		if(xbox.getRawButton(2)){
			depgear=new DepositGear();
			Scheduler.getInstance().add(depgear);
		}
		//SWITCHING THE "FRONT"
		if(xbox.getRawButton(1))front=!front;//A
		if(front){
			z/*x if test bot*/ = -xbox.getRawAxis(0);
			y = xbox.getRawAxis(1);
			x/*z if test bot*/ = -xbox.getRawAxis(4);//not negative if test bot
		}
		else{
			z/*x if test bot*/ = xbox.getRawAxis(0);
			y = -xbox.getRawAxis(1);
			x/*z if test bot*/ = xbox.getRawAxis(4);//negative if test bot
		}
		/*if(x==0&&y==0&&z==0){
			Robot.navX.reset();
			startingAngle = Robot.navX.getYaw();
		}
		if(!(z==0)||!(y==0)){
			System.out.println("y"+y);
			System.out.println("Anti-drift test");
			double strafe = x;
	    	float dTheta = Robot.navX.getYaw() - startingAngle;
	    	double rotate = 0.0;
	    	
	    	if(dTheta > 5) {
	    		// pos turn counterclockwise
	    		rotate = 0.5;
	    	} else if(dTheta < 5) {
	    		rotate = -0.5;
	    	}
	    	
	    	Robot.driveTrain.mecanumDrive(rotate, 0, strafe, 0);
		}
		else{*/
		//CONTROLLER SCALING
		if (!Lbumper) {//LEFT TRIGGER SCALING
			x = (Math.abs(x) < 0.3) ? 0 : x*(1-(LTrigger*.75));// X Dead Zone
			y = (Math.abs(y) < 0.3) ? 0 : y*(1-(LTrigger*.75));// Y Dead Zone
			z = (Math.abs(z) < 0.3) ? 0 : z*(1-(LTrigger*.6));// Z Dead Zone
		} else if (Lbumper) {//L BUMPER SCALING
			x = (Math.abs(x) < 0.3) ? 0 : x * .5;// X Dead Zone and scaling
			y = (Math.abs(y) < 0.3) ? 0 : y * .5;// Y Dead Zone and scaling
			z = (Math.abs(z) < 0.3) ? 0 : z * .7;// Z Dead Zone and scaling
		}
		
		Robot.driveTrain.mecanumDrive(-x, -y, -z, 0);
		//}
	}
	// Make this return true when this Command no longer needs to run execute()
	protected boolean isFinished() {
		return false;
	}

	// Called once after isFinished returns true
	protected void end() {
	}

	// Called when another command which requires one or more of the same
	// subsystems is scheduled to run
	protected void interrupted() {
	}
}