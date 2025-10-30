package util;

// import se.miun.elal2203.dt187g.jpaint.Drawing;
// import se.miun.elal2203.dt187g.jpaint.geometry.Circle;
// import se.miun.elal2203.dt187g.jpaint.geometry.Point;
// import se.miun.elal2203.dt187g.jpaint.geometry.Rectangle;
// import se.miun.elal2203.dt187g.jpaint.geometry.Shape;

import java.nio.file.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import javax.print.DocFlavor.STRING;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import enums.Credits;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

import java.io.*;

public class FileHandler {

    public FileHandler() {

    }

    public static void save(List<Course> courseList, String fileName) {

        try {
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }

            File selectedFile = new File(fileName);
            FileWriter fileWriter = new FileWriter(selectedFile);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Course course : courseList) {

                String courseName = course.getCourseName().trim();
                Credits credits = course.getCredits();
                String type = course.toStringType();
                String overview = course.getOverview().trim();

                bw.write(courseName + "," + credits + "," + type + "," + overview);
                bw.newLine();

            }
            bw.close();
        } catch (IOException e) {
            System.err.println("Save failed:" + e.toString());
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static ArrayList<Course> load(String fileName) throws FileNotFoundException,
            IOException {

        ArrayList<Course> courseList = new ArrayList<Course>();
        try {
            if (!fileName.endsWith(".txt")) {
                fileName += ".txt";
            }
            // Path path = Path.of(fileName);
            // List<String> data = java.nio.file.Files.readAllLines(path);

            File file = new File(fileName);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            ArrayList<String> arrayList = new ArrayList<>();
            String rowInFile;
            while ((rowInFile = bufferedReader.readLine()) != null) {
                arrayList.add(rowInFile);
            }
            Course course;
            for (String courseRow : arrayList) {
                String splittedArray[] = courseRow.split(",");

                String courseName = splittedArray[0];
                String creditAsString = splittedArray[1];
                String typeAsString = splittedArray[2];
                String overview = splittedArray[3];

                Credits credit = Credits.getEnum(creditAsString);

                if (typeAsString.equals("Campus course")) {
                    course = new CampusCourse(courseName, credit, overview);
                    courseList.add(course);
                } else {
                    // "Online course"
                    course = new OnlineCourse(courseName, credit, overview);
                    courseList.add(course);
                }
            }
            bufferedReader.close();
            return courseList;
        } catch (FileNotFoundException e) {
            System.err.println("Could not load:" + fileName + ":" + " " + e.toString());
            e.printStackTrace();
            return null;
        }
    }

}
