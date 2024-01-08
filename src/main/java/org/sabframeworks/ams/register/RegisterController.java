package org.sabframeworks.ams.register;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService) {
        this.registerService = registerService;
    }

    @PostMapping
    public String register(@RequestBody RegisterReuest registerReuest){
        return registerService.register(registerReuest);
    }

    @GetMapping(path = "confirm")
    public String confirmToken(@RequestParam("token") String token){
        return registerService.confirmToken(token);
    }
}
