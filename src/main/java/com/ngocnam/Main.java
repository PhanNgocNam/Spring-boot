package com.ngocnam;

import com.ngocnam.entitis.Customer;
import com.ngocnam.repository.CustomerRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@SpringBootApplication
@RestController
@RequestMapping("api/v1/customers")
public class Main {
    public Main(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public static void main(String[] args) {
        SpringApplication.run(Main.class, args);
    }

    private final CustomerRepository customerRepository;
    @GetMapping
    public List<Customer> getCustomers() {
        return customerRepository.findAll();
    }

    record NewCustomerRequest(
            String name,
            String email,
            Integer age
    ) {}

    @PostMapping
    public void addCustomer(@RequestBody NewCustomerRequest rq) {
        Customer customer = new Customer();
        customer.setName(rq.name());
        customer.setAge(rq.age());
        customer.setEmail(rq.email());
        customerRepository.save(customer);
    }

//    @GetMapping("/")
//    public GreetRespone greet() {
//        return new GreetRespone("Hello Spring Boot!");
//    }
//
//    @GetMapping("/student")
//    public Student respone() {
//        return new Student("Phan Ngoc Nam", "SE", 22, new GirlFriend("Trang Le", 24), List.of("Football", "Coding", "Swimming", "Bamboo Flute"));
//    }
//
//    record Student(String name, String major, int age, GirlFriend girlFriend, List<String> hobbies) {}
//
//    record GirlFriend(String name,int age) {}
//
//    record GreetRespone(String msg) {}
}
