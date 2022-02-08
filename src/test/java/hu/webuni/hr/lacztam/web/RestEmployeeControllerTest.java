package hu.webuni.hr.lacztam.web;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.web.reactive.server.WebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient.ResponseSpec;

import hu.webuni.hr.lacztam.dto.EmployeeDto;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class RestEmployeeControllerTest {
	
	private static final String BASE_URI="/api/employees";
	
	@Autowired
	WebTestClient webTestClient;
	
	@Test
	void testName() throws Exception {
		EmployeeDto e1 = new EmployeeDto(5L, "Employee Name", "SomeTitle", 1500, LocalDateTime.of(2000, 10, 10, 0, 0));
		saveEmployee(e1);
		
		EmployeeDto e2 = new EmployeeDto(2L, "SomeName", "SomeTitle", 2000, LocalDateTime.of(2000, 10, 10, 0, 0));
		modifyEmployeeDto(1L, e2);
	}
	
	@Test
	void nullTitle() throws Exception{
		EmployeeDto e4 = new EmployeeDto(4L, "Employee Name", null , 2000, LocalDateTime.now());
		saveEmployee(e4)
			.expectStatus()
			.isBadRequest();		
	}
	@Test
	void emptyTitle() throws Exception{
		EmployeeDto e5 = new EmployeeDto(5L, "Employee Name", "" , 2000, LocalDateTime.now());
		saveEmployee(e5)
			.expectStatus()
			.isBadRequest();	
	}
	@Test
	void negativeSalary() throws Exception{
		EmployeeDto e6 = new EmployeeDto(6L, "Employee Name", "Some Title" , -1000, LocalDateTime.now());
		saveEmployee(e6)
			.expectStatus()
			.isBadRequest();	
	}
	@Test
	void futureStartDate() throws Exception{
		EmployeeDto e7 = new EmployeeDto(7L, "Employee Name", "Some Title" , -1000, LocalDateTime.of(2100, 10, 10, 0, 0));
		saveEmployee(e7)
			.expectStatus()
			.isBadRequest();	
	}
	@Test
	void nullName() throws Exception{
		EmployeeDto e3 = new EmployeeDto(3L, null, "SomeTitle", 2000, LocalDateTime.now());
		saveEmployee(e3)
			.expectStatus()
			.isBadRequest();	
	}
	@Test
	void emptyName() throws Exception{
		EmployeeDto e3 = new EmployeeDto(3L, "", "SomeTitle", 2000, LocalDateTime.now());
		saveEmployee(e3)
			.expectStatus()
			.isBadRequest();	
	}
	
	@Test
	void testThatNewValidEmployeeCanBeSaved() throws Exception{
		List<EmployeeDto> employeesBefore = getAllEmployees();
		
		EmployeeDto newEmployee 
				= new EmployeeDto(0L, "ABC", "student", 200000, LocalDateTime.of(2019, 01, 01, 01, 8, 0, 0));
		
		saveEmployee(newEmployee)
			.expectStatus()
			.isOk();
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter.size()).isEqualTo(employeesBefore.size() + 1);
		assertThat(employeesAfter.get(employeesAfter.size() - 1))
			.usingRecursiveComparison()
			.ignoringFields("id")
			.isEqualTo(employeesAfter);
	}
	
	@Test
	void testThatNewInvalidEmployeeCanBeSaved() throws Exception{
		List<EmployeeDto> employeesBefore = getAllEmployees();
		
		EmployeeDto newEmployee = newInvalidEmployee();
		
		saveEmployee(newEmployee)
			.expectStatus()
			.isBadRequest();
		
		List<EmployeeDto> employeesAfter = getAllEmployees();
		
		assertThat(employeesAfter).hasSameSizeAs(employeesBefore);
	}
	
	private EmployeeDto newInvalidEmployee() {
		return new EmployeeDto(0L, "", "student", 200000, LocalDateTime.of(2019, 01,01,8,0,0));
	}
	
	private ResponseSpec saveEmployee(EmployeeDto newEmployee) throws Exception {
		return webTestClient
			.post()
			.uri(BASE_URI)
			.bodyValue(newEmployee)
			.exchange();
	}
	
	private void modifyEmployeeDto(long id, EmployeeDto modifyEmployee) {
		String path = BASE_URI + "/" + modifyEmployee.getId();
		webTestClient
			.put()
			.uri(path)
			.bodyValue(modifyEmployee)
			.exchange();
	}
	
	private List<EmployeeDto> getAllEmployees() {
		List<EmployeeDto> responseList = webTestClient
			.get()
			.uri(BASE_URI)
			.exchange()
			.expectStatus()
			.isOk()
			.expectBodyList(EmployeeDto.class)
			.returnResult()
			.getResponseBody();
		
		Collections.sort(responseList, (e1, e2) -> Long.compare(e1.getId(), e2.getId()));
		return responseList;
	}
}
