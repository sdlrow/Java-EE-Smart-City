package Nurbol.Security;

import Nurbol.Entities.User;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

import java.util.ArrayList;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import javax.ejb.Stateless;


@Stateless

public class JWTService {

    private List<String> validJWTTokens = new ArrayList();
    private String secret;
    private int JWT_TOKEN_VALIDITY = 100000;
    public String generateJWTToken(User user) {

        secret = "secret";
        String encodedString = Base64.getEncoder().encodeToString(secret.getBytes());
        String token = Jwts.builder()
                .claim("login", user.getLogin())
                .claim("password", user.getPassword())
                .claim("ROLE", user.getRole())
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY*1000)).signWith(SignatureAlgorithm.HS512, encodedString ).compact();
        this.validJWTTokens.add(token);
        return token;
    }

    public String valid(String token) {
        System.out.println(token);
        System.out.println(validJWTTokens);
        if (!this.validJWTTokens.contains(token)) {
//            throw new RuntimeException("Token is not valid anymore");
            return "not valid";
        }
        Base64.Decoder decoder = Base64.getDecoder();
//      JwtParser signed = Jwts.parser().setSigningKey(secret);
        String[] chunks = token.split("\\.");
        String header = new String(decoder.decode(chunks[0]));
        String payload = new String(decoder.decode(chunks[1]));

        return payload;
//        String data = signed.parseClaimsJws(token).getBody().getSubject();
    }

    public void removeToken(String token) {
        this.validJWTTokens.remove(token);
    }
}
