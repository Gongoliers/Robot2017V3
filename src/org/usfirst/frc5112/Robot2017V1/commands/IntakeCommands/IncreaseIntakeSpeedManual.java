package org.usfirst.frc5112.Robot2017V1.commands.IntakeCommands;

import org.usfirst.frc5112.Robot2017V1.Robot;

import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class IncreaseIntakeSpeedManual extends Command {

    public IncreaseIntakeSpeedManual() {
        requires(Robot.intake);
    }

    protected void initialize() {
    	Robot.intake.increaseIntakeSpeed();
    }

    protected void execute() {
    }

    protected boolean isFinished() {
        return true;
    }

    protected void end() {
    	SmartDashboard.putNumber("IntakeSpeedDisplay", Robot.intake.intakeSpeed);
    }

    protected void interrupted() {
    	end();
    }
}
