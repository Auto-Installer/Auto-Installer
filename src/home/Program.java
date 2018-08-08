package home;

public class Program {
    private String programCategory;
    private String name;

    Program(String programCategory, String name){
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

}
