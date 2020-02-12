//package main;

/**
 *
 * @author mihai
 */
public class Basic extends Free {
    private int nrBasic;
    
    public int getNrBasic() {
        return nrBasic;
    }

    public void setNrBasic(int nrBasic) {
        this.nrBasic = nrBasic;
    }

    public Basic(){
    }

    public Basic(String name,int nrBasic) {
        setName(name);
        this.nrBasic = nrBasic;    
    }
   
   /*vom afisa Basic cand un obiect nu mai are cereri Premium,
    ulterior numarul cererilor basic va fi actualizat*/ 
    @Override
    public String printFBP(){
        if(nrBasic > 0){
            nrBasic-=1;
            return "Basic";
        }
        else
            return "Free";
    }   
}