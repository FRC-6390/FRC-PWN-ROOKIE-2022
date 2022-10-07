package frc.robot.commands;

import java.util.Arrays;
import java.util.Iterator;

import edu.wpi.first.math.kinematics.ChassisSpeeds;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.shuffleboard.BuiltInLayouts;
import edu.wpi.first.wpilibj.shuffleboard.Shuffleboard;
import edu.wpi.first.wpilibj.shuffleboard.ShuffleboardLayout;
import edu.wpi.first.wpilibj2.command.CommandBase;
import frc.robot.subsystems.DesiredPosition;
import frc.robot.subsystems.DriveTrain;
import frc.robot.subsystems.LimeLightTurretSubsystem;
import frc.robot.subsystems.TurretedShooter;

public class BackwardCommand extends CommandBase {

    private DriveTrain driveTrain;
    private DesiredPosition currentCord;
    private boolean done;
    private ShuffleboardLayout waypointLayout = Shuffleboard.getTab("Auto").getLayout("Waypoint", BuiltInLayouts.kList).withSize(2, 8).withPosition(0, 0);
    private ShuffleboardLayout drivelayout = Shuffleboard.getTab("Auto").getLayout("Drive PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(2, 0);
    private ShuffleboardLayout rotationlayout = Shuffleboard.getTab("Auto").getLayout("Rotation PID", BuiltInLayouts.kList).withSize(2, 8).withPosition(4, 0);
    private static boolean shuffleboard = true;
    private Iterator<DesiredPosition> cords;

    public BackwardCommand(DriveTrain subsystem, DesiredPosition... cords) {
      this(subsystem,Arrays.asList(cords).iterator());
    }

    //public BackwardCommand(DriveTrain subsystem, Iterator<DesiredPosition> iterator) {
    //}

    public BackwardCommand(DriveTrain subsystem, Iterator<DesiredPosition> cords) {
      driveTrain = subsystem;
      this.cords = cords;
    }

    //outputs all data un FRC Shuffleboard
    private void setupShuffleboard(){
      //waypointLayout.addNumber("X", () -> currentCord.getPos().getX());
      //waypointLayout.addNumber("Y", () -> currentCord.getPos().getY());
      //waypointLayout.addNumber("Theta", () -> currentCord.getPos().getX());
      //waypointLayout.addBoolean("At Threshold", () -> currentCord.threashhold());
      //drivelayout.addNumber("X Error", () -> currentCord.getXPID().getError());
      //drivelayout.addNumber("Y Error", () -> currentCord.getYPID().getError());
      //rotationlayout.addNumber("R Error", () -> currentCord.getRotationPID().getError());
      //shuffleboard = false;
    }
  
    @Override
    public void initialize() {
      //currentCord = cords.next();
      //done = false;
      //if(shuffleboard)setupShuffleboard();
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