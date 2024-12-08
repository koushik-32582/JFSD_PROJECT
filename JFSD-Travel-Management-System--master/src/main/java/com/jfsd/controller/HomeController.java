package com.jfsd.controller;

import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jfsd.entity.User;
import com.jfsd.service.*;

@Controller
public class HomeController {

    @Autowired
    private UserService uservice;
    @Autowired
    private BookingService bservice;
    @Autowired
    private HttpSession session;
    @Autowired
    private HotelService hservice;
    @Autowired
    private FlightService fservice;
    @Autowired
    private PlaceService pservice;
    @Autowired
    private TourPackageService tservice;

    @GetMapping("/")
    public String homepage() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String validate(@RequestParam("userid") String userid,
                           @RequestParam("pwd") String pwd,
                           RedirectAttributes ra) {
        if (userid == null || pwd == null) {
            ra.addFlashAttribute("error", "Both username and password are required.");
            return "redirect:/login";
        }

        User user = uservice.ValidateLogin(userid, pwd);
        if (user == null) {
            ra.addFlashAttribute("error", "Invalid username or password");
            return "redirect:/login";
        } else {
            session.setAttribute("userid", user.getUserid());
            session.setAttribute("uname", user.getUname());
            session.setAttribute("role", user.getRole());
            return "redirect:/packages";
        }
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @PostMapping("/register")
    public String saveUser(@ModelAttribute("user") User user, RedirectAttributes ra) {
        if (user == null) {
            ra.addFlashAttribute("error", "Invalid user data.");
            return "redirect:/register";
        }

        uservice.saveUser(user);
        ra.addFlashAttribute("msg", "User registered successfully");
        return "redirect:/login";
    }

    @GetMapping("/home/hotels")
    public String hotels(Model model) {
        model.addAttribute("list", hservice.listAll());
        return "hotels";
    }

    @GetMapping("/book")
    public String bookings(@RequestParam(required = false) String tour,
                           Model model, RedirectAttributes ra) {
        if (session.getAttribute("userid") == null) {
            ra.addFlashAttribute("error", "Please log in first to book.");
            return "redirect:/login";
        }
        model.addAttribute("hotels", hservice.listAll());
        model.addAttribute("flights", fservice.listall());
        model.addAttribute("tours", tservice.listall());
        return "bookings";
    }

    @GetMapping("/payment")
    public String payments(@RequestParam("bid") int bid) {
        return "payment";
    }

    @GetMapping("/places")
    public String places(Model model) {
        model.addAttribute("list", pservice.listAll());
        return "places";
    }

    @GetMapping("/packages")
    public String packages(Model model) {
        model.addAttribute("list", tservice.listall());
        return "packages";
    }

    @GetMapping("/flights")
    public String flights(Model model) {
        model.addAttribute("list", fservice.listall());
        return "flights";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }

    @GetMapping("/profile")
    public String profile(Model model) {
        model.addAttribute("title", "Profile Page");
        return "profile";
    }
}
