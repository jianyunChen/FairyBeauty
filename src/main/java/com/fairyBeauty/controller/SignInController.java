package com.fairyBeauty.controller;


import com.fairyBeauty.entity.MUser;
import com.fairyBeauty.entity.base.WebResponseVo;
import com.fairyBeauty.enums.ResponseEnum;
import com.fairyBeauty.service.MUserService;
import com.fairyBeauty.utils.WebResponseException;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 客户信息表 前端控制器
 * </p>
 *
 * @author chenjianyun
 * @since 2021-03-18
 */
@RestController
@RequestMapping("/sign")
public class SignInController {
    @Autowired
    private MUserService userService;

    @ApiOperation(value = "登录", notes = "in")
    @ApiImplicitParam(value = "用户信息", name = "user", required = true)
    @RequestMapping(value = "/signIn", method = RequestMethod.POST)
    public WebResponseVo signIn(@RequestBody MUser user) {
        WebResponseVo result = null;
        try {
            return new WebResponseVo(userService.signIn(user));
        } catch (WebResponseException e) {
            //返回错误信息
            result = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            result = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return result;
    }

    @ApiOperation(value = "退出登录", notes = "signOut")
    @ApiImplicitParam(value = "用户信息", name = "user", required = true)
    @RequestMapping(value = "/signOut", method = RequestMethod.POST)
    public WebResponseVo signOut(MUser user) {
        WebResponseVo result = null;
        try {
            result = new WebResponseVo("退出成功！");
        } catch (WebResponseException e) {
            //返回错误信息
            result = new WebResponseVo(e);
        }catch (Exception ex){
            //返回错误信息
            result = new WebResponseVo(ResponseEnum.UN_KNOW_ERROR,ex.getMessage());
        }
        return result;
    }
}
