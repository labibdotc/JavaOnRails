package jrails;

import java.util.*;
import java.lang.Object.*;
import java.lang.*;
import java.lang.reflect.*;
import java.io.File;
import java.io.*;



public class Model {
    //private static Map<Object,Integer>map = new HashMap<>(); //model to id
    private static Map<Object,List<Object>>saved = new LinkedHashMap<>(); //model to corresponding field values (preserves insertion order)
    private static int id_count = 1;
    public int id = 0;
    private static Boolean serverSynced = false;

    public static void SyncFileData() {
        //System.out.println("was: " + saved.size());
        serverSynced = true;
        try {
            File file = new File("db.txt");
            Scanner read = new Scanner(file);
            while (read.hasNextLine()) {
                String row = read.nextLine();
                List<String> rowdata = Arrays.asList(row.split(","));
                try{
                    //System.out.println()
                    Class <?>c = Class.forName(rowdata.get(0));
                    Constructor<?>cons= c.getConstructor();
                    Object classobject = cons.newInstance();
                    Field[]fields = c.getDeclaredFields();
                    ((Model)(classobject)).id = Integer.parseInt(rowdata.get(1));
                    id_count = Integer.max(Integer.parseInt(rowdata.get(1)) + 1, id_count);
                    int i = 2;
                    List<Object>fieldSetValues = new ArrayList<>();
                    for(Field field: fields) {
                        if(field.isAnnotationPresent(Column.class)) {
                            Object o = field.get(classobject);
                            if(o instanceof String) {
                                field.set(classobject, rowdata.get(i));
                                fieldSetValues.add(rowdata.get(i));
                                }
                            else if(o instanceof Integer) {
                            fieldSetValues.add(Integer.parseInt(rowdata.get(i)));
                                field.set(classobject, Integer.parseInt(rowdata.get(i)));}
                                //System.out.print((Integer)o + " ");
                            else if(o instanceof Boolean) {
                                fieldSetValues.add(Boolean.parseBoolean(rowdata.get(i)));
                                field.set(classobject, Boolean.parseBoolean(rowdata.get(i)));
                                }
                                //System.out.print((Boolean)o + " ");
                            i++;
                        }
                    }
        
                    saved.put(classobject,fieldSetValues);

                } catch(Exception e) {
                    e.printStackTrace();
                }
                
            }
            read.close();
        } catch (FileNotFoundException e) {
            System.out.println("Data base never existed before this run.");
            e.printStackTrace();
        }
        //System.out.println("AfterSyncing: " + id_count);
    }
    //we check if this model exist in map
    //objects to ids
    //
    //if it does we check id
    // private void openFile(){
    //     try {
    //         //opens file if not open
    //         if (dbfile.createNewFile()) {
    //         } 
    //     } catch (IOException e) {
    //         System.out.println("An error occurred.");
    //         e.printStackTrace();
    //     }
    // }
    private static void printFile(){

        try{
            FileWriter file = new FileWriter("db.txt");
            
            for(Map.Entry<Object,List<Object>> entry : saved.entrySet()) {
                List<Object>list = entry.getValue();
                Object key = entry.getKey();
                file.write(key.getClass().getSimpleName()+",");
                file.write(((Model)key).id+",");
                //System.out.println("here");
                //System.out.println(list.size());
                for(Object o : list) {
                    if(o instanceof String)
                        file.write((String)o + ",");
                    else if(o instanceof Integer) 
                        file.write((Integer)o + ",");
                    else if(o instanceof Boolean) 
                        file.write((Boolean)o + ",");
                    else if(o == null) 
                        file.write("null,");
                } file.write("\n");
            }
            file.close();
        }
        catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }
    public void save() {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        //if(saved.size() == 0) {SyncFileData();}
        /* this is an instance of the current model */
        //get this and find all of its annotated column fields
        //put data in file
        if(id == 0) { // not in db
            id = id_count;
            id_count++;
            List<Object>vals = getFields();
            saved.put(this, vals);
        } else { // in db
            List<Object>vals = getFields();
            //if(vals.equals(saved.get(this))) return;
            for(Object key: saved.keySet()) {
                if(((Model)(key)).id == id) {saved.remove(key); saved.put(this, vals); printFile(); return;}
            }
            throw new UnsupportedOperationException("non zero id not in the database");
        }
        printFile();
    }
    private List<Object> getFields() {
        Field[]fields = this.getClass().getDeclaredFields();
        // System.out.println(fields.length);
        List<Object> vals = new ArrayList<>();
        for(Field field: fields) {
            //System.out.println(field.getName() + "------");
            if(field.isAnnotationPresent(Column.class)) {
                try{
                    Object o = field.get(this);
                    if(o instanceof String) 
                        vals.add((String)o);
                    else if(o instanceof Integer) 
                        vals.add((Integer)o);
                        //System.out.print((Integer)o + " ");
                    else if(o instanceof Boolean) 
                        vals.add((Boolean)o);
                        //System.out.print((Boolean)o + " ");
                    else if(o == null) {
                        vals.add(null);
                    }
                        
                    else 
                        throw new IllegalAccessException("Model's column type is not supported");
                } catch(Throwable e) {

                }
                
            }
        }
        return vals;
    }
    
    public int id() {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        return id;
        //return map.get(this) == null ? 0: map.get(this);
    }

    public static <T> T find(Class<T> c, int id) {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        //throw new UnsupportedOperationException();
        //if(!saved.keySet().contains(id)) return null;

        try{
            Object o = null;
            //for (Map.Entry<Object,Integer> entry : map.entrySet()) 
            for(Object key: saved.keySet()) 
                if(((Model)(key)).id == id) o = key;
            if(o == null) {return null;}
            Object res;
            Constructor<?>cons = c.getConstructor();
            res = cons.newInstance();
            Field[]fields = c.getFields();
            int counter = 0;
            List<Object>vals = saved.get(o);
            for(Field field: fields) {
                if(field.isAnnotationPresent(Column.class)) {
                    field.set(res, vals.get(counter));
                    counter++;
                } else if(field.getName() == "id") {
                    field.set(res, id);
                }
            }
            return c.cast(res);
        } catch (Throwable e) {
            
            System.out.println(e.getMessage());
        }
        
        return null;
        
    }

    public static <T> List<T> all(Class<T> c) {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        List<T>res = new LinkedList<>();
        for(Object key: saved.keySet()) {
            if(key.getClass().equals(c)) {
                res.add(c.cast(key));
            }
        }
        return res;
    }

    public void destroy() {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        if(saved.get(this) == null) {throw new UnsupportedOperationException("Destroy called on un-saved db item");}
       //map.remove(this);
        this.id = 0;
        saved.remove(this);
        printFile();
    }

    public static void reset() {
        if(saved.size() == 0 && !serverSynced) {SyncFileData();}
        //map.clear();
        for(Object key: saved.keySet()) {
            ((Model)(key)).id = 0;
        }
        saved.clear();
        id_count = 1;
        printFile();
    }
}
