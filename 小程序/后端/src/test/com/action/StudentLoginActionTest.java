package test.com.action; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;

import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import com.opensymphony.xwork2.ActionProxy;
import com.action.StudentLoginAction;

/** 
* StudentLoginAction Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 16, 2019</pre> 
* @version 1.0 
*/ 
public class StudentLoginActionTest extends StrutsTestCase{

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
//TODO: Test goes here..
    request.setParameter("x", "Sugar");
    request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/StudentLoginAction");
    StudentLoginAction billAction = (StudentLoginAction) proxy.getAction();
    proxy.execute();
    System.out.println("request���ò���numberΪ123456(������)�����Ҹû�Ա�˵�");
    //System.out.println("billAction���ؽ����"+result);
    System.out.println("request���õ�message��ϢΪ��"+request.getAttribute("message"));
    System.out.println(request.getAttribute("res"));
} 


} 
