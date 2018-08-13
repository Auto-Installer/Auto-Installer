package home;

public class ProgramList<P> {
    public P[] gamingApplications;
    public P[] IDEs;
    public P notDefined;

    // Constructor
    public ProgramList(P[]  gamingApplications, P[]  IDEs){
        this.gamingApplications = gamingApplications;
        this.IDEs = IDEs;
        this.notDefined = null;
    }

    // Dummy Constructor for Jackson, idk why this works lol
    public ProgramList(){}

    public P[]  getGamingApplications(){
        return this.gamingApplications;
    }
    public P[]  getIDEs(){
        return this.IDEs;
    }


}
