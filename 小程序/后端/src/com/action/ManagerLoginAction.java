package com.action;

import com.dao.ManagerDao;
import com.opensymphony.xwork2.ActionSupport;
import com.pojo.Manager;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;


public class ManagerLoginAction extends ActionSupport {
    public String execute(){
        HttpServletRequest request= ServletActionContext.getRequest();
        String username=request.getParameter("username");
        String password=request.getParameter("pass");
        System.out.println(username);
        Manager manager=new ManagerDao().getManager(username);
        if(manager==null){
            return ERROR;
        }
        else if(password.equals(manager.getPassword())){
            return SUCCESS;
        }else{
            return ERROR;
        }
    }

}
