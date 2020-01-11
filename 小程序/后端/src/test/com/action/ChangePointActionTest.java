package test.com.action;
import com.action.ChangePointAction;
import com.action.GetPrizeListAction;
import com.opensymphony.xwork2.ActionProxy;
import org.junit.Test;
import org.apache.struts2.StrutsTestCase;

/**
* ChangePointAction Tester. 
* 
* @author <Authors name> 
* @since <pre>ʮ���� 30, 2019</pre> 
* @version 1.0 
*/ 
public class ChangePointActionTest extends StrutsTestCase{



/** 
* 
* Method: execute() 
* 
*/ 
@Test
public void testExecute() throws Exception { 
//TODO: Test goes here...
    request.setParameter("username", "yang");
    request.setParameter("mode","+");
    request.setParameter("point","10");
    request.setParameter("q_id","1");
    //request.setParameter("y", "12345");
    ActionProxy proxy = getActionProxy("/ChangePointAction");
    ChangePointAction billAction = (ChangePointAction) proxy.getAction();
    proxy.execute();
} 


} 
