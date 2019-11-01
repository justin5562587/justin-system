package com.justin.system.entity.utils;

import com.auth0.jwt.interfaces.Claim;
import com.justin.system.entity.basic.SystemConstant;
import com.justin.system.models.User;
import com.justin.system.repository.UserRepository;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;
import java.util.Optional;

public class TokenIntercptor {

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = Logger.getLogger(TokenIntercptor.class);

    @Override
    public boolean preHandle(HttpServletRequest req, HttpServletResponse res, Object handler) throws Exception {
        logger.debug("Access token executing...");

        boolean ret = false;

        String token = req.getHeader("Authorization");
        if (token == null || token.equals("")) {
            ret = true;
        } else {
            try {
                String email = JwtUtil.getClaim(token, SystemConstant.USER_EMAIL);
                Optional<User> optionalUser = userRepository.findByEmail(email);
                if (optionalUser.isPresent()) {
                    Map<String, Claim> claimMap = JwtUtil.validateToken(token, optionalUser.get());

                } else {
                    throw new RuntimeException("User Not Existed");
                }
            } catch (Exception e) {
                throw new RuntimeException(e.toString());
            }

        }
        return ret;
    }

}
