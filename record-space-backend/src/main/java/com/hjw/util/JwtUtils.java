package com.hjw.util;

import com.hjw.shiro.JwtToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.Objects;

/**
 * @author hjw
 * @date 2021年10月11日 15:19
 * @description jwt工具类
 */

@Slf4j
@Data
@Component
@ConfigurationProperties(prefix = "custom.jwt")
public class JwtUtils {

    private String secret;
    private long expire;
    private String header;


    /**
     * 生成jwt token
     */
    public String generateToken(Integer userId) {
        Date nowDate = new Date();
        //过期时间
        Date expireDate = new Date(nowDate.getTime() + expire * 1000);

        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setSubject(userId.toString())
                .setIssuedAt(nowDate)
                .setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

    /**
     * 检查token是否有效
     * @param token
     * @return
     */
    public boolean checkToken(String token){
        if(StringUtils.isEmpty(token)){
            return false;
        }
        try{
            Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
        }catch (Exception e){
            log.debug("token error: {} ", e.getMessage());
            return false;
        }
        return true;

    }


    public Long getUserId(String jwtToken){
        Claims claim = getClaimByToken(jwtToken);
        if(Objects.isNull(claim)){
            return null;
        }
        return Long.parseLong(claim.getSubject()) ;
    }


    /**
     * 获取jwt的信息
      */
    public Claims getClaimByToken(String token) {
        if(checkToken(token)){
            return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
        }
        return null;
    }

}
