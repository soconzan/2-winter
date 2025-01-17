package com.example.hello;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/*
 * UI 반환
 */
@Controller
public class WebController {

//	Get : method
//	"/" : 경로
//	@GetMapping("/")
	public String home() {
		return "index";
		// 확장자 반환 X -> 프로젝트 생성시 지정한 view 어쩌구로 지정
		// thymeleaf 기본값 -> html
	}
	
	@GetMapping("/form")
	public String form() {
		return "form";
	}
	
	// form method=post -> @PostMapping
	@PostMapping("/processForm")
	public String processform(
			@RequestParam("id") int id,
			@RequestParam("name") String name,
			Model model // param 값을 넘겨주는 역할
			) {
		model.addAttribute("id", id);
		model.addAttribute("name", name);
		return "result";
	}

	/*
	 * Getter/Setter 설정시
	 */
//	@GetMapping("/hello")
//	@ResponseBody
//	public String hello() {
//
//		User user = new User();
//		user.getUserId();
//
//		return "hello";
//	}

	/*
	 * @AllArgsConstructor User 타입 사용 -> ResponseBody -> User 객체 반환
	 */
	/*
	 * @GetMapping("/hello")
	 * 
	 * @ResponseBody public User hello() { return new User(1, "ABC"); }
	 */

}
