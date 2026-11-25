AutonomousCodeBiobuzzpackage org.firstinspires.ftc.teamcode.drive.opmode.autonomous;

// Imported Libraries

import com.acmerobotics.roadrunner.geometry.Pose2d;
import com.acmerobotics.roadrunner.trajectory.Trajectory;
import com.qualcomm.robotcore.eventloop.opmode.Autonomous;
import com.qualcomm.robotcore.eventloop.opmode.LinearOpMode;
import com.qualcomm.robotcore.hardware.DcMotor;

import org.firstinspires.ftc.teamcode.drive.SampleTankDrive;
import org.firstinspires.ftc.teamcode.drive.SubSystem;

// Other Libraries


@Autonomous(group = "drive")
public class AutoShootSecond extends LinearOpMode {




    @Override
    public void runOpMode() throws InterruptedException {
        SampleTankDrive drive = new SampleTankDrive(hardwareMap);         // Change to SampleTankDrive if using tank drive
        drive.setMode(DcMotor.RunMode.RUN_WITH_ENCODER);

        SubSystem subSystem = new SubSystem();
        subSystem.init(hardwareMap, drive);



        if (isStopRequested()) return;
        Trajectory trajectory = drive.trajectoryBuilder(new Pose2d())
                .forward(30)
                .turn(Math.toRadians(90))
                .back(20)
                .build();

        drive.followTrajectory(trajectory);


        subSystem.startLauncher();




        waitForStart();
        Trajectory trajectory2 = drive.trajectoryBuilder(new Pose2d())
                .forward(5)
                .build();
        drive.turn(Math.toRadians(90));
        drive.followTrajectory(trajectory2);
    }
}