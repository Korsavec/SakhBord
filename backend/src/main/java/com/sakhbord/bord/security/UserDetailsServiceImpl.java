package com.sakhbord.bord.security;

import com.sakhbord.bord.models.admin.Admin;
import com.sakhbord.bord.models.user.User;
import com.sakhbord.bord.repository.service.ServiceJpa;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static com.sakhbord.bord.security.HttpPath.*;

@Transactional
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

  private final
  ServiceJpa serviceJpa;

  public final
  HttpServletRequest request;

  public UserDetailsServiceImpl(ServiceJpa serviceJpa, HttpServletRequest request) {
    this.serviceJpa = serviceJpa;
    this.request = request;
  }

  @Override
  public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {



    if (request.getRequestURI().equals(LOGIN_USER)
            || request.getRequestURI().equals(AUTH_ADD_ANNOUNCEMENT)) {

      User user = serviceJpa.findUserByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(user);

    } else if (request.getRequestURI().equals(LOGIN_ADMIN) || request.getRequestURI().equals(AUTH_ADMIN)) {
      Admin admin = serviceJpa.findAdminByEmail(email).orElseThrow(() -> new UsernameNotFoundException("no"));
      return UserDetailsImpl.build(admin);

    } else {

      return UserDetailsImpl.buildEmpty();

    }




  }


}
