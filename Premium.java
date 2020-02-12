//package main;

/**
 *
 * @author mihai
 */

public class Premium extends Basic{
    private int nrPremium;

    public int getNrPremium() {
        return nrPremium;
    }

    public void setNrPremium(int nrPremium) {
        this.nrPremium = nrPremium;
    }

    public Premium(){  
    }
    
    public Premium(int nrPremium, int nrBasic, String name) {
        setName(name);
        setNrBasic(nrBasic);
        setNrPremium(nrPremium);
    }  
    
       /*vom afisa Premium cand un obiect are toate tipurile ce cereri,
    ulterior numarul cererilor premium va fi actualizat*/ 
    @Override
    public String printFBP(){
        if(nrPremium > 0){
            nrPremium-=1 ;
            return "Premium";
        }else if(getNrBasic() > 0){
            setNrBasic(getNrBasic()-1);
                return "Basic";}
        else
            return "Free";    
    }
}
