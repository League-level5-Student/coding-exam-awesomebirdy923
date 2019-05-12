package Coding_Exam_A;

import java.awt.Color;

import javax.swing.JOptionPane;

import org.jointheleague.graphical.robot.Robot;

public class CodingExamA {
	public static void main(String[] args) {
		/*
		 * Write a program that asks the user for three pieces of information.
		 * 1. How many robots
		 * 2. The color of the shapes
		 * 3. How many sides each shape will have
		 * 
		 * Once the information has been collected, the program will then make the requested number of robots
		 * each draw the requested shape in the requested color. The robots should execute at the same time so 
		 * Threads will need to be used. Arrange the robots so that the shapes do not overlap.
		 * For full credit, define the Thread functions using lambdas.
		 * 
		 * See the Coding_Exam_A_Demo.jar for an example of what the finished product should look like.
		 */
		int robotCount = Integer.parseInt(JOptionPane.showInputDialog("How many robots:"));
		String color = JOptionPane.showInputDialog("What color:");
		int sides = Integer.parseInt(JOptionPane.showInputDialog("Sides:"));
		Thread[] threads = new Thread[robotCount];
		for (int i = 0; i < threads.length; i++) {
			final int x = i;
			threads[i] = new Thread(()->{
				Robot robot = new Robot(x*150, 100);
				robot.penDown();
				if(color.equalsIgnoreCase("red")) {
					robot.setPenColor(Color.RED);
				} else if(color.equalsIgnoreCase("green")) {
					robot.setPenColor(Color.GREEN);
				} else if(color.equalsIgnoreCase("blue")) {
					robot.setPenColor(Color.BLUE);
				} else if(color.equalsIgnoreCase("yellow")) {
					robot.setPenColor(Color.YELLOW);
				} else {
					JOptionPane.showMessageDialog(null, "That color doesn't exist!");
					System.exit(1);
				}
				for (int j = 0; j < sides; j++) {
					robot.move(360/sides);
					robot.turn(360/sides);
				}
			});
			try {
				threads[i].join();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for (int i = 0; i < threads.length; i++) {
			threads[i].start();
		}
	}
}
