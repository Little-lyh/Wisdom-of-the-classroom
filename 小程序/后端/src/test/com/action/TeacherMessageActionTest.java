package test.com.action; 

import org.junit.Test; 
import org.junit.Before; 
import org.junit.After;
import com.action.TeacherMessageAction;
import com.action.StudentMessageAction;
import org.apache.struts2.StrutsTestCase;
import org.junit.Test;
import com.opensymphony.xwork2.ActionProxy;

/** 
* TeacherMessageAction Tester. 
* 
* @author <Authors name> 
* @since <pre>Ê®¶þÔÂ 17, 2019</pre> 
* @version 1.0 
*/ 
public class TeacherMessageActionTest extends StrutsTestCase{

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
    //request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/TeacherMessageAction");
    TeacherMessageAction billAction = (TeacherMessageAction) proxy.getAction();
    proxy.execute();
}
} 
