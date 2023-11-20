import java.util.*;

class Employee {
    private String fullName;
    private int age;
    private String dateOfBirth;
    private double salary;
    private Department department;

    public Employee(String fullName, int age, String dateOfBirth, double salary, Department department) {
        this.fullName = fullName;
        this.age = age;
        this.dateOfBirth = dateOfBirth;
        this.salary = salary;
        this.department = department;
    }

    public String getFullName() {
        return fullName;
    }

    public int getAge() {
        return age;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public double getSalary() {
        return salary;
    }

    public Department getDepartment() {
        return department;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public void displayInfo() {
        System.out.println("---------------------------");
        System.out.println("Full Name: " + fullName);
        System.out.println("Age: " + age);
        System.out.println("Date of Birth: " + dateOfBirth);
        System.out.println("Salary: " + salary);
        System.out.println("Department: " + department);
        System.out.println("---------------------------");
    }
}

enum Department {
    DEVELOPMENT, TESTING, HR, ACCOUNTS   // Add more departments if needed
}

class EmployeeManagement {
    Map<String, Employee> employees;
    private Scanner scanner;

    public EmployeeManagement() {
        employees = new HashMap<>();
        scanner = new Scanner(System.in);
    }

    public void showAllEmployees() {
        employees.values().forEach(Employee::displayInfo);
    }

    // Method to filter employees by department
    public void filterEmployees() {
        System.out.println("Choose filter criteria:");
        System.out.println("1. Department");
        System.out.println("2. Salary");
        System.out.println("3. Age");
        System.out.print("Enter your choice: ");

        int filterChoice = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        switch (filterChoice) {
            case 1:
                filterByDepartment();
                break;
            case 2:
                filterBySalary();
                break;
            case 3:
                filterByAge();
                break;
            default:
                System.out.println("Invalid choice for filter.");
        }
    }

    public void filterByDepartment() {
        System.out.println("Enter department name (DEVELOPMENT, TESTING, HR, ACCOUNTS): ");
        String departmentInput = scanner.nextLine().toUpperCase();
        Department department = Department.valueOf(departmentInput);

        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == department) {
                employee.displayInfo();
            }
        }
    }

    public void filterBySalary() {
        System.out.println("Enter minimum salary: ");
        double minSalary = scanner.nextDouble();

        System.out.println("Enter maximum salary: ");
        double maxSalary = scanner.nextDouble();
        scanner.nextLine(); // Consume newline character

        for (Employee employee : employees.values()) {
            if (employee.getSalary() >= minSalary && employee.getSalary() <= maxSalary) {
                employee.displayInfo();
            }
        }
        System.out.println(("Employees not found"));
    }

    public void filterByAge() {
        System.out.println("Enter minimum age: ");
        int minAge = scanner.nextInt();

        System.out.println("Enter maximum age: ");
        int maxAge = scanner.nextInt();
        scanner.nextLine(); // Consume newline character

        for (Employee employee : employees.values()) {
            if (employee.getAge() >= minAge && employee.getAge() <= maxAge) {
                employee.displayInfo();
            }
        }
        System.out.println(("Employees not found"));
    }

    // Method to search for an employee by name
    public void searchEmployee() {
        System.out.println("Enter employee name to search: ");
        String searchName = scanner.nextLine();
        
        Employee employee = employees.get(searchName);
        if (employee != null) {
            employee.displayInfo();
        } else {
            System.out.println("Employee not found.");
        }
    }

    // Method to update an employee's record
    public void updateEmployee(String name) {
        Employee employeeToUpdate = employees.get(name);
        if (employeeToUpdate != null) {
            System.out.println("What do you want to update?");
            System.out.println("1. Full Name");
            System.out.println("2. Age");
            System.out.println("3. Date of Birth");
            System.out.println("4. Salary");
            System.out.println("5. Department");
            System.out.print("Enter your choice: ");

            int updateChoice = scanner.nextInt();
            scanner.nextLine(); // Consume newline character

            switch (updateChoice) {
                case 1:
                    System.out.println("Enter new full name: ");
                    String newFullName = scanner.nextLine();
                    employeeToUpdate.setFullName(newFullName);
                    break;
                case 2:
                    System.out.println("Enter new age: ");
                    int newAge = scanner.nextInt();
                    employeeToUpdate.setAge(newAge);
                    break;
                case 3:
                    System.out.println("Enter new date of birth: ");
                    String newDOB = scanner.nextLine();
                    employeeToUpdate.setDateOfBirth(newDOB);
                    break;
                case 4:
                    System.out.println("Enter new salary: ");
                    double newSalary = scanner.nextDouble();
                    employeeToUpdate.setSalary(newSalary);
                    break;
                case 5:
                    System.out.println("Enter new department (DEVELOPMENT, TESTING, HR, ACCOUNTS): ");
                    String newDeptInput = scanner.nextLine().toUpperCase();
                    Department newDept = Department.valueOf(newDeptInput);
                    employeeToUpdate.setDepartment(newDept);
                    break;
                default:
                    System.out.println("Invalid choice for update.");
                    return;
            }
            employees.put(name, employeeToUpdate);
            System.out.println("Employee record updated successfully.");
        } else {
            System.out.println("Employee not found. Record update failed.");
        }
    }

