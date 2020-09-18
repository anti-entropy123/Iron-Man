package com.mbry.IronMan.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.mbry.IronMan.Utils.JwtTokenUtil;

@SuppressWarnings("SpringJavaAutowiringInspection")
@Component
public class JwtAuthenticationTokenFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService jwtUserDetailsService;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Value("${jwt.header}")
    private String tokenHeader;

    @Value("${jwt.tokenHead}")
    private String tokenHead;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain) throws ServletException, IOException {
        
        String authHeader = request.getHeader(this.tokenHeader);
        if (authHeader != null && authHeader.startsWith(tokenHead)) {
            final String authToken = authHeader.substring(tokenHead.length()); // The part after "Bearer "
            String[] idAndAuth = jwtTokenUtil.getIdAndAuthFromToken(authToken);
            String id = idAndAuth[0];
            String auth = idAndAuth[1];
            if(id != null && auth != null){
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    // 如果我们足够相信token中的数据，也就是我们足够相信签名token的secret的机制足够好
                    // 这种情况下，我们可以不用再查询数据库，而直接采用token中的数据
                    // 本例中，我们还是通过Spring Security的 @UserDetailsService 进行了数据查询
                    // 但简单验证的话，你可以采用直接验证token是否合法来避免昂贵的数据查询
                    UserDetails user = this.jwtUserDetailsService.loadUserByUsername(id);
                    // if (jwtTokenUtil.validateToken(authToken, userDetails)) {
                    //     UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    //             userDetails, null, userDetails.getAuthorities());
                    //     authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(
                    //             request));
                    //     logger.info("authenticated user " + username + ", setting security context");
                    //     SecurityContextHolder.getContext().setAuthentication(authentication);
                    // }
                    String name = user.getUsername();
                    if(name != null && name.length()>0){
                        UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            user, null, user.getAuthorities());
                        authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                        logger.info("用户: " +name+ "通过身份认证");
                        SecurityContextHolder.getContext().setAuthentication(authentication);
                    }
                }
            }
        }
        chain.doFilter(request, response);
    }

    // @Override
    // protected Boolean preHandle(ServletRequest request, ServletResponse response){
    //     HttpServletRequest httpServletRequest = (HttpServletRequest)request;
    //     HttpServletResponse httpServletResponse = (HttpServletResponse)response;

    //     httpServletResponse.setHeader("Access-Control-Allow-Origin", httpServletRequest.getHeader("Origin"));
    //     // httpServletResponse.setHeader("Access-Control-Allow-Credentials", "true");
    //     httpServletResponse.setHeader("Access-Control-Allow-Methods", "POST,GET,PUT,PATCH,OPTIONS,DELETE");
    //     httpServletResponse.setHeader("Access-Control-Allow-Headers", httpServletRequest.getHeader("Access-Control-Request-Headers"));
    //     if(httpServletRequest.getMethod().equals(RequestMethod.OPTIONS.name())){
    //         return false;
    //     }
        
    //     return true;
    // }
}
