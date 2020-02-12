//package main;

/**
 *
 * @author mihai
 */
public abstract class Subscriptie {
    private String name;
    private int timestamp;
    private int nrget;

    public Subscriptie(){
    }
    
    public Subscriptie(String name) {
        this.name = name;  
    } 
    
    public String getName() {
        return name;
    }

    public int getTimestamp() {
        return timestamp;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTimestamp(int timestamp) {
        this.timestamp = timestamp;
    }

    public int getNrget() {
        return nrget;
    }

    public void setNrget(int nrget) {
        this.nrget = nrget;
    }
    /*metoda ce printeaza mesajul corespunzator*/
    public abstract String printFBP();

}


