/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Richochetpackage;


import java.util.concurrent.TimeUnit;

/**
 *
 * @author JD

public class Test {
      public static void main(String[] args) throws InterruptedException {
        //testing out the simulation
        testGolfSimulation(55, 9.8, 10, 50, 5);
        
    }
       
    public static void testGolfSimulation(double angleOfShot, double gravity, double heightOfStart,
        double initialSpeed, double airResistance) throws InterruptedException{
        RichochetSimController testGolfSim = new RichochetSimController();
        testGolfSim.startTime();
        for(int i=0; i <100; i++){
            System.out.println("X: " + testGolfSim.calculateHorizontalComponent(angleOfShot, airResistance, initialSpeed, testGolfSim.getTime()));
            System.out.println("Y: " + testGolfSim.calculateVerticalComponent(angleOfShot, gravity, heightOfStart, initialSpeed, testGolfSim.getTime()));
            System.out.println("Time: " + testGolfSim.getTime());
            TimeUnit.MILLISECONDS.sleep(100);
        }
    }
        
} */
