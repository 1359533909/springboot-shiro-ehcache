package com.mxl.demo.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mxl.demo.entity.User;

/**
 * @author 莫小林
 *
 */
@Controller
public class DemoController {
	@RequestMapping(value="/toLogin")
	public String toLogin(Model model) {
		return "login";
	}
	@RequestMapping(value="/login")
	public String Login(String username,String password,Model model) {
		Subject subject = SecurityUtils.getSubject();
		UsernamePasswordToken token=new UsernamePasswordToken(username,password);
		try {
			subject.login(token);
			return "/user/index";
		} catch (UnknownAccountException e) {
			// 用户名不存在
			model.addAttribute("msg", "用户名不存在!");
			return "login";
		}catch (IncorrectCredentialsException e) {
			// TODO: handle exception
			model.addAttribute("msg", "密码不正确!");
			return "login";
		}catch(ExcessiveAttemptsException e){
			model.addAttribute("msg", "登录次数已经超过限制，请一分钟后重试");
			return "login";
		}
	}
	@RequestMapping(value="/Unauthor")
	public String unauthor() {
		return "Unauthor";
		
	}
	/**
	 * 登录之前页面跳转
	 */
	@RequestMapping(value="/index")
	public String toIndex() {
		return "index";
	}
	@RequestMapping(value="/SupplyOfGoods")
	public String toSupplyOfGoods() {
		return "SupplyOfGoods";
	}
	@RequestMapping(value="/SupplyOfCars")
	public String toSupplyOfCars() {
		return "SupplyOfCars";
	}
	@RequestMapping(value="/SupplyOfAirplane")
	public String toSupplyOfAirPlane() {
		return "SupplyOfAirplane";
	}
	@RequestMapping(value="/NewsInfo")
	public String toNewsInfo() {
		return "NewsInfo";
	}
	@RequestMapping(value="/aboutWe")
	public String toaboutWe() {
		return "aboutWe";
	}
	/**
	 * 登录成功之后页面跳转user/
	 */
	@RequestMapping(value="/user/index")
	public String toUserIndex() {
		
		return "user/index";
	}
	@RequestMapping(value="/user/SupplyOfAirplane")
	public String toUserSupplyOfAirplane() {
		
		return "user/SupplyOfAirplane";
	}
	@RequestMapping(value="/user/SupplyOfGoods")
	public String toUserSupplyOfGoods() {
		
		return "user/SupplyOfGoods";
	}
	@RequestMapping(value="/user/SupplyOfCars")
	public String toUserSupplyOfCars() {
		
		return "user/SupplyOfCars";
	}
	@RequestMapping(value="/user/aboutWe")
	public String toUseraboutWe() {
		
		return "user/aboutWe";
	}
	@RequestMapping(value="/user/NewsInfo")
	public String toUserNewsInfo() {
		
		return "user/NewsInfo";
	}
	
	//跳转:
public String totiaozhuan() {
		
		return "redirect:index.html";
	}
}
