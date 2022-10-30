package com.backbase.movies.controller;

import com.backbase.movies.config.jwt.JwtTokenProvider;
import com.backbase.movies.config.jwt.JwtTokenResponse;
import com.backbase.movies.dto.request.WebUserDto;
import com.backbase.movies.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;

import javax.naming.AuthenticationException;
import javax.validation.Valid;

@RestController
@RequestMapping(Constants.LOGIN)
@CrossOrigin
public class LoginController {
    @Autowired
    private AuthenticationManager authMngr;
    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @PostMapping
    public ResponseEntity<JwtTokenResponse> signIn(@RequestBody @Valid WebUserDto webUser) throws AuthenticationException {
        try {
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                    webUser.getUsername(), webUser.getPassword());
            authMngr.authenticate(authToken);
            return ResponseEntity.ok(new JwtTokenResponse(jwtTokenProvider.createToken(webUser.getUsername())));
        } catch (DisabledException e) {
            throw new AuthenticationException("USER_DISABLED " + e.getMessage());
        } catch (BadCredentialsException e) {
            throw new AuthenticationException("INVALID_CREDENTIALS " + e.getMessage());
        }
    }

}