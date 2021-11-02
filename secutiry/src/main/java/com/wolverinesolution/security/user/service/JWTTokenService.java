package com.wolverinesolution.security.user.service;

import com.google.common.collect.ImmutableMap;
import io.jsonwebtoken.*;
import io.jsonwebtoken.impl.compression.GzipCompressionCodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.Date;
import java.util.Map;
import java.util.function.Supplier;

import static org.apache.commons.lang3.StringUtils.substringBeforeLast;

@Component
public class JWTTokenService implements TokenService, Clock {

    private static final String DOT = ".";
    private static final GzipCompressionCodec COMMPRESSION_CODEC = new GzipCompressionCodec();
    String issuer;
    int expirationInSec;
    int clockSkewSec;
    String secretKey;

    @Autowired
    public JWTTokenService(@Value("${jwt.issuer}") String issuer,
                           @Value("${jwt.expiration-sec}") int expirationInSec,
                           @Value("${jwt.clock-skew-sec}") int clockSkewSec,
                           @Value("${jwt.secret}") String secretKey) {
        this.issuer = issuer;
        this.expirationInSec = expirationInSec;
        this.clockSkewSec = clockSkewSec;
        this.secretKey = secretKey;
    }



    @Override
    public String permanent(Map<String, String> attributes) {
        return newToken(attributes,0);
    }

    @Override
    public String expiring(Map<String, String> attributes) {
        return newToken(attributes,expirationInSec);
    }

    @Override
    public Map<String, String> untrusted(String tokenName) {
        final JwtParser parser = getParser();

        final String noSignature = substringBeforeLast(tokenName,DOT)+DOT;
        return parseClaims(()->parser.parseClaimsJws(tokenName).getBody());
    }



    @Override
    public Map<String, String> verify(String token) {
        return parseClaims(()->getParser().parseClaimsJws(token).getBody());
    }

    @Override
    public Date now() {
        return null;
    }

    private String newToken(final Map<String,String> attributes, final int expirationInSec){
        final LocalDateTime currentTime = LocalDateTime.now();
        final Claims claims = Jwts
                .claims()
                .setIssuer(issuer)
                .setIssuedAt(Date.from(currentTime.toInstant(ZoneOffset.UTC)));
        if(expirationInSec>0){
            final LocalDateTime expiresAt = currentTime.plusSeconds(expirationInSec);
            claims.setExpiration(Date.from(expiresAt.toInstant(ZoneOffset.UTC)));
        }
        claims.putAll(attributes);;
        return Jwts
                .builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS256,secretKey)
                .compressWith(COMMPRESSION_CODEC)
                .compact();
    }

    private static Map<String,String> parseClaims(final Supplier<Claims> toClaims){
        try{
            final Claims claims  = toClaims.get();
            final ImmutableMap.Builder<String,String> builder = ImmutableMap.builder();
            claims.entrySet().stream().forEach(e->builder.put(e.getKey(),String.valueOf(e.getValue())));

            return builder.build();
        }catch (final IllegalArgumentException | JwtException e){
            return ImmutableMap.of();
        }
    }

    private JwtParser getParser() {
        final JwtParser parser = Jwts.parser()
                .requireIssuer(issuer)
                .setClock(this)
                .setAllowedClockSkewSeconds(clockSkewSec);
        return parser;
    }
}
