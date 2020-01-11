package test.com.action; 


import com.action.GetPrizeListAction;
import com.action.GetProblemListAction;
import com.opensymphony.xwork2.ActionProxy;
import com.action.GetProblemListAction;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;

/** 
* GetPrizeListAction Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 30, 2019</pre> 
* @version 1.0 
*/ 
public class GetPrizeListActionTest extends StrutsTestCase{



/** 
* 
* Method: getPrizeList() 
* 
*/ 
@Test
public void testGetPrizeList() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUserPrize() 
* 
*/ 
@Test
public void testGetUserPrize() throws Exception { 
//TODO: Test goes here...
    request.setParameter("username", "yang");
    //request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/GetPrizeListAction_getUserPrize");
    GetPrizeListAction billAction = (GetPrizeListAction) proxy.getAction();
    proxy.execute();
}
} 
