package server;

public class  Program  {
    public Object name;
    public Object version;

    Program(Object name, Object version){
        this.name = name;
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
