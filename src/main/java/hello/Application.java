package hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static org.springframework.security.web.context.HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY;

@SpringBootApplication
@Controller
public class Application {

    @Autowired
    DaoAuthenticationProvider provider;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    @ResponseBody
    public boolean login(@RequestBody LoginBean loginBean, HttpServletRequest request){

        UsernamePasswordAuthenticationToken authReq
                = new UsernamePasswordAuthenticationToken(loginBean.getUsername(), loginBean.getPassword());
        try {
            Authentication auth = provider.authenticate(authReq);
            SecurityContext sc = SecurityContextHolder.getContext();
            sc.setAuthentication(auth);
            HttpSession session = request.getSession(true);
            session.setAttribute(SPRING_SECURITY_CONTEXT_KEY, sc);
            return true;
        } catch (AuthenticationException e){
            return false;
        }
    }

    @GetMapping("/resource")
    @ResponseBody
    public Map<String, Object> home() {
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("id", UUID.randomUUID().toString());
        model.put("content", "Hello World");
        return model;
    }

    @GetMapping("/isAuthenticated")
    @ResponseBody
    public boolean isAuthenticated(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object o = session.getAttribute(SPRING_SECURITY_CONTEXT_KEY);
        return o != null;
    }

    public static void main(String[] args){
        SpringApplication.run(Application.class, args);
    }

}
