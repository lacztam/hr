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

/*		Test cases for creating new employees 	*/
	
//		OK
		EmployeeDto e1 = new EmployeeDto(5L, "Employee Name", "SomeTitle", 1500, LocalDateTime.of(2000, 10, 10, 0, 0));
		saveEmployee(e1);
		
//		name = null
//		EmployeeDto e2 = new EmployeeDto(2L, null, "SomeTitle", 1500, LocalDateTime.now());
//		createNewEmployeeDto(e2);
		
//		name = ""
//		EmployeeDto e3 = new EmployeeDto(3L, "", "SomeTitle", 2000, LocalDateTime.now());
//		createNewEmployeeDto(e3);
	
//		title = null
//		EmployeeDto e4 = new EmployeeDto(4L, "Employee Name", null , 2000, LocalDateTime.now());
//		createNewEmployeeDto(e4);
		
//		title = ""
//		EmployeeDto e5 = new EmployeeDto(5L, "Employee Name", "" , 2000, LocalDateTime.now());
//		createNewEmployeeDto(e5);
		
//		salary = -1000
//		EmployeeDto e6 = new EmployeeDto(6L, "Employee Name", "Some Title" , -1000, LocalDateTime.now());
//		createNewEmployeeDto(e6);	
		
//		start date is in future
//		EmployeeDto e7 = new EmployeeDto(7L, "Employee Name", "Some Title" , -1000, LocalDateTime.of(2100, 10, 10, 0, 0));
//		createNewEmployeeDto(e7);			
	
	
/*		Test cases for modifing employees 	*/
	
//		OK
		EmployeeDto e2 = new EmployeeDto(2L, "SomeName", "SomeTitle", 2000, LocalDateTime.of(2000, 10, 10, 0, 0));
		modifyEmployeeDto(1L, e2);
		
//		name = null
//		EmployeeDto e3 = new EmployeeDto(3L, null, "SomeTitle", 2000, LocalDateTime.now());
//		modifyEmployeeDto(1L, e3);
		
//		name = ""
//		EmployeeDto e3 = new EmployeeDto(3L, "", "SomeTitle", 2000, LocalDateTime.now());
//		modifyEmployeeDto(1L, e3);
	
//		title = null
//		EmployeeDto e4 = new EmployeeDto(4L, "Employee Name", null , 2000, LocalDateTime.now());
//		modifyEmployeeDto(1L, e4);
		
//		title = ""
//		EmployeeDto e5 = new EmployeeDto(5L, "Employee Name", "" , 2000, LocalDateTime.now());
//		modifyEmployeeDto(1L, e5);
		
//		salary = -1000
//		EmployeeDto e6 = new EmployeeDto(6L, "Employee Name", "Some Title" , -1000, LocalDateTime.now());
//		modifyEmployeeDto(1L, e6);	
		
//		start date is in future
//		EmployeeDto e7 = new EmployeeDto(7L, "Employee Name", "Some Title" , -1000, LocalDateTime.of(2100, 10, 10, 0, 0));
//		modifyEmployeeDto(1L, e7);	
				
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
