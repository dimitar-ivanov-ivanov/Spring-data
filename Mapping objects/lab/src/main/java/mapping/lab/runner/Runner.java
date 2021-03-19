package mapping.lab.runner;

import mapping.lab.dtos.EmployeeDto;
import mapping.lab.entities.Employee;
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

    @Bean
    CommandLineRunner commandLineRunner() {
        return args -> {
            String firstName = "Ivan";
            String lastName = "Ivan";
            BigDecimal price = new BigDecimal("5.35");
            LocalDate localDate = LocalDate.of(1999, 5, 23);
            String address = "neko";

            Employee employee = new Employee(firstName, lastName, price, localDate, address);

            employeeRepository.save(employee);
            employee = employeeRepository.findById(1L).get();

            ModelMapper mapper = new ModelMapper();
            EmployeeDto employeeDto = mapper.map(employee, EmployeeDto.class);

            System.out.println(employeeDto);
        };
    }

    private EmployeeDto convertToEmployeeDto(Employee employee) {
        return null;
    }
}
