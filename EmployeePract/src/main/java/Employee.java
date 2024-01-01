public class Employee {
    private int id;
    private static int nextId = 1;
    private String name;
    private String surname;
    private double salary;

    public Employee(String name, String surname, double salary) throws FieldLengthLimitException, IncorrectSalaryException {
        setId();
        setName(name);
        setSurname(surname);
        setSalary(salary);
    }

    private void setId() {
        this.id = nextId++;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws FieldLengthLimitException {
        if (name.length() > 15) { // Максимальна довжина імені
            throw new FieldLengthLimitException("Name length exceeds the limit");
        }
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) throws FieldLengthLimitException {
        if (surname.length() > 30) { // Максимальна довжина прізвища
            throw new FieldLengthLimitException("Surname length exceeds the limit");
        }
        this.surname = surname;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) throws IncorrectSalaryException {
        if (salary < 0) {
            throw new IncorrectSalaryException("Salary cannot be negative");
        }
        this.salary = salary;
    }
}

class FieldLengthLimitException extends Exception {
    public FieldLengthLimitException(String message) {
        super(message);
    }
}

class IncorrectSalaryException extends Exception {
    public IncorrectSalaryException(String message) {
        super(message);
    }
}
