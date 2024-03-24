package com.resumetracker.ResumeTracker.controller;

import com.resumetracker.ResumeTracker.config.JwtUtil;
import com.resumetracker.ResumeTracker.dto.MyData;
import com.resumetracker.ResumeTracker.dto.RequestDto;
import com.resumetracker.ResumeTracker.dto.ResponseDto;
import com.resumetracker.ResumeTracker.repository.UserRepository;
import com.resumetracker.ResumeTracker.service.AuthenticationService;
import com.sun.tools.jconsole.JConsoleContext;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthenticationService authenticationService;
    private final UserRepository userRepository;
    private final JwtUtil jwtService;
    private final AuthenticationManager authenticationManager;
    private final PasswordEncoder passwordEncoder;

        @PostMapping("/register")
        public ResponseEntity<ResponseDto> register (@RequestBody RequestDto request ){
            try{
                    ResponseDto payload = authenticationService.register(request);
                    var user = userRepository.findByUsername(payload.getData().getUsername()).orElseThrow(() ->
                            new UsernameNotFoundException("User Not Found"));
                    String jwtToken = jwtService.generateToken(user);

                    HttpHeaders headers = new HttpHeaders();
                    headers.set("Authorization", "Bearer " + jwtToken);
                    return ResponseEntity.ok().headers(headers).body(payload);
            }catch(Exception e){
               System.out.println(e.getMessage());
               return  ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(),new MyData()));

            }
    }


    @PostMapping("/login")
    public ResponseEntity<ResponseDto> login(@RequestBody RequestDto request) {


      try {
          authenticationManager.authenticate(
                  new UsernamePasswordAuthenticationToken(
                          request.getEmail(),
                          request.getPassword()
                  )
          );
          var user = userRepository.findByEmail(request.getEmail()).orElseThrow(() ->
                  new UsernameNotFoundException("User Not Found"));
          

          String jwtToken = jwtService.generateToken(user);

          HttpHeaders headers = new HttpHeaders();
          headers.set("Authorization", "Bearer " + jwtToken);
          return ResponseEntity.ok().headers(headers).body(
                  ResponseDto.builder().
                          message("User logged in successfully")
                          .data(MyData.builder()
                                  .username(user.getUsername())
                                  .email(user.getEmail())
                                  .build()
                          ).
                          build()
          );
      }catch (Exception e){
          System.out.println(e.getMessage());
          return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDto(e.getMessage(),new MyData()));
      }
    }
}
