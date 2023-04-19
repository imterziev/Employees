package dataOperations;

import entities.Employee;
import entities.Project;

import java.io.File;
import java.io.FileNotFoundException;
import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class InputParser {
    private File file;
    private Scanner scanner;

    public InputParser(File file) {
        this.file = file;
    }

    public Map<Integer, Employee> getEmployees() {

        Map<Integer, Employee> employeeMap = new LinkedHashMap<>();

        try {
            scanner = new Scanner(this.file);
        } catch (FileNotFoundException e) {
            // handle exception
        }

        while (scanner.hasNext()) {
            String[] tokens = scanner.next().split(";");

            if (tokens[3].equals("NULL")) {
                tokens[3] = String.valueOf(LocalDate.now());
            }

            LocalDate startDate = DateFormatter.getDate(tokens[2]);
            LocalDate endDate = DateFormatter.getDate(tokens[3]);

            Employee employee = new Employee(Integer.parseInt(tokens[0]));
            Project project = new Project(Integer.parseInt(tokens[1]), startDate, endDate);

            int key = Integer.parseInt(tokens[0]);

            employeeMap.putIfAbsent(key, employee);
            employeeMap.get(key).add(project);
        }

        return employeeMap;
    }
}