package test.com.action; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import com.action.TeacherLoginAction;
import com.action.StudentMessageAction;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import com.opensymphony.xwork2.ActionProxy;
/** 
* TeacherLoginAction Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®¶þÔÂ 17, 2019</pre> 
* @version 1.0 
*/ 
public class TeacherLoginActionTest extends StrutsTestCase{

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
    request.setParameter("pass", "12345");
    ActionProxy proxy = getActionProxy("/TeacherLoginAction");
    TeacherLoginAction billAction = (TeacherLoginAction) proxy.getAction();
    proxy.execute();
}
} 
