import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;
import java.util.stream.Collectors;

public class JavaExampleFindSecondHighestValue {
    public static void main(String args[]) {
        System.out.println("Example for Java Collection Find second highest value");
        List<Employee> employees = new ArrayList<>();
        employees.add(new Employee("Jhon", "Manager", 1000.45f));
        employees.add(new Employee("Jack", "Developer", 500.45f));
        employees.add(new Employee("Ron", "Developer", 510.45f));
        employees.add(new Employee("Tim", "Developer", 510.45f));
        employees.add(new Employee("Cara", "Sr. Developer", 750.45f));
        employees.add(new Employee("Jackson", "Sr. Developer", 710.45f));
        JavaExampleFindSecondHighestValue javaExampleFindSecondHighestValue = new JavaExampleFindSecondHighestValue();

        Map<String, List<Employee>> groupedDesignation = employees.stream()
                .collect(Collectors.groupingBy(Employee::getDesignation));
        groupedDesignation.entrySet().stream().forEach(javaExampleFindSecondHighestValue::printSecondHigestSalariedEmployeeOfDesignation);
    }

    public void printSecondHigestSalariedEmployeeOfDesignation(Entry<String, List<Employee>> entry) {
        String designation = entry.getKey();
        List<Employee> employees = entry.getValue();
        this.getSecondHighestSalariedEmployee(employees).ifPresentOrElse(employee -> {
            System.out.println("Second highest Salaried employee for Designation: " + designation + " is " + employee);
        }, () -> System.out.print("List doesn't have enough values"));

    }

    public Optional<Employee> getSecondHighestSalariedEmployee(List<Employee> employees) {
        return employees.stream().sorted(Comparator.comparingDouble(Employee::getSalary).reversed()).limit(2).skip(1)
                .findFirst();
    }

    public static class Employee {
        private String name;
        private String designation;
        private float salary;

        public Employee(String name, String designation, float salary) {
            this.designation = designation;
            this.name = name;
            this.salary = salary;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getName() {
            return this.name;
        }

        public void setDesignation(String designation) {
            this.designation = designation;
        }

        public String getDesignation() {
            return this.designation;
        }

        public void setSalary(float salary) {
            this.salary = salary;
        }

        public float getSalary() {
            return this.salary;
        }

        @Override
        public String toString() {
            return new StringBuilder().append("{").append("Name: " + this.getName()).append(", ")
                    .append("Designation: " + this.getDesignation()).append(", ").append("Salary: " + this.getSalary())
                    .append("}").toString();
        }

    }
}
