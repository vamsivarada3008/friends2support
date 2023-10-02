package com.example.friends2support;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class DonarController {
    @Autowired
    private final DonarRepository donarRepository;

    public DonarController(DonarRepository donarRepository) {
        this.donarRepository = donarRepository;
    }

    @GetMapping("/donars")
    public List<Donor> getDonars() {
        return donarRepository.findAll();
    }

    @PostMapping("/donars")
    public ResponseEntity<Object> createDonar(@RequestBody Donor donar) {
        Donor savedDonar = donarRepository.save(donar);


        return new ResponseEntity<>(savedDonar, HttpStatus.CREATED);
    }
    @GetMapping ("/donars/{id}")
    public ResponseEntity<Object> getDonarWithId(@PathVariable int id){
        Optional<Donor> optionalDonar = donarRepository.findById(id);
        if(optionalDonar.isPresent()){
            return new ResponseEntity<>(optionalDonar,HttpStatus.OK);
        }
        else{
            String errorMessage = "Donar not found with id: " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping("/donars/{id}")
    public ResponseEntity<Object> updateDonar(@PathVariable Integer id, @RequestBody Donor updatedDonar) {
        Optional<Donor> optionalDonar = donarRepository.findById(id);
        if (optionalDonar.isPresent()) {
            Donor existingDonar = optionalDonar.get();
            existingDonar.setName(updatedDonar.getName());

            Donor updatedDonarObj = donarRepository.save(existingDonar);
            return new ResponseEntity<>(updatedDonarObj, HttpStatus.OK);
        } else {
            String errorMessage = "Donar not found with id: " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/donars/{id}")
    public ResponseEntity<Object> deleteDonar(@PathVariable Integer id) {
        Optional<Donor> optionalDonar = donarRepository.findById(id);
        if (optionalDonar.isPresent()) {
            donarRepository.deleteById(id);
            return new ResponseEntity<>("Donar deleted successfully", HttpStatus.OK);
        } else {
            String errorMessage = "Donar not found with id: " + id;
            return new ResponseEntity<>(errorMessage, HttpStatus.NOT_FOUND);
        }
    }


    @GetMapping("/bloodGroup/{bloodGroup}")
    public List<Donor> getDonorsByBloodGroup(@PathVariable String bloodGroup) {
        return donarRepository.findByBloodGroup(bloodGroup);
    }

    @GetMapping("/country/{country}")
    public List<Donor> getDonorsByCountry(@PathVariable String country) {
        return donarRepository.findByCountry(country);
    }

    @GetMapping("/district/{district}")
    public List<Donor> getDonorsByDistrict(@PathVariable String district) {
        return donarRepository.findByDistrict(district);
    }

    @GetMapping("/state/{state}")
    public List<Donor> getDonorsByState(@PathVariable String state) {
        return donarRepository.findByState(state);
    }
}
