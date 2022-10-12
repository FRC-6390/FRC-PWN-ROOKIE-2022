package frc.robot.commands;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.Feeder;
import frc.robot.subsystems.Intake;
import frc.robot.subsystems.LimeLightTurretSubsystem;
import frc.robot.subsystems.TurretedShooter;


import edu.wpi.first.math.geometry.Pose2d;
import edu.wpi.first.math.geometry.Rotation2d;
import edu.wpi.first.math.geometry.Translation2d;
import edu.wpi.first.math.trajectory.Trajectory;
import edu.wpi.first.math.trajectory.TrajectoryConfig;
import edu.wpi.first.math.trajectory.TrajectoryGenerator;


public class FollowPath extends edu.wpi.first.wpilibj2.command.CommandBase {
  public Pose2d currentPos;
  public Pose2d targetPos;
  private DriveTrain driveTrain;
  
  public FollowPath() {
    // Use requires() here to declare subsystem dependencies
    // eg. requires(chassis);
    //driveTrain = subsystem;
    TrajectoryConfig config = new TrajectoryConfig(10, 10);
    Trajectory trajectory = TrajectoryGenerator.generateTrajectory(new Pose2d(0,0, new Rotation2d(0)), List.of(new Translation2d(1,1), new Translation2d(2,-1) ), new Pose2d(3, 0, new Rotation2d(0)), config);
  }

  // Called just before this Command runs the first time
  @Override
  public void initialize() 
  {
     currentPos = driveTrain.getPos();
  }

  // Called repeatedly when this Command is scheduled to run
  @Override
  public void execute() 
  {
    currentPos = driveTrain.getPos();
    while(currentPos != new Pose2d(2, 0, new Rotation2d(0)))
    {
      driveTrain.drive(new ChassisSpeeds(0.5, 0, 0));
    }
    while(currentPos == new Pose2d(2, 0, new Rotation2d(0)))
    {
      TurretedShooter.seeking = true;
      if(TurretedShooter.seeTarget() == true)
        { 
          TurretedShooter.shooterLeft.set(1); 
          TurretedShooter.shooterRight.set(-1); 
          TurretedShooter.preLeftShooter.set(1);
          TurretedShooter.preRightShooter.set(-1);
        }
    }
  }

  

  // Called once after isFinished returns true
  @Override
  public void end(boolean interrupted) {}

  // Called when another command which requires one or more of the same
  // subsystems is scheduled to run
  public boolean isFinished()
  {
    return false;
  }
}
