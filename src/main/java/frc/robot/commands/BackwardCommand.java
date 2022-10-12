package frc.robot.commands;

import java.util.Arrays;
import java.util.Iterator;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;

import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;

import frc.robot.subsystems.TurretedShooter;

//testing will this push
public class BackwardCommand extends CommandBase {

    private DriveTrain driveTrain;
    private boolean done;


    public BackwardCommand(DriveTrain subsystem, DesiredPosition... cords) {
      this(subsystem,Arrays.asList(cords).iterator());
    }


    public BackwardCommand(DriveTrain subsystem, Iterator<DesiredPosition> cords) {
      driveTrain = subsystem;
      
    }
  
    @Override
    public void initialize() {

    }
  
    @Override
    public void execute() { 
        
        driveTrain.drive(new ChassisSpeeds(-0.5, 0.5, 0));
        Timer.delay(5);
        TurretedShooter.seeking = true;
        if(TurretedShooter.seeTarget() == true)
        { 
          TurretedShooter.shooterLeft.set(1); 
          TurretedShooter.shooterRight.set(-1); 
          TurretedShooter.preLeftShooter.set(1);
          TurretedShooter.preRightShooter.set(-1);
        }
        Timer.delay(10);
    }
  
    @Override
    public void end(boolean interrupted) {}
  
    @Override
    public boolean isFinished() {
      return done;
    }
  }