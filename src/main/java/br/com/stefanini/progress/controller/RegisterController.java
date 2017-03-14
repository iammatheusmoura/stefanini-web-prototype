package br.com.stefanini.progress.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import br.com.stefanini.progress.model.User;
import br.com.stefanini.progress.service.UserService;

@Controller
public class RegisterController {
	
	@Autowired
	private UserService userService;
	
	@RequestMapping(value = "/register", method=RequestMethod.GET)
	public ModelAndView register(){
		ModelAndView modelAndView = new ModelAndView();
		User user = new User();
		modelAndView.addObject("user", user);
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
//	@RequestMapping(value = "/register")
//	public ModelAndView selectTag(){
//		ModelAndView modelAndView = null;
//		
//		Map<String, String> profileMap = new HashMap<String, String>();
//		profileMap.put("1", "Desenvolvedor");
//		profileMap.put("2", "Gerente");
//		profileMap.put("3", "Diretor");
//		profileMap.put("4", "Admin");
//		
//		modelAndView = new ModelAndView("register", "dropDownItems", profileMap);
//		return modelAndView;
//		
////		modelAndView.addObject("profiles", profileMap);
////		modelAndView.addObject("user", new User());
//		
//	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public ModelAndView newUser(@Valid User user, BindingResult bindingResult){
		ModelAndView modelAndView = new ModelAndView();
		User cpfExists = userService.findByCpf(user.getCpf());
		
		if(cpfExists != null){
			bindingResult.rejectValue("cpf", "error.user", "O cliente com esse CPF já foi cadastrado");
		}
		
		if (bindingResult.hasErrors()){
			modelAndView.setViewName("register");
		} else {
			userService.saveUser(user);
			modelAndView.addObject("successMessage", "Cliente cadastrado com sucesso");
			modelAndView.addObject("user", new User());
			modelAndView.setViewName("login");
		}
		
		return modelAndView;
	}
}
