package csci5308.group8.jobportal.student;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentController {
	@RequestMapping("/hello")
	public String sayHi()
	{
		return "Hi";
	}

}
