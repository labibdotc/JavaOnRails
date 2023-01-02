package jrails;

import java.util.Map;
import java.util.*;
import java.lang.reflect.*;

public class JRouter {
    private static Map<String, List<String>>m = new HashMap<>();
    public void addRoute(String verb, String path, Class clazz, String method) {
        // Implement me!
        //System.out.println(clazz.getName());
        m.put(verb+"///"+path, new ArrayList<>() {
            {
            add(clazz.getName());
            add(method);
            }
        });
    }

    // Returns "clazz#method" corresponding to verb+URN
    // Null if no such route
    public String getRoute(String verb, String path) {
        if(m.get(verb+"///"+path) == null) return null;
        return m.get(verb+"///"+path).get(0) + "#"+m.get(verb+"///"+path).get(1);
    }

    // Call the appropriate controller method and
    // return the result
    public Html route(String verb, String path, Map<String, String> params) {
        if(m.get(verb+"///"+path) == null){throw new UnsupportedOperationException();}
        //get class
        //get method
        //call method with params
        List<String> l =m.get(verb+"///"+path);
        String clazz = l.get(0);
        String meth = l.get(1);
        try{
            Class <?> c = Class.forName(clazz);
            Method[]methods= c.getMethods();
            for(Method method: methods) {
                if(method.getName().equals(meth)){
                    return (Html)method.invoke(null, params);
                }
            }

        } catch(Exception e) {
            // throw new UnsupportedOperationException();
        }
        //return null;
        throw new UnsupportedOperationException();
    }
}
