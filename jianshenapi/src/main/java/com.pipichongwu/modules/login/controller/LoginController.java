package com.pipichongwu.modules.login.controller;

import com.pipichongwu.core.Common;
import com.pipichongwu.core.Constants;
import com.pipichongwu.core.util.jsonutil.JsonUtil;
import com.pipichongwu.core.util.jsonutil.StringUtils;
import com.pipichongwu.modules.user.model.User;
import com.pipichongwu.modules.user.service.UserService;
import com.pipichongwu.util.httputil.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by dongao on 2018/3/29.
 */
@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private UserService userService;


    @RequestMapping("wxSession")
    @ResponseBody
    public String login(HttpServletRequest request, @ModelAttribute("common") Common common, @RequestParam("code")String code) throws IOException{
        Map resMap =new HashMap();
        Map codeMap = new HashMap();
        codeMap.put("code","0");
        codeMap.put("msg","登录错误");
        resMap.put("result",codeMap);


        try {
            if(StringUtils.isEmptyString(code)){
                return JsonUtil.toJson(resMap);
            }
            String url = Constants.JSCODE2SESSION;
            url += "?appId="+Constants.APPID;
            url += "&secret="+Constants.SECRET;
            url += "&js_code="+code;
            url += "&grant_type="+Constants.GRANT_TYPE;
            String res = HttpUtil.get(url);
            if(StringUtils.isNotEmptyString(res)) {
                Map<String, String> resMap1 = JsonUtil.parseToMap(res);
                String unionid = resMap1.get("unionid");  //获取unionid，
                String openid = resMap1.get("openid");  //获取unionid，
                String session_key = resMap1.get("session_key");
                if(StringUtils.isNotEmptyString(openid) && StringUtils.isNotEmptyString(session_key)){
                    User user = userService.selectByOpenId(openid);
                    Map bodyMap = new HashMap();
                    if(null == user){
                        user = new User();
                        user.setOpenId(openid);
                        user.setSessionKey(session_key);
                        userService.insert(user);
                        bodyMap.put("role","2");

                    }else{
                        user.setSessionKey(session_key);
                        userService.updateUser(user);
                        if(null == user.getUserRole()){
                            bodyMap.put("role","2");
                        }else{
                            bodyMap.put("role",user.getUserRole());
                        }
                    }
                    codeMap.put("code","1");
                    codeMap.put("msg","");
                    bodyMap.put("userinfo",user);
                    resMap.put("bodyMap",bodyMap);
                    resMap.put("result",codeMap);
                    return JsonUtil.toJson(resMap);

                }else{
                    codeMap.put("msg","调腾讯接口失败");
                    resMap.put("resule",codeMap);
                    return JsonUtil.toJson(resMap);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return JsonUtil.toJson(resMap);
        }
        return JsonUtil.toJson(resMap);
    }


    @RequestMapping("setRole")
    @ResponseBody
    public String setRole(HttpServletRequest request, @ModelAttribute("common") Common common, @RequestParam("tapIndex")String tapIndex) throws IOException{
        Map resMap =new HashMap();
        Map codeMap = new HashMap();
        codeMap.put("code","0");
        codeMap.put("msg","请求错误");
        resMap.put("result",codeMap);

        try {
            if(StringUtils.isEmptyString(tapIndex) || null == common || StringUtils.isEmptyString(common.getUserId())){
                return JsonUtil.toJson(resMap);
            }
            User user = userService.getUser(Integer.valueOf(common.getUserId()));
            if(null == user || null != user.getUserRole()){
                return JsonUtil.toJson(resMap);
            }else{
                user.setUserRole(Integer.valueOf(tapIndex));
                userService.updateUser(user);
                codeMap.put("code","1");
                codeMap.put("msg","");
                return JsonUtil.toJson(resMap);
            }


        } catch (IOException e) {
            e.printStackTrace();
        }

        return "";
    }


}
