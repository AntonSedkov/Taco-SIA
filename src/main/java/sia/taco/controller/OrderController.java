package sia.taco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;
import sia.taco.data.OrderRepository;
import sia.taco.data.UserRepository;
import sia.taco.model.TacoOrder;
import sia.taco.model.User;
import sia.taco.util.OrderProps;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {

    private OrderProps orderProps;
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public OrderController(UserRepository userRepository,
                           OrderRepository orderRepository,
                           OrderProps orderProps) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
        this.orderProps = orderProps;
    }

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "order_form";
    }

    @GetMapping
    public String ordersForUser(
            @AuthenticationPrincipal User user, Model model) {
        Pageable pageable = PageRequest.of(0, orderProps.getPageSize());
        model.addAttribute("orders",
                orderRepository.findByUserOrderByPlacedAtDesc(user, pageable));
        return "orderList";
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
        return "redirect:/";
    }

}
