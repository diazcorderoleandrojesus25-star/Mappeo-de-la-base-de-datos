package com.Jobxpress.Jobxpress.Controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RedirectController {

    @GetMapping("/redirect")
    public String redirectByRole(Authentication auth) {

        String role = auth.getAuthorities().iterator().next().getAuthority();

        switch (role) {
            case "ROLE_ADMIN":
                return "redirect:/admin/home";
            case "ROLE_PRESTADOR":
                return "redirect:/prestador/home";
            default:
                return "redirect:/cliente/home";
        }
    }
}
