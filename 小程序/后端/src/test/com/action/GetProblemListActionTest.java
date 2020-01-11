package test.com.action;

import com.action.GetProblemListAction;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import com.action.GetProblemListAction;

/** 
* GetProblemListAction Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 30, 2019</pre> 
* @version 1.0 
*/ 
public class GetProblemListActionTest extends StrutsTestCase{



/** 
* 
* Method: getAllProblem() 
* 
*/ 
@Test
public void testGetAllProblem() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getTeacherProblem() 
* 
*/ 
@Test
public void testGetTeacherProblem() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: changeQuestion() 
* 
*/ 
@Test
public void testChangeQuestion() throws Exception { 
//TODO: Test goes here... 
} 

/** 
* 
* Method: getUserQuestion() 
* 
*/ 
@Test
public void testGetUserQuestion() throws Exception { 
//TODO: Test goes here...
    request.setParameter("username", "yang");
    //request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/GetProblemListAction_getUserQuestion");
    GetProblemListAction billAction = (GetProblemListAction) proxy.getAction();
    proxy.execute();

} 


} 
