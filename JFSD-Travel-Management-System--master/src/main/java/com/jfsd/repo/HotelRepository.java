package com.jfsd.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.jfsd.entity.Hotel;
import java.util.List;

@Repository
public interface HotelRepository extends JpaRepository<Hotel, Integer> {

    // Custom query method to filter by city, state, or country
    List<Hotel> findByCityOrStateOrCountry(String city, String state, String country);
}
