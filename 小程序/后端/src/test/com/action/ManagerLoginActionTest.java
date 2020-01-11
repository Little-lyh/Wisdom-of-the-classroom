package test.com.action; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import com.action.GetProblemListAction;
import com.opensymphony.xwork2.ActionProxy;
import org.apache.struts2.StrutsTestCase;
import com.action.ManagerLoginAction;
/** 
* ManagerLoginAction Tester. 
* 
* @author <Authors name> 
* @since <pre>һ�� 7, 2020</pre> 
* @version 1.0 
*/ 
public class ManagerLoginActionTest extends StrutsTestCase{

@Before
public void before() throws Exception { 
} 

@After
public void after() throws Exception { 
} 

/** 
* 
* Method: execute() 
* 
*/ 
@Test
public void testExecute() throws Exception { 
//TODO: Test goes here...
    request.setParameter("username", "yang");
    request.setParameter("pass","12345");
    //request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/ManagerLoginAction");
    ManagerLoginAction billAction = (ManagerLoginAction) proxy.getAction();
    proxy.execute();
} 


} 
