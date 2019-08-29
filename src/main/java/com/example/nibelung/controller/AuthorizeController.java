package com.example.nibelung.controller;

import com.example.nibelung.dto.AccessTokenDTO;
import com.example.nibelung.dto.GithubUser;
import com.example.nibelung.provider.GithubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

/**
 * @ Author    :Nibelung
 * @ Date      ：Created in 10:10 2019/8/29
 * @ Description :
 * @ Modified By :
 * @ Version : $
 */
@Controller
public class AuthorizeController {

    @Autowired
    private GithubProvider githubProvider;

    @Value("${github.client.id}")
    private  String clientId;

    @Value("${github.Client_secret}")
    private  String clientSecret;

    @Value("${github.Redirect_uri}")
    private  String RedirecrUrl;


    @GetMapping("callback")
    public  String callback(@RequestParam(name = "code")String code,
                            @RequestParam(name = "state")String state,
                            HttpServletRequest request){
        AccessTokenDTO accessTokenDTO = new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(clientSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(RedirecrUrl);
        accessTokenDTO.setState(state);
        String accessToken = githubProvider.getAccessToken(accessTokenDTO);
        GithubUser user = githubProvider.getUser(accessToken);
        if (user!=null){
            //登录成功
            request.getSession().setAttribute("user",user);
            return "redirect:/";
        }
        else {
            //登录失败
            return  "redirect:/";
        }

    }
}
