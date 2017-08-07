package com.spring.dto;
/**
 * @author jlh
 *佰米后台一些不用放入数据库的全局变量
 */
public interface ManaGlobal {

	public static final String ADMIN_USER="adminUser";//用于管理员用户对象sessionkey
	public static final String ADMIN_PERMISSION="adminPermission";//用于管理员用户权限sessionkey
	public static final String CODE_STR="codeStr";//登录页面二维码图片model key
	public static final String PIC_STR="picStr";//登录页面二维码model key
	public static final String SUCCESS="success";//后台返回前台程序执行状态：成功
	public static final String FAIL="fail";//后台返回前台程序执行状态：失败
	public static final String STATUS="status";//用于返回前台在不光只要成功失败消息，还附加带一些其他参数时使用
	public static final String MESSAGE="message";//用于返回前台在不光只要成功失败消息，还附加带一些其他参数时使用
}
