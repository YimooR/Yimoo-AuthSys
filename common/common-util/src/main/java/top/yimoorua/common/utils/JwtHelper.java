package top.yimoorua.common.utils;

import io.jsonwebtoken.*;
import org.springframework.util.StringUtils;


import java.util.Date;

/**
 * @ClassName JwtHelper
 * @Description TODO
 * @date 2023/4/9 20:51
 * @Version 1.0
 * @Author hao yang
 */
public class JwtHelper {
    /**
     * token过期时间
     */
    private static long tokenExpiration = 365 * 24 * 60 * 60 * 1000;
    /**
     * 加密密钥
     */
    private static String tokenSignKey = "123456";

    /**
     * 用户id和用户名称生成token字符串
     * @param userId
     * @param username
     * @return
     */
    public static String createToken(String userId, String username) {
        String token = Jwts.builder()
                .setSubject("AUTH-USER")
                .setExpiration(new Date(System.currentTimeMillis() + tokenExpiration))
                .claim("userId", userId)
                .claim("username", username)
                .signWith(SignatureAlgorithm.HS512, tokenSignKey)
                .compressWith(CompressionCodecs.GZIP)
                .compact();
        return token;
    }

    /**
     * 获取用户id
     * @param token
     * @return
     */
    public static String getUserId(String token) {
        try {
            if (StringUtils.isEmpty(token)) {return null;}

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            String userId = (String) claims.get("userId");
            return userId;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    /**
     * 获取用户姓名
     * @param token
     * @return
     */
    public static String getUsername(String token) {
        try {
            if (StringUtils.isEmpty(token)) {
                return "";
            }

            Jws<Claims> claimsJws = Jwts.parser().setSigningKey(tokenSignKey).parseClaimsJws(token);
            Claims claims = claimsJws.getBody();
            return (String) claims.get("username");
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public static void removeToken(String token) {
        //jwttoken无需删除，客户端扔掉即可。
    }

}
