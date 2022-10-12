package com.portfolio.portfolioEMM.security.jwt;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import com.portfolio.portfolioEMM.security.entities.MainUser;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtProvider {

	private final static Logger LOGGER = LoggerFactory.getLogger(JwtProvider.class);

	@Value("${jwt.secret}")
	private String secret;
	@Value("${jwt.expiration}")
	private int expiration;

	public String generateToken(Authentication auth) {
		MainUser mainUser = (MainUser) auth.getPrincipal();
		return Jwts.builder().setSubject(mainUser.getUsername()).setIssuedAt(new Date())
				.setExpiration(new Date(new Date().getTime() + expiration * 2000))
				.signWith(SignatureAlgorithm.HS512, secret).compact();
	}

	public String getUserNameFromToken(String token) {
		return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody().getSubject();
	}

	public boolean validateToken(String token) {
		try {
			Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
			return true;
		} catch (MalformedJwtException e) {
			LOGGER.error("Error: Token wrong generation");
		} catch (UnsupportedJwtException e) {
			LOGGER.error("Error: Token not supported");
		} catch (ExpiredJwtException e) {
			LOGGER.error("Error: Token expired");
		} catch (SignatureException e) {
			LOGGER.error("Error: Firm invalid");
		} catch (IllegalArgumentException e) {
			LOGGER.error("Error: Token empty");
		}
		return false;
	}

}
