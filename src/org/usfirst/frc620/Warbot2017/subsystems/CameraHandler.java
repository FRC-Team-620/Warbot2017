package org.usfirst.frc620.Warbot2017.subsystems;

import org.opencv.core.Mat;
import org.usfirst.frc620.Warbot2017.Robot;
import org.usfirst.frc620.Warbot2017.RobotMap;

import edu.wpi.cscore.CvSink;
import edu.wpi.cscore.CvSource;
import edu.wpi.cscore.UsbCamera;
import edu.wpi.first.wpilibj.CameraServer;
import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;

public class CameraHandler
{
	public final int NUMBER_OF_CAMERAS;
	private UsbCamera[] cameras;
	private CvSink[] cvs;
	private CvSource outputStream;
	private Mat frame;
	private int camera = 0;
	private final CameraServer server;

	public CameraHandler(int numberOfCameras)
	{
		cameras = new UsbCamera[2];
		
		cameras[0] = CameraServer.getInstance().startAutomaticCapture();
		cameras[1] = CameraServer.getInstance().startAutomaticCapture();
		
		NUMBER_OF_CAMERAS = numberOfCameras;
		server = CameraServer.getInstance();
//		
////		UsbCamera camera = server.startAutomaticCapture();
////		camera.setWhiteBalanceManual(0);
////		camera.setExposureManual(0);
//		if (NUMBER_OF_CAMERAS > 0)
//		{
//			cameras = new UsbCamera[NUMBER_OF_CAMERAS];
//			cvs = new CvSink[NUMBER_OF_CAMERAS];
//			for (int i = 0; i < NUMBER_OF_CAMERAS; i++)
//			{
//				cameras[i] = new UsbCamera("USB Camera " + i, i);
////				CameraServer.getInstance().removeServer("USB Camera " + i);
////				CameraServer.getInstance().removeCamera("USB Camera " + i);
//				cameras[i].setResolution(320, 240);
//				cvs[i] = server.getVideo(cameras[i]);
//			}
////			cameras[0].setResolution(360, 240);
//			outputStream = server.putVideo("Camera Stream", 320, 240);
//			frame = new Mat();
//			
//			switchToCamera(0);
//
//			new Thread(() ->
//			{
//				while (!Thread.interrupted())
//				{
//					System.out.println(camera);
//					cvs[camera].grabFrame(frame);
//					outputStream.putFrame(frame);
//				}
//			}).start();
//		}
	}

	public int getCurrentCam()
	{
		return camera;
	}

	public void nextCamera()
	{
//		if(NUMBER_OF_CAMERAS > 0)
//			switchToCamera((camera + 1) % NUMBER_OF_CAMERAS);
	}

	public void lastCamera()
	{
//		switchToCamera((camera + NUMBER_OF_CAMERAS - 1) % NUMBER_OF_CAMERAS);
	}

	public void switchToCamera(int cam)
	{
//		cameras[cam].setResolution(1, 1);
//		cvs.setSource(cameras[cam]);
//		cameras[cam].setResolution(360, 240);
//		camera = cam;
	}

	public void darkenCamera()
	{
		RobotMap.visionlightSpike.set(Relay.Value.kForward);
		UsbCamera camera = cameras[this.camera];
		camera.setWhiteBalanceManual(0);
		camera.setExposureManual(0);
	}

	public void brightenCamera()
	{
		RobotMap.visionlightSpike.set(Relay.Value.kOff);
		UsbCamera camera = cameras[this.camera];
		camera.setWhiteBalanceManual(50);
		camera.setExposureManual(50);
	}
}