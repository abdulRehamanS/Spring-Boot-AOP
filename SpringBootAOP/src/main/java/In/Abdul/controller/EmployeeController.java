package In.Abdul.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import In.Abdul.entity.Employee;
import In.Abdul.repo.EmployeeRepo;
import In.Abdul.service.EmployeeService;

@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService service;

	@GetMapping("/")
	public String getMsg() {
		return "Api is working";

	}

	@PostMapping("/add")
	public Employee addEmployee(@RequestBody Employee employee) throws Exception {
		return service.addEmployee(employee);

	}

	@GetMapping("/getAll")
	public List<Employee> getAllEmployee() {
		return service.getAllEmployee();

	}

}
