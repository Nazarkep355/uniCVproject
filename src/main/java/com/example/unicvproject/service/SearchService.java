package com.example.unicvproject.service;

import com.example.unicvproject.entity.Lector;
import com.example.unicvproject.entity.enums.Degree;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class SearchService {

    @Autowired
    private StatisticsFinder statisticsFinder;
    @Autowired
    private HeadFinder headFinder;

    @Autowired
    private SalaryFinder salaryFinder;

    @Autowired
    private EmployeeCountFinder employeeCountFinder;
    @Autowired
    private GlobalFinder globalFinder;

    private final static String FIND_HEAD_REQUEST = "Who is head of department";
    private final static String RESULT_HEAD_COMMAND = "Head of %s department is %s";

    private final static String FIND_STATISTICS_COMMAND = "[ ]*Show [A-Z a-z]+ statistics[.]*";

    private final static String RESULT_STATISTICS_COMMAND = "assistants - %s. \n" +
            "associate professors - %s\n" +
            "professors - %s\n";
    private final static String AVG_SALARY_COMMAND =
            "Show the average salary for the department ";
    private final static String AVG_SALARY_RESULT = "The average salary of %s is %s\n";

    private final static String EMPLOYEE_COUNT_COMMAND = "Show count of employee for ";
    private final static String GLOBAL_SEARCH_COMMAND = "Global search by ";


    public Object parseCommand(String command) {
        if (command.contains(FIND_HEAD_REQUEST)) {
            return parseHeadFindCommand(command);
        }
        if (Pattern.compile(FIND_STATISTICS_COMMAND).matcher(command).matches()) {
            return parseStatisticsCommand(command);
        }
        if (command.contains(AVG_SALARY_COMMAND)) {
            return parseAverageSalaryCommand(command);
        }
        if (command.contains(EMPLOYEE_COUNT_COMMAND)) {
            return parseEmployeeCountCommand(command);
        }
        if (command.contains(GLOBAL_SEARCH_COMMAND)) {
            parseGlobalSearchCommand(command);
        } else {
            System.out.println();
        }
        return null;
    }
    private List<Lector> parseGlobalSearchCommand(String command){
        Matcher matcher = Pattern.compile(GLOBAL_SEARCH_COMMAND).matcher(command);
        String lecName = matcher.replaceAll("");
        matcher = Pattern.compile("[.$]*").matcher(lecName);
        lecName = matcher.replaceAll("");
        List<Lector> lectors = globalFinder.find(lecName);
        StringBuilder builder = new StringBuilder();
        for(Lector lector : lectors){
            builder.append(lector.getName()).append(", ");
        }
        System.out.println(builder.substring(0,builder.length()-2));
        return lectors;

    }

    private Integer parseEmployeeCountCommand(String command) {
        Matcher matcher = Pattern.compile(EMPLOYEE_COUNT_COMMAND).matcher(command);
        String depName = matcher.replaceAll("");
        matcher = Pattern.compile("[.$]*").matcher(depName);
        depName = matcher.replaceAll("");
        int size = employeeCountFinder.find(depName);
        System.out.println(size);
        return size;
    }

    private Lector parseHeadFindCommand(String command) {
        Matcher matcher = Pattern.compile(FIND_HEAD_REQUEST).matcher(command);
        String depName = matcher.replaceAll("").trim();
        Lector head = headFinder.find(depName);
        System.out.printf(RESULT_HEAD_COMMAND, depName, head.getName());
        return head;
    }

    private Map<Degree, Integer> parseStatisticsCommand(String command) {
        Matcher matcher = Pattern.compile("Show").matcher(command);
        String depName = matcher.replaceFirst("");
        matcher = Pattern.compile("statistics[.]*$").matcher(depName);
        depName = matcher.replaceAll("").trim();
        Map<Degree, Integer> map = statisticsFinder.find(depName);
        System.out.printf(RESULT_STATISTICS_COMMAND, map.get(Degree.ASSISTANT),
                map.get(Degree.ASSOCIATE_PROFESSOR),
                map.get(Degree.PROFESSOR));
        return map;
    }

    private Double parseAverageSalaryCommand(String command) {
        Matcher matcher = Pattern.compile(AVG_SALARY_COMMAND).matcher(command);
        String depName = matcher.replaceAll("");
        matcher = Pattern.compile("[.$]*").matcher(depName);
        depName = matcher.replaceAll("");
        Double salary = salaryFinder.find(depName);
        System.out.printf(AVG_SALARY_RESULT, depName, salary);
        return salary;
    }


}
