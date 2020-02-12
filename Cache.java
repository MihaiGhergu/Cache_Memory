//package main;

/**
 *
 * @author mihai
 */
public interface Cache {
    /*adauga un element de tip subscriptie in memorie*/
    public abstract void add(Subscriptie s); 
    
    /*scoate din memorie elementul care trebuie in functie de tipul de cache*/
    public abstract void remove();
    
     /*cauta un element dupa nume*/
    public abstract Subscriptie findInCache(String name);
    
    /*scoate din memorie un element dupa nume*/
    public abstract void removeByName(String name);
    
    /*returneaza lungimea memorie cache*/
    public abstract int dim();

}
