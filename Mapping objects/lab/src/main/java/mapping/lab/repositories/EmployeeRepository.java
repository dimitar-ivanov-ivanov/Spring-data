package mapping.lab.repositories;

import java.util.*;
import mapping.lab.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

  List<Employee> getEmployeesByBirthdayBeforeOrderBySalaryDesc(LocalDate date);
}
