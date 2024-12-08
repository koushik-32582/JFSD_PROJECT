package com.jfsd.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jfsd.entity.Hotel;
import com.jfsd.repo.HotelRepository;

import java.util.List;

@Service
public class HotelService {

    @Autowired
    private HotelRepository repo;

    // Save a new hotel
    public void saveHotel(Hotel hotel) {
        repo.save(hotel);
    }

    // Retrieve all hotels
    public List<Hotel> listAll() {
        return repo.findAll();
    }

    // Retrieve a hotel by ID
    public Hotel findById(int id) {
        return repo.findById(id).orElse(null);
    }

    // Filter hotels based on city, state, or country
    public List<Hotel> filterHotels(String city, String state, String country) {
        return repo.findByCityOrStateOrCountry(city, state, country);
    }
}
