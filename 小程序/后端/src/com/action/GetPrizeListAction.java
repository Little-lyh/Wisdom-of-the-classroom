package com.action;

import com.dao.PrizeRecordDao;
import com.dao.StudentDao;
import com.pojo.P_record;
import com.pojo.Prize;
import com.dao.PrizeDao;
import com.opensymphony.xwork2.ActionSupport;
import net.sf.json.JSONObject;
import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.struts2.ServletActionContext;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;

public class GetPrizeListAction extends ActionSupport {
    public String getPrize(){
        HttpServletRequest request= ServletActionContext.getRequest();
        System.out.println("奖品id"+request.getParameter("x"));
        System.out.println(request.getParameter("y"));
        int p_id=Integer.parseInt(request.getParameter("x"));
        Prize prize=new PrizeDao().getPrize(p_id);
        Map map = new HashMap();
        map.put("prize",prize);
        HttpServletResponse response= ServletActionContext.getResponse();
        JsonConfig config = new JsonConfig();
        config.setExcludes(new String[]{"recordList"});
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getPrizeList(){
        List<Prize> prizes=new PrizeDao().getPrizeList();
        Map map = new HashMap();
        map.put("prizes",prizes);
        JsonConfig config = new JsonConfig();
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
    public String getUserPrize(){
        HttpServletRequest request=ServletActionContext.getRequest();
        String username=request.getParameter("username");
        List<P_record> prizes=new PrizeRecordDao().getQuestions(username);
        Map map = new HashMap();
        map.put("prizes",prizes);
        JsonConfig config = new JsonConfig();
        config.registerJsonValueProcessor(Date.class, new com.pojo.JsonDateValueProcessor());
        config.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        HttpServletResponse response= ServletActionContext.getResponse();
        try {
            System.out.println(JSONObject.fromObject(map,config).toString());
            response.setCharacterEncoding("UTF-8");
            response.getWriter().print(JSONObject.fromObject(map,config).toString());
        }catch (Exception e){
            e.printStackTrace();
        }
        return NONE;
    }
}
