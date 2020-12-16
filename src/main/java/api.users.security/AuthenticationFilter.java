package com.photoapp.api.users.security;

import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.appsdeveloperblog.api.users.ui.model.LoginRequestModel;
import com.fasterxml.jackson.databind.ObjectMapper;

public class AuthenticationFilter extends UsernamePasswordAuthenticationFilter{

    private UsersService usersService;
    private Environment environment;

    public AuthenticationFilter(UsersService usersService, Environment environment, AuthenticationManager authenticationManager)
    {
        this.usersService = usersService;
        this.environment  = environment;
        super.setAuthenticationManager(authenticationManager);
    }


    @Override
    public Authentication attemptAuthentication( HttpServletRequest req,
                                                 HttpServletRequest res) throws AuthenticationException {
        try {
            LoginRequestModel creds = new ObjectMapper()
                .readValue(req.getInputStream(), LoginRequestModel.class);
            
            return getAuthenticationManager().authenticate(
                    new UsernamePasswordAuthenticationToken(
                        creds.getEmail(),
                        creds.getPassword(),
                        new ArrayList<>())
            );

        } catch (IOException e){
            throw new RuntimeException(e);
        }
    }
    @Override
    protected void successfullAuthentication(HttpServletRequest req,
                                             HttpServletResponse res,
                                             FilterChain chain,
                                             Authentication auth ) throws IOException, ServeltException {
        String userName =((User) aut.getPrincipal()).getUsername();
        UserDto userDetails = usersService.getUserDetailsByEmail(userName);

        String token = Jwts.builder()
                .setSubject(userDetails.getUserId())
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(environment.getProperty("token.expiration_time"))))
                .signWith(SignatureAlgorithm.HS512, environment.getProperty("token.secret") )
                .compact();
        
        res.addHeader("token", token);
        res.addHeader("userId", userDetails.getUserId());

    }
}