package sia.taco.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.taco.data.OrderRepository;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private OrderRepository orderRepository;

    public AdminController(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @PostMapping("/deleteOrders")
    public String deleteAllOrders() {
        orderRepository.deleteAll();
        return "redirect:/admin";
    }
}
