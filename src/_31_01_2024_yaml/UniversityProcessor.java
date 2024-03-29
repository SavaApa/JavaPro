package _31_01_2024_yaml;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class UniversityProcessor {

    // 1. Получить список всех курсов в университете
    public static List<Course> getAllCourses(University university) throws RuntimeException {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .toList();
    }

    // 2. Найти количество курсов в каждом департаменте
    public static Map<String, Long> getCoursesCountByDepartment(University university) {
        return university.getDepartments().stream()
                .collect(Collectors.toMap(Department::getName, department -> (long) department.getCourses().size()));
    }

    // 3. Получить список названий всех курсов, которые имеют более 3 кредитов
    public static List<String> getCoursesWithMoreThanThreeCredits(University university) {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .filter(el -> el.getCredits() > 3)
                .map(Course::getTitle)
                .collect(Collectors.toList());
    }

    // 4. Найти всех профессоров, которые ведут более одного курса
    public static Set<String> getProfessorsTeachingMultipleCourses(University university) {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .collect(Collectors.groupingBy(Course::getProfessor, Collectors.counting()))
                .entrySet().stream()
                .filter(el -> el.getValue() > 1)
                .map(Map.Entry::getKey)
                .collect(Collectors.toSet());
    }

    // 5. Получить мапу курсов, где ключ - название курса, значение - список тем
    public static Map<String, List<String>> getCourseTopicsMap(University university) {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .collect(Collectors.toMap(Course::getTitle, Course::getTopics));
    }

    // 6. Найти департаменты, где все курсы имеют более 3 кредитов
    public static List<String> getDepartmentsWithAllCoursesMoreThanThreeCredits(University university) {
        return university.getDepartments().stream()
                .filter(el -> el.getCourses().stream().allMatch(ele -> ele.getCredits() <= 3))
                .map(Department::getName)
                .collect(Collectors.toList());
    }

    // 7. Получить список курсов, сгруппированных по количеству кредитов
    public static Map<Integer, List<Course>> getCoursesGroupedByCredits(University university) {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .collect(Collectors.groupingBy(Course::getCredits));
    }

    // 8. Найти департамент с самым большим количеством курсов
    public static String getDepartmentWithMostCourses(University university) {
        return university.getDepartments().stream()
                .max(Comparator.comparingInt(el -> el.getCourses().size()))
                .map(Department::getName)
                .orElse(null);
    }

    // 9. Получить мапу, где ключ - название департамента, а значение - среднее количество кредитов курсов в департаменте
    public static Map<String, Double> getAverageCreditsPerDepartment(University university) {
        return university.getDepartments().stream()
                .collect(Collectors.groupingBy(Department::getName,
                        Collectors.flatMapping(department -> department.getCourses().stream(),
                                Collectors.averagingInt(Course::getCredits))));

    }

    // 10. Найти курсы, у которых более 2 тем и профессор начинается на 'Dr.'
    public static List<Course> getCoursesWithMoreThanTwoTopicsAndDrProfessor(University university) {
        return university.getDepartments().stream()
                .flatMap(el -> el.getCourses().stream())
                .filter(el -> el.getProfessor().startsWith("Dr.") && el.getTopics().size() > 2)
                .collect(Collectors.toList());
    }
}
