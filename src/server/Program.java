package server;

public class  Program  {
    public Object name;
    public Object exeName;
    public Object category;
    public Object version;
    public Object img_dir;

    Program(Object name,Object category, Object version, Object exeName, Object img_dir){
        this.name = name;
        this.exeName = exeName;
        this.category = category;
        this.version =  version;
        this.img_dir = img_dir;
    }

    Program(){ }

    public Object getName(){
        return name;
    }

    public Object getVersion(){
        return version;
    }

}
