//package main;

import java.util.*;

/**
 *
 * @author mihai
 */
public class FIFOCache implements Cache {
     private int maxCache;
     ArrayList<Subscriptie> fifo = new ArrayList<>(maxCache);
   
    public int getMaxCache() {
        return maxCache;
    }

    public void setMaxCache(int maxCache) {
        this.maxCache = maxCache;
    }

    public FIFOCache() {
    }

    public FIFOCache(int maxCache) {
        setMaxCache(maxCache);
    }

     @Override
    public void add(Subscriptie s){
        fifo.add(s);
    }
    
     @Override
    public void remove(){
        fifo.remove(0);
    }
    
    /*cauta un element dupa nume in cache*/
     @Override
    public Subscriptie findInCache(String name){
        for(int i = 0; i < fifo.size(); i++ )
            if( fifo.get(i).getName().equals(name) )
                return fifo.get(i);
            return null;
    }
    
    /*scoate un element din cache dupa nume*/
     @Override
     public void removeByName(String name){
        int i;
        for(i=0; i<fifo.size(); i++)
            if(fifo.get(i).getName().equals(name))
                fifo.remove(i);
    }
     
     @Override
    public int dim(){
        return fifo.size();
    }
}
