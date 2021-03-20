package mapping.lab.runner;

import mapping.lab.dtos.EmployeeDto;
import mapping.lab.dtos.ManagerDto;
import mapping.lab.entities.Address;
import mapping.lab.entities.Employee;
import mapping.lab.repositories.AddressRepository;
import mapping.lab.repositories.EmployeeRepository;
import org.modelmapper.Converter;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.math.BigDecimal;
import java.time.LocalDate;

@Configuration
public class Runner {

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private AddressRepository addressRepository;

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String firstName = "Ivan";
            String lastName = "Ivan";
            BigDecimal price = new BigDecimal("5.35");
            LocalDate localDate = LocalDate.of(1999, 5, 23);

            Address address = new Address("Haskovo", "ulica", 500);

            Employee employee = new Employee(
                    "Dimitar",
                    "Ivanov",
                    BigDecimal.valueOf(50),
                    LocalDate.of(1843, 3, 2),
                    address);

            Employee employee2 = new Employee(
                    "Kiro",
                    "Kirev",
                    BigDecimal.valueOf(500),
                    LocalDate.of(1849, 3, 2),
                    address);

            Employee manager = new Employee(
                    "Ivan",
                    "Dimitrov",
                    BigDecimal.valueOf(550),
                    LocalDate.of(1843, 3, 2),
                    address);

            addressRepository.save(address);
            manager.getEmployeesInChargeOf().add(employee);
            manager.getEmployeesInChargeOf().add(employee2);
            employee.setManager(manager);
            employee2.setManager(manager);

            //save all the manager's employees as well
            //through cascade all
            employeeRepository.save(manager);

            manager = employeeRepository.findById(1L).get();
            employee = employeeRepository.findById(2L).get();

            ModelMapper mapper = new ModelMapper();
            EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);
            ManagerDto managerDto = mapper.map(manager, ManagerDto.class);

            /*
            TypeMap<Employee, ManagerDto> typeMap = mapper.createTypeMap(Employee.class, ManagerDto.class);
            typeMap.addMapping(
                    src -> src.getManager().getFirstName(),
                    ManagerDto::setFirstName
            );

            typeMap.addMapping(
                    src -> src.getManager().getLastName(),
                    ManagerDto::setLastName
            );

            typeMap.addMapping(
                    src -> src.getManager().getEmployeesInChargeOf(),
                    ManagerDto::setEmployeesInChargeOf
            );
      */

            System.out.println(employeeDto);
            System.out.println(managerDto);

            employeeRepository
                    .getEmployeesByBirthdayBeforeOrderBySalaryDesc(LocalDate.of(1990, 1, 1))
                    .stream()
                    .map(e -> mapper.map(e, EmployeeDto.class))
                    .forEach(System.out::println);
        };
    }

    private EmployeeDto convertToEmployeeDto(Employee employee) {
        return null;
    }
}
