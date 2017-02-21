package org.usfirst.frc620.Warbot2017;

import org.usfirst.frc620.Warbot2017.commands.AlignForGearPeg;
import org.usfirst.frc620.Warbot2017.commands.AutoLeftStart;
import org.usfirst.frc620.Warbot2017.commands.AutoMidStart;
import org.usfirst.frc620.Warbot2017.commands.AutoRightStart;
import org.usfirst.frc620.Warbot2017.commands.AutonomousCommand;
import org.usfirst.frc620.Warbot2017.commands.Climb;
import org.usfirst.frc620.Warbot2017.commands.DepositGear;
import org.usfirst.frc620.Warbot2017.commands.DriveWithXbox;
import org.usfirst.frc620.Warbot2017.commands.LowerBallMech;
import org.usfirst.frc620.Warbot2017.commands.LowerGearArm;
import org.usfirst.frc620.Warbot2017.commands.RaiseGearArm;
import org.usfirst.frc620.Warbot2017.commands.SwitchSides;
import org.usfirst.frc620.Warbot2017.commands.ToggleRelay;
import org.usfirst.frc620.Warbot2017.commands.Turn;

import edu.wpi.first.wpilibj.XboxController;
import edu.wpi.first.wpilibj.buttons.JoystickButton;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * This class is the glue that binds the controls on the physical operator
 * interface to the commands and command groups that allow control of the robot.
 */
public class OI {
    private XboxController xbox;
    private JoystickButton a;
    private JoystickButton b;
    private JoystickButton x;
    private JoystickButton y;
    private JoystickButton lBumper;
    private JoystickButton rBumper;
    public int gyro=0;

    public OI() {
        xbox = new XboxController(0);
        
        rBumper = new JoystickButton(xbox, 6);
        rBumper.whenPressed(new Climb());
        
        lBumper = new JoystickButton(xbox, 5);
        lBumper.whenPressed(new SwitchSides());
        
        y = new JoystickButton(xbox, 4);
        y.whenPressed(new RaiseGearArm());
        
        x = new JoystickButton(xbox, 3);
        x.whenPressed(new LowerGearArm());
        
        a = new JoystickButton(xbox, 1);
        a.whenPressed(new LowerBallMech());
        
        b = new JoystickButton(xbox, 2);
        b.whenPressed(new DepositGear());
       
        // SmartDashboard Buttons
        SmartDashboard.putData("Autonomous Command", new AutonomousCommand());
        SmartDashboard.putData("DriveWithXbox", new DriveWithXbox());
        SmartDashboard.putData("RaiseGearArm", new RaiseGearArm());
        SmartDashboard.putData("LowerGearArm", new LowerGearArm());
        SmartDashboard.putData("DepositGear", new DepositGear());
        SmartDashboard.putData("AutoLeftStart", new AutoLeftStart());
        SmartDashboard.putData("AutoRightStart", new AutoRightStart());
        SmartDashboard.putData("AutoMidStart", new AutoMidStart());
        SmartDashboard.putData("AlignForGearPeg", new AlignForGearPeg());
    }

    public XboxController getXbox() {
        return xbox;
    }
    
    public boolean getLBumper() {
    	return xbox.getRawButton(5);
    }
    
    public double getRightXAxis(){
    	return xbox.getRawAxis(4);
    }
    
    public double getLTrigger() {
    	return xbox.getRawAxis(2);
    }
    
    public double getRTrigger() {
    	return xbox.getRawAxis(3);
    }
}