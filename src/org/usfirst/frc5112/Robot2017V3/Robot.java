package org.usfirst.frc5112.Robot2017V3;

import org.usfirst.frc5112.Robot2017V3.commands.autoCommands.DriveForwardLine;
import org.usfirst.frc5112.Robot2017V3.commands.autoCommands.PlaceGearLeftPeg;
import org.usfirst.frc5112.Robot2017V3.commands.autoCommands.PlaceGearMiddlePeg;
import org.usfirst.frc5112.Robot2017V3.commands.autoCommands.PlaceGearRightPeg;
import org.usfirst.frc5112.Robot2017V3.commands.autoCommands.DoNothing;
import org.usfirst.frc5112.Robot2017V3.subsystems.*;

import edu.wpi.first.wpilibj.IterativeRobot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.command.Scheduler;
import edu.wpi.first.wpilibj.livewindow.LiveWindow;
import edu.wpi.first.wpilibj.smartdashboard.SendableChooser;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 * The VM is configured to automatically run this class, and to call the
 * functions corresponding to each mode, as described in the IterativeRobot
 * documentation. If you change the name of this class or the package after
 * creating this project, you must also update the manifest file in the resource
 * directory.
 */
public class Robot extends IterativeRobot {

	Command autonomousCommand;

	public static OI oi;
	public static Drivetrain drivetrain;
	public static Scaler scaler;
	public static TargetingSystem targetingSystem;
	public SendableChooser<Command> chooser;

	/**
	 * This function is run when the robot is first started up and should be
	 * used for any initialization code.
	 */
	public void robotInit() {
		RobotMap.init();
		drivetrain = new Drivetrain();
		scaler = new Scaler();
		targetingSystem = new TargetingSystem();
		chooser = new SendableChooser<>();

		chooser.addDefault("Do nothing", new DoNothing());
		chooser.addObject("Move Forward", new DriveForwardLine());
		chooser.addObject("Middle Peg", new PlaceGearMiddlePeg());
		chooser.addObject("Left Peg", new PlaceGearLeftPeg());
		chooser.addObject("Right Peg", new PlaceGearRightPeg());
		
		SmartDashboard.putData("Auto chooser", chooser);

		// OI must be constructed after subsystems. If the OI creates Commands
		// (which it very likely will), subsystems are not guaranteed to be
		// constructed yet. Thus, their requires() statements may grab null
		// pointers. Bad news. Don't move it.
		oi = new OI();

		// Instantiate the command used for the autonomous period
	}

	/**
	 * This function is called when the disabled button is hit. You can use it
	 * to reset subsystems before shutting down.
	 */
	public void disabledInit() {

	}

	public void disabledPeriodic() {
		Scheduler.getInstance().run();
	}

	public void autonomousInit() {
		// schedule the autonomous command (example)
		autonomousCommand = chooser.getSelected();
		if (autonomousCommand != null)
			autonomousCommand.start();
	}

	/**
	 * This function is called periodically during autonomous
	 */
	public void autonomousPeriodic() {
		Scheduler.getInstance().run();
	}

	public void teleopInit() {
		// This makes sure that the autonomous stops running when
		// teleop starts running. If you want the autonomous to
		// continue until interrupted by another command, remove
		// this line or comment it out.
		if (autonomousCommand != null)
			autonomousCommand.cancel();
	}

	/**
	 * This function is called periodically during operator control
	 */
	public void teleopPeriodic() {
		Scheduler.getInstance().run();
	}

	/**
	 * This function is called periodically during test mode
	 */
	public void testPeriodic() {
		LiveWindow.run();
	}
}
