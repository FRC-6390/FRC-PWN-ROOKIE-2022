package frc.robot;

import edu.wpi.first.wpilibj.RobotBase;

public final class Main {
  private Main() {}
  //THIS IS MAHDI'S CHANGE
  public static void main(String... args) {
    RobotBase.startRobot(Robot::new);
    
  }
}
