package mapping.lab.dtos;

import mapping.lab.entities.Employee;

import javax.persistence.Column;
import java.util.HashSet;
import java.util.Set;

public class ManagerDto {

    private String firstName;

    private String lastName;

    private Set<EmployeeDto> employeesInChargeOf;

    public ManagerDto() {
        this.employeesInChargeOf = new HashSet<>();
    }

    public ManagerDto(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.employeesInChargeOf = new HashSet<>();
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Set<EmployeeDto> getEmployeesInChargeOf() {
        return employeesInChargeOf;
    }

    public void setEmployeesInChargeOf(Set<EmployeeDto> employeesInChargeOf) {
        this.employeesInChargeOf = employeesInChargeOf;
    }

    @Override
    public String toString() {
        StringBuilder builder = new StringBuilder();
        employeesInChargeOf
                .forEach(employee -> builder.append(employee.toString() + "\n"));


        return "ManagerDto{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                employeesInChargeOf + '}';
    }
}
