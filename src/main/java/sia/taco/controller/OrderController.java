package sia.taco.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import sia.taco.model.TacoOrder;

import javax.validation.Valid;

@Controller
@Slf4j
@RequestMapping("/orders")
public class OrderController {

    @GetMapping("/current")
    public String orderForm(Model model) {
        model.addAttribute("tacoOrder", new TacoOrder());
        return "order_form";
    }

    @PostMapping
    public String processOrder(@Valid TacoOrder order, Errors errors) {
        if (errors.hasErrors()) {
            return "order_form";
        }
        log.info("Order submitted " + order);
        return "redirect:/";
    }
}
