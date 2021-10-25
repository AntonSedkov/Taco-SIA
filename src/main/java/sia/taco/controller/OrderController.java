package sia.taco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;
import sia.taco.data.OrderRepository;
import sia.taco.data.UserRepository;
import sia.taco.model.TacoOrder;
import sia.taco.model.User;

import javax.validation.Valid;
import java.security.Principal;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(UserRepository userRepository,
                           OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "order_form";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors,
                               SessionStatus sessionStatus,
                               @AuthenticationPrincipal User user) {
        if (errors.hasErrors()) {
            return "order_form";
        }

        order.setUser(user);
        orderRepository.save(order);
        sessionStatus.setComplete();

        log.info("Order submitted " + order);
        return "redirect:/";
    }
}
