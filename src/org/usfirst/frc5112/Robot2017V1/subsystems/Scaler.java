package org.usfirst.frc5112.Robot2017V1.subsystems;

import org.usfirst.frc5112.Robot2017V1.RobotMap;
import org.usfirst.frc5112.Robot2017V1.commands.ScalerCommands.StopScaler;

import com.thegongoliers.output.interfaces.LifterInterface;
import edu.wpi.first.wpilibj.Joystick;
import edu.wpi.first.wpilibj.SpeedController;
import edu.wpi.first.wpilibj.command.Subsystem;

/**
 *
 */
public class Scaler extends Subsystem implements LifterInterface {
	
	public double scalerSpeed = 0.25;

	private final SpeedController scalerMotor = RobotMap.scalerScalerMotor;

	// Put methods for controlling this subsystem
	// here. Call these from Commands.

	public void initDefaultCommand() {
		setDefaultCommand(new StopScaler());
	}
	public void increaseScalerSpeed() {
		if (scalerSpeed <= 0.95) {
			scalerSpeed = scalerSpeed + 0.05;
		} else {
			scalerSpeed = 1.00;
		}
	}
	public void decreaseScalerSpeed() {
		if (scalerSpeed >= 0.05) {
			scalerSpeed = scalerSpeed - 0.05;
		} else {
			scalerSpeed = 0.00;
		}
	}

	public double scalerSpeed() {
		return scalerSpeed;
	}

	@Override
	public void stop() {
		scalerMotor.stopMotor();

	}

	@Override
	public void up(double speed) {
		scalerMotor.set(speed);

	}

	@Override
	public void down(double speed) {
		

	}

	@Override
	public boolean isAtBottom() {

		return false;
	}

	@Override
	public boolean isAtTop() {

		return false;
	}

	@Override
	public double getPosition() {

		return 0;
	}

	public void scalerOperatorControl(Joystick joystick) {
		scalerMotor.set(joystick.getY());
	}
}