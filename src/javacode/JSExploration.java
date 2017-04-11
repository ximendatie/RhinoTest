package javacode;   
  
import java.io.FileReader;   
import java.io.LineNumberReader;   
  
import org.mozilla.javascript.Context;   
import org.mozilla.javascript.Function;   
import org.mozilla.javascript.Scriptable;   
  
public class JSExploration   
{   
    private Context cx;   
  
    private Scriptable scope;   
  
    public JSExploration()   
    {   
        this.cx = Context.enter();   
        this.scope = cx.initStandardObjects();   
    }   
  
//  运行js
    public Object runJavaScript(String filename)   
    {   
        String jsContent = this.getJsContent(filename);   
        Object result = cx.evaluateString(scope, jsContent, filename, 1, null);   
        return result;   
    }   
  
 // 从js文件读出内容，返回String
    private String getJsContent(String filename)   
    {   
        LineNumberReader reader;   
        try  
        {   
            reader = new LineNumberReader(new FileReader(filename));   
            String s = null;   
            StringBuffer sb = new StringBuffer();   
            while ((s = reader.readLine()) != null)   
            {   
                sb.append(s).append("\n");   
            }   
            return sb.toString();   
        }   
        catch (Exception e)   
        {   
            // TODO Auto-generated catch block   
            e.printStackTrace();   
            return null;   
        }   
    }   
  
  
    public Scriptable getScope()   
    {   
        return scope;   
    }   
  
//    个人将scope理解为js的全局
    public static void main(String[] args)   
    {   
    	JSExploration jsExploration = new JSExploration();
    	
        String filename = System.getProperty("user.dir") + "/src/jscode/jsmap.js";       
        Object result = jsExploration.runJavaScript(filename);   
        
        Scriptable scope = jsExploration.getScope();   
        Scriptable obj = (Scriptable) scope.get("obj", scope);   
        System.out.println("obj.a == " + obj.get("a", obj));   
        Scriptable b = (Scriptable) obj.get("b", obj);   
        System.out.println("b[0] == " + b.get(0, b));   
        Boolean flag = (Boolean) scope.get("flag", scope);   
        System.out.println(flag);   
  
        Scriptable myobj = (Scriptable) scope.get("obj", scope);   
        Boolean myflag = (Boolean) scope.get("flag", scope);   
        System.out.println(myflag);   
  
        Scriptable jsFunction = (Scriptable) scope.get("jsFunction", scope);   
        Function fc = (Function) jsFunction.get("handler", jsFunction);   
        Object isPrime = fc.call(Context.getCurrentContext(), jsFunction, fc, new Object[] { "this is my test" });   
    }   
}   
  