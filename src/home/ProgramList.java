package home;
import java.lang.reflect.Array;

public class ProgramList {
    public Object gamingApplications;
    public Object IDEs;

    // Constructor
    public ProgramList(Object gamingApplications,Object IDEs){
        this.gamingApplications = gamingApplications;
        this.IDEs = IDEs;
    }

    // Dummy Constructor for Jackson, idk why this works lol
    public ProgramList(){}

    public Object getGamingApplications(){
        System.out.println(this.gamingApplications);
        return this.gamingApplications;
    }
    public Object getIDEs(){
        System.out.println(this.IDEs);
        return this.IDEs;
    }


}
