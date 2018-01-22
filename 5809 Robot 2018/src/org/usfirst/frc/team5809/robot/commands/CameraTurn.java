package org.usfirst.frc.team5809.robot.commands;

import org.usfirst.frc.team5809.robot.OI;
import org.usfirst.frc.team5809.robot.Robot;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;

/**
 *
 */
public class CameraTurn extends Command {

	
	private double commandMagnitude = 0.0 ;
	private boolean initFromOI     = true;
	private double commandTimeout = 5;
    public CameraTurn() {
    	
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	//requires(Robot.cameraPID);
    }
    
    public CameraTurn(double magnitude, double driveTimeout) {
    	commandMagnitude = magnitude;
    	initFromOI = false;
    	commandTimeout = driveTimeout;
    	
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
    	requires(Robot.driveTrain);
    	//requires(Robot.pivotTurnPID);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
    	//Robot.driveTrain.setBrake();
    	setInterruptible(true);
    	setTimeout(commandTimeout);
    	
    	 if(initFromOI) {
         	commandMagnitude = OI.getDriveMag();
         }
    	 
    	 Robot.driveTrain.CameraPIDInit(commandMagnitude, 0);
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
    	return (Robot.driveTrain.CameraPIDIsFinished()) || isTimedOut();
    }

    // Called once after isFinished returns true
    protected void end() {
    	Robot.driveTrain.CameraPIDStop();
    	Robot.driveTrain.setCoast();
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    	Robot.driveTrain.CameraPIDStop();
    	Robot.driveTrain.setCoast();
    }
}
