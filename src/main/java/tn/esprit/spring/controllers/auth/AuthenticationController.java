package tn.esprit.spring.controllers.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tn.esprit.spring.dto.auth.AuthenticationRequest;
import tn.esprit.spring.dto.auth.AuthentificationResponse;
import tn.esprit.spring.entity.auth.EntendedUser;
import tn.esprit.spring.services.auth.ApplicationUserDetailsService;
import tn.esprit.spring.utils.JwtUtil;

import static tn.esprit.spring.utils.Constants.APP_ROOT;

@RestController
@RequestMapping(APP_ROOT + "/auth")
public class AuthenticationController {

    private ApplicationUserDetailsService applicationUserDetailsService;
    private AuthenticationManager authenticationManager;

    private JwtUtil jwtUtil;
    @Autowired
    public AuthenticationController(ApplicationUserDetailsService applicationUserDetailsService,
                                    AuthenticationManager authenticationManager,
                                    JwtUtil jwtUtil)
    {
        this.applicationUserDetailsService = applicationUserDetailsService;
        this.authenticationManager = authenticationManager;
        this.jwtUtil=jwtUtil;
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthentificationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getLogin(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails=applicationUserDetailsService.loadUserByUsername(request.getLogin());
        final String jwt=jwtUtil.generateToken((EntendedUser) userDetails);
        return ResponseEntity.ok(AuthentificationResponse.builder().accessToken(jwt).build());
    }
}
