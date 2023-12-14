package In.Abdul.service;

import In.Abdul.entity.Employee;
import In.Abdul.repo.EmployeeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {

    @Autowired
    private EmployeeRepo repo;

    public Employee addEmployee(Employee employee) throws Exception {

        if (employee.getName().length() > 5) throw new Exception("please name should be < 5 characters");

        return repo.save(employee);

    }

    public List<Employee> getAllEmployee() {
        return repo.findAll();

    }

}
