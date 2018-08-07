package home;

public class Program {
    public String programCategory;
    public String name;

    public String getName(){
        System.out.println(this.name);
        return this.name;
    }

    public String getProgramName(){
        System.out.println(this.programCategory);
        return this.programCategory;
    }

}
