package In.Abdul.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import In.Abdul.entity.Employee;

public interface EmployeeRepo extends JpaRepository<Employee, Integer>{

}
