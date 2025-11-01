package util;

import java.util.ArrayList;
import java.util.List;
import enums.Credits;
import model.CampusCourse;
import model.Course;
import model.OnlineCourse;

import java.io.*;

public class FileHandler {

    private static final String FILE_TYPE = ".txt";

    public FileHandler() {

    }

    public static void save(List<Course> courseList, String fileName) throws IOException {
        try {
            if (!fileName.endsWith(FILE_TYPE)) {
                fileName += FILE_TYPE;
            }

            File selectedFile = new File(fileName);
            FileWriter fileWriter = new FileWriter(selectedFile);
            BufferedWriter bw = new BufferedWriter(fileWriter);
            for (Course course : courseList) {

                String courseName = course.getCourseName().trim();
                Credits credits = course.getCredits();
                String type = course.toStringType(course);
                String overview = course.getOverview().trim();

                bw.write(courseName + "," + credits + "," + type + "," + overview);
                bw.newLine();
            }
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
            e.getMessage();
        }
    }

    public static ArrayList<Course> load(String fileName) throws FileNotFoundException,
            IOException {

        ArrayList<Course> courseList = new ArrayList<Course>();
        try {
            if (!fileName.endsWith(FILE_TYPE)) {
                fileName += FILE_TYPE;
            }

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
                    course = new OnlineCourse(courseName, credit, overview);
                    courseList.add(course);
                }
            }
            bufferedReader.close();
            return courseList;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

}