    //Average salary of all employees
    public void calculateAverageSalary() {
        if (employees.isEmpty()) {
            System.out.println("No employees in the company.");
            return;
        }

        double totalSalary = 0;
        for (Employee employee : employees.values()) {
            totalSalary += employee.getSalary();
        }

        double averageSalary = totalSalary / employees.size();
        System.out.println("Average Salary of all employees: " + averageSalary);
    }

    //Average salary of employees by department
    public void calculateAverageSalaryByDepartment(Department department) {
        if (employees.isEmpty()) {
            System.out.println("No employees in the company.");
            return;
        }

        int count = 0;
        double totalSalary = 0;
        for (Employee employee : employees.values()) {
            if (employee.getDepartment() == department) {
                totalSalary += employee.getSalary();
                count++;
            }
        }

        if (count == 0) {
            System.out.println("No employees found in the specified department.");
            return;
        }

        double averageSalary = totalSalary / count;
        System.out.println("Average Salary in the " + department + " department: " + averageSalary);
    }

    // Method to delete an employee
    public void deleteEmployee(String name) {
        if (employees.containsKey(name)) {
            employees.remove(name);
            System.out.println("Employee deleted successfully.");
        } else {
            System.out.println("Employee not found. Deletion failed.");
        }
    }

    public void run() {
        boolean running = true;

        while (running) {
            System.out.println("Employee Management System");
            System.out.println("1. Show all Employees");
            System.out.println("2. Filter Employees");
            System.out.println("3. Search for an Employee");
            System.out.println("4. Update an Employee's Record");
            System.out.println("5. Average Salary of All Employees");
            System.out.println("6. Average Salary of Employees by Department");
            System.out.println("7. Delete an Employee");
            System.out.println("8. Exit");
            System.out.print("Enter your choice: ");

            int choice;
            try {
                choice = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Invalid input. Please enter a number.");
                continue;
            }

            switch (choice) {
                case 1:
                    //Display all employees
                    showAllEmployees();
                    break;
                case 2:
                    //Filter an employee
                    filterEmployees();
                    break;
                case 3:
                    // Search an employee
                    searchEmployee();
                    break;
                case 4:
                    // Update an employee
                    System.out.println("Enter employee name to update: ");
                    String employeeToUpdate = scanner.nextLine();
                    updateEmployee(employeeToUpdate);
                    break;
                case 5:
                    calculateAverageSalary();
                    break;
                case 6:
                    // Get user input for department
                    System.out.println("Enter department name (DEVELOPMENT, TESTING, HR, ACCOUNTS): ");
                    String departmentInput = scanner.nextLine().toUpperCase();
                    Department selectedDept = Department.valueOf(departmentInput);
                    calculateAverageSalaryByDepartment(selectedDept);
                    break;
                case 7:
                    // Delete an employee
                    System.out.println("Enter employee name to delete: ");
                    String employeeToDelete = scanner.nextLine();
                    deleteEmployee(employeeToDelete);
                    break;
                case 8:
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice. Please enter a valid option.");
            }
        }
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeManagement employeeManagement = new EmployeeManagement();
        employeeManagement.employees.put("Suresh", new Employee("Suresh", 30, "1993-05-15", 500000, Department.HR));
        employeeManagement.employees.put("Ramesh", new Employee("Ramesh", 25, "1998-10-20", 600000, Department.ACCOUNTS));
        employeeManagement.employees.put("Dinesh", new Employee("Dinesh", 23, "2000-08-30", 300000, Department.HR));
        employeeManagement.employees.put("Rajesh", new Employee("Rajesh", 35, "1988-06-18", 800000, Department.DEVELOPMENT));
        employeeManagement.employees.put("Mukesh", new Employee("Mukesh", 27, "1996-11-23", 650000, Department.ACCOUNTS));
        employeeManagement.employees.put("Kamesh", new Employee("Kamesh", 32, "1991-03-12", 750000, Department.TESTING));
        employeeManagement.employees.put("Nithesh", new Employee("Nithesh", 45, "1978-01-26", 950000, Department.DEVELOPMENT));
        employeeManagement.employees.put("Satheesh", new Employee("Satheesh", 39, "1984-09-05", 800000, Department.TESTING));
        employeeManagement.employees.put("Vignesh", new Employee("Vignesh", 29, "1994-02-10", 700000, Department.DEVELOPMENT));
        employeeManagement.employees.put("Mahesh", new Employee("Mahesh", 48, "1975-04-02", 1100000, Department.DEVELOPMENT));
        employeeManagement.run();
    }
}
