package com.mintic.Front.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import com.mintic.Front.Client.IClientStore;
import com.mintic.Front.Model.LoginDTO;

@Controller
public class AppController{
	
	@Autowired
	IClientStore clienteTienda;
	
	@GetMapping("/")
	public String index() {
		return "index";
	}
	
	@GetMapping("/menu")
	public String menu() {
		return "menu";
	}
	
	@GetMapping("/productos")
	public String productos() {
		return "productos";
	}
	
	@PostMapping("/login")
	public String login(Model model, LoginDTO loginDTO) {
		int validLogin = clienteTienda.login(loginDTO);
		
		if(validLogin == 1) {
			return "menu";
		}else {
			model.addAttribute("error", "Usuario o contraseña Inválidos");
			return "index";
		}
	}
	
}
