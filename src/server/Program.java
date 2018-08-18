package server;

public class  Program  {
    public Object name;
    public Object category;
    public Object version;

    Program(Object name,Object category, Object version){
        this.name = name;
        this.category = category;
        this.version =  version;
    }

    Program(){ }

    public Object getName(){
        return name;
    }

    public Object getVersion(){
        return version;
    }

}
