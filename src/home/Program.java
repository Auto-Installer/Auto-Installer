package home;

public class Program {
    public String programCategory;
    public String name;

    public Program(String programCategory, String name){
        this.programCategory = programCategory;
        this.name = name;
    }

    public String getName(){
        System.out.println(this.name);
        return this.name;
    }

    public String getProgramCategory(){
        System.out.println(this.programCategory);
        return this.programCategory;
    }

    public Program(){ }

}
