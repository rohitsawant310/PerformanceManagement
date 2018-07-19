package com.tools.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.tools.beans.UserDetails;
import com.tools.service.UsersService;

@Controller
@RequestMapping("/")
public class UserDetailsController {

	@Autowired
	UsersService userService;

	@RequestMapping(value = { "/", "/home" }, method = RequestMethod.GET)
	public String homePage(ModelMap model) {
		model.addAttribute("greeting", "Hi, Welcome to mysite");
		return "welcome";
	}

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String adminPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "admin";
	}

	@RequestMapping(value = "/db", method = RequestMethod.GET)
	public String dbaPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "dba";
	}

	@RequestMapping(value = "/Access_Denied", method = RequestMethod.GET)
	public String accessDeniedPage(ModelMap model) {
		model.addAttribute("user", getPrincipal());
		return "accessDenied";
	}

	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String loginPage() {
		return "login";
	}

	@RequestMapping(value = "/logout", method = RequestMethod.GET)
	public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (auth != null) {
			new SecurityContextLogoutHandler().logout(request, response, auth);
		}
		return "redirect:/login?logout";
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.GET)
	public String newRegistration(ModelMap model) {
		UserDetails user = new UserDetails();
		model.addAttribute("user", user);
		return "newuser";
	}

	@RequestMapping(value = "/newUser", method = RequestMethod.POST)
	public String saveRegistration(UserDetails userDetails, BindingResult result, ModelMap model) {

		if (result.hasErrors()) {
			System.out.println("There are errors");
			return "newuser";
		}
		userService.addUserDetails(userDetails);

		System.out.println("userDetail->" + userDetails);

		model.addAttribute("success", "User " + userDetails.getUserId() + " has been registered successfully");
		return "registrationsuccess";
	}

	private String getPrincipal() {
		String userName = null;
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

		if (principal instanceof UserDetails) {
			userName = ((UserDetails) principal).getUserId();
		} else {
			userName = principal.toString();
		}
		return userName;
	}
/*
	@ModelAttribute("roles")
	public List<UserProfile> initializeProfiles() {
		return userProfileService.findAll();
	}*/

	/*
	 * @Autowired private UserDetailsService userDetailsService;
	 * 
	 * @RequestMapping(value = "user/{id}", method = RequestMethod.GET) public
	 * String getUserDetails(@PathVariable int id, ModelMap userModel) {
	 * userModel.addAttribute("userDetails", userDetailsService.getUserDetails(id));
	 * return "user"; }
	 * 
	 * @RequestMapping(value = "users", method = RequestMethod.GET) public String
	 * getUsersDetails(ModelMap userModel) { userModel.addAttribute("userDetails",
	 * userDetailsService.getAllUserDetails()); return "users"; }
	 * 
	 * @RequestMapping(value = "addUser") public String addPage() { return "add"; }
	 * 
	 * @RequestMapping(value = "add/user", method = RequestMethod.POST) public
	 * String addUser(@RequestParam(value = "fname", required = true) String fname,
	 * 
	 * @RequestParam(value = "lname", required = true) String lname,
	 * 
	 * @RequestParam(value = "email", required = true) String email,
	 * 
	 * @RequestParam(value = "dob", required = true) String dob, ModelMap userModel)
	 * { UserDetails userDetails = new UserDetails();
	 * userDetails.setFirstName(fname); userDetails.setLastName(lname);
	 * userDetails.setEmail(email); userDetails.setDob(dob); int resp =
	 * userDetailsService.addUserDetails(userDetails); if (resp > 0) {
	 * userModel.addAttribute("msg", "User with id : " + resp +
	 * " added successfully."); userModel.addAttribute("userDetails",
	 * userDetailsService.getAllUserDetails()); return "users"; } else {
	 * userModel.addAttribute("msg", "User addition failed."); return "add"; } }
	 * 
	 * @RequestMapping(value = "delete/user/{id}", method = RequestMethod.GET)
	 * public String deleteUser(@PathVariable("id") int id, ModelMap userModel) {
	 * int resp = userDetailsService.deleteUserDetails(id);
	 * userModel.addAttribute("userDetails",
	 * userDetailsService.getAllUserDetails()); if (resp > 0) {
	 * userModel.addAttribute("msg", "User with id : " + id +
	 * " deleted successfully."); } else { userModel.addAttribute("msg",
	 * "User with id : " + id + " deletion failed."); } return "users"; }
	 * 
	 * @RequestMapping(value = "update/user/{id}", method = RequestMethod.GET)
	 * public String updatePage(@PathVariable("id") int id, ModelMap userModel) {
	 * userModel.addAttribute("id", id); userModel.addAttribute("userDetails",
	 * userDetailsService.getUserDetails(id)); return "update"; }
	 * 
	 * @RequestMapping(value = "update/user", method = RequestMethod.POST) public
	 * String updateUser(@RequestParam int id, @RequestParam(value = "fname",
	 * required = true) String fname,
	 * 
	 * @RequestParam(value = "lname", required = true) String
	 * lname, @RequestParam("email") String email,
	 * 
	 * @RequestParam("dob") String dob, ModelMap userModel) { UserDetails
	 * userDetails = new UserDetails(); userDetails.setId(id);
	 * userDetails.setFirstName(fname); userDetails.setLastName(lname);
	 * userDetails.setEmail(email); userDetails.setDob(dob); int resp =
	 * userDetailsService.updateUserDetails(userDetails);
	 * userModel.addAttribute("id", id); if (resp > 0) {
	 * userModel.addAttribute("msg", "User with id : " + id +
	 * " updated successfully."); userModel.addAttribute("userDetails",
	 * userDetailsService.getAllUserDetails()); return "users"; } else {
	 * userModel.addAttribute("msg", "User with id : " + id + " updation failed.");
	 * userModel.addAttribute("userDetails", userDetailsService.getUserDetails(id));
	 * return "update"; } }
	 */

}
