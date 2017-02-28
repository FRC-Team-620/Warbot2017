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

//RobotBuilder Version: 2.0
//
//This file was generated by RobotBuilder. It contains sections of
//code that are automatically generated and assigned by robotbuilder.
//These sections will be updated in the future when you export to
//Java from RobotBuilder. Do not put any code or make any change in
//the blocks indicating autogenerated code or it will be lost on an
//update. Deleting the comments indicating the section will prevent
//it from being updated in the future.

import edu.wpi.first.wpilibj.I2C;
import edu.wpi.first.wpilibj.PIDSource;
import edu.wpi.first.wpilibj.PIDSourceType;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
*
*/
public class Lidar extends Subsystem implements PIDSource {
	PIDSourceType pid_source_type = PIDSourceType.kDisplacement;
	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=CONSTANTS

	// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DECLARATIONS

	I2C i2c;

	public Lidar() {
		i2c = new I2C(I2C.Port.kMXP, 0x62);
		// intLidar();
	}

	public int getDistance() {

		byte[] buffer;
		buffer = new byte[6];
		
//		i2c.writeBulk(new byte[]{0x0,0x04});
		if(i2c.write(0x0, 0x04)){
//			System.out.println("Write Failed.");
		}
		Timer.delay(.04);
		byte[] write = {(byte) 0x8f,0x0};
		
		if(i2c.writeBulk(write)){
//			System.out.println("Bulk write failed.");
		}
		buffer[0] = (byte) 0x8f;
		if(i2c.readOnly(buffer, 6)){
//			System.out.println("Read online Failed.");
		}
//		i2c.read(0x8f, 2, buffer);
//		byte[] temp = new byte[1];
//		do{
//			i2c.read(0x01, 1, temp);
////			System.out.println("val- " + temp[0]);
//		} while((temp[0] & 0x01) == 1);
////		Timer.delay(0.04);
////		i2c.read(0x8f, 2, buffer);
//		
//		
//		i2c.readOnly(write, 2);
////		i2c.writeBulk(buffer);
//		System.out.println(buffer[0] + "    " + buffer[1]);

		return (int) Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);
	}
	public int getDistanceOld() {

		byte[] buffer;
		buffer = new byte[2];

		i2c.write(0x00, 0x04);
		Timer.delay(0.04);
		i2c.read(0x8f, 2, buffer);

		return (int) Integer.toUnsignedLong(buffer[0] << 8) + Byte.toUnsignedInt(buffer[1]);
	}

	public double getDistanceInches() {
		return getDistance() / 2.54;
	}

	public void initDefaultCommand() {
		// BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=DEFAULT_COMMAND

		// Set the default command for a subsystem here.
		// setDefaultCommand(new MySpecialCommand());
	}

	@Override
	public void setPIDSourceType(PIDSourceType pidSource) {
		if (pidSource == PIDSourceType.kRate) {
			throw new UnsupportedOperationException(pidSource.name());
		}
		pid_source_type = pidSource;

	}

	@Override
	public PIDSourceType getPIDSourceType() {
		return pid_source_type;
	}

	@Override
	public double pidGet() {
		if (pid_source_type == PIDSourceType.kRate) {
			return 0;// TODO FIX
		} else {
			return getDistance();
		}
	}
}
