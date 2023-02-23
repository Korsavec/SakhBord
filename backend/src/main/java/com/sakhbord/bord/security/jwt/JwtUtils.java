package com.sakhbord.bord.security.jwt;

import com.sakhbord.bord.security.UserDetailsImpl;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;
import org.springframework.util.FileCopyUtils;

import java.io.IOException;
import java.security.KeyFactory;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.*;

import static com.sakhbord.bord.config.Constants.STATIC_EMAIL;

@Component
public class JwtUtils {

  @Value("${sakhshop.app.jwtExpirationMsOneHour}")
  private int jwtExpirationMsOneHour;

  @Value("${sakhshop.app.jwtExpirationMsEightHours}")
  private int jwtExpirationMsEightHours;


  private int rememberMe (boolean x) {

    if (x) {
      return jwtExpirationMsEightHours;
    } else {
      return jwtExpirationMsOneHour;
    }

  }

  //    Генерация jwt токена
  public String generateJwtToken(Authentication authentication, boolean rememberMeFor) {



    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roleList = new ArrayList<>();
    userPrincipal.getAuthorities().forEach(grantedAuthority -> roleList.add(grantedAuthority.getAuthority().replace("ROLE_", "")));

    try {
      return Jwts.builder()
              .setHeaderParam("typ","JWT")
              .claim("email", userPrincipal.getUsername())
              .claim("roles", roleList)
              .claim("isEnabled", userPrincipal.isEnabled())
              .claim("isAccountNonLocked", userPrincipal.isAccountNonLocked())
              .setIssuedAt(new Date())
              .setExpiration(new Date(new Date().getTime() + rememberMe(rememberMeFor)))
              .signWith(getPrivateKey(), SignatureAlgorithm.RS512)
              .compact();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new JwtException(e.toString());
    }

  }

  public String generateJwtTokenAdmin(Authentication authentication, boolean rememberMeFor) {

    UserDetailsImpl userPrincipal = (UserDetailsImpl) authentication.getPrincipal();

    List<String> roleList = new ArrayList<>();
    userPrincipal.getAuthorities().forEach(grantedAuthority -> roleList.add(grantedAuthority.getAuthority().replace("ROLE_", "")));

    try {
      return Jwts.builder()
              .setHeaderParam("typ","JWT")
              .claim("email", userPrincipal.getUsername())
              .claim("roles", roleList)
              .setIssuedAt(new Date())
              .setExpiration(new Date(new Date().getTime() + rememberMe(rememberMeFor)))
              .signWith(getPrivateKey(), SignatureAlgorithm.RS512)
              .compact();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new JwtException(e.toString());
    }

  }

  public String getUserEmailFromJwtToken(String jwtString) {

    try {
      return Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwtString).getBody().get(STATIC_EMAIL).toString();
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new JwtException(e.toString());
    }

  }

  public Jws<Claims> validateJwtToken(String jwtString) {

    try {
      return Jwts.parserBuilder().setSigningKey(getPublicKey()).build().parseClaimsJws(jwtString);
    } catch (NoSuchAlgorithmException | InvalidKeySpecException e) {
      throw new JwtException(e.toString());
    }

  }


  //  Конвертируем строковый ключ в PublicKey
  private PublicKey getPublicKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

    String key = getKeyContent("key/public.pem");

    X509EncodedKeySpec keySpec = new X509EncodedKeySpec(Base64.getDecoder().decode(key));

    KeyFactory kf = KeyFactory.getInstance("RSA");

    return kf.generatePublic(keySpec);
  }


  //  Конвертируем строковый ключ в PrivateKey
  private PrivateKey getPrivateKey() throws NoSuchAlgorithmException, InvalidKeySpecException {

    String key = getKeyContent("key/private.pem");

    PKCS8EncodedKeySpec keySpec = new PKCS8EncodedKeySpec(Base64.getDecoder().decode(key));

    KeyFactory kf = KeyFactory.getInstance("RSA");

    return kf.generatePrivate(keySpec);
  }


  public static String getKeyContent(String resourcePath) {
    Objects.requireNonNull(resourcePath);
    ClassPathResource resource = new ClassPathResource(resourcePath);
    try {
      byte[] bytes = FileCopyUtils.copyToByteArray(resource.getInputStream());
      return new String(bytes);
    } catch(IOException e) {
      throw new NullPointerException(e.toString());
    }
  }


}
