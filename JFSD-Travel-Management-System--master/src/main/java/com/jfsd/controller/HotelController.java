package com.jfsd.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import com.jfsd.entity.Hotel;
import com.jfsd.service.HotelService;

import java.util.List;

@Controller
public class HotelController {

    @Autowired
    private HotelService hotelService;

    @GetMapping("/hotels")
    public String listHotels(Model model) {
        List<Hotel> hotels = hotelService.listAll();
        model.addAttribute("list", hotels);
        return "hotels";
    }

    @PostMapping("/addHotel")
    public String saveHotel(@ModelAttribute("hotel") Hotel hotel, RedirectAttributes ra) {
        if (hotel == null || hotel.getHname() == null) {
            ra.addFlashAttribute("error", "Hotel data is invalid.");
            return "redirect:/hotels";
        }
        hotelService.saveHotel(hotel);
        ra.addFlashAttribute("msg", "Hotel added successfully!");
        return "redirect:/hotels";
    }

    @GetMapping("/filterHotels")
    public String filterHotels(@RequestParam(required = false) String city,
                               @RequestParam(required = false) String state,
                               @RequestParam(required = false) String country,
                               Model model) {
        List<Hotel> filteredHotels = hotelService.filterHotels(city, state, country);
        if (filteredHotels.isEmpty()) {
            model.addAttribute("error", "No hotels found for the given filters.");
        }
        model.addAttribute("list", filteredHotels);
        return "hotels";
    }
}
