//package main;

import java.util.*;

/**
 *
 * @author mihai
 */
public class LRUCache implements Cache{
    int maxCache;
     ArrayList<Subscriptie> lru = new ArrayList<>(maxCache); 

    public LRUCache() {
    }

    public LRUCache(int maxCache) {
       setMaxCache(maxCache);
    }

    public void setMaxCache(int maxCache) {
        this.maxCache = maxCache;
    }

    public int getMaxCache() {
        return maxCache;
    }
    
    @Override
     public void add(Subscriptie s){
        lru.add(s);
    }
     
    /*elimin obiectul cu timestamp ul cel mai mic*/
    @Override
         public void remove(){
             int min = 9999999;
             int poz = 0;
          for(int i=0; i<maxCache; i++)
              if(lru.get(i).getTimestamp() < min){
                min = lru.get(i).getTimestamp();
                poz = lru.indexOf(lru.get((i)));
                }
          lru.remove(lru.get(poz));
         }
    
    /*cauta un element dupa nume in cache*/
    @Override
        public Subscriptie findInCache(String name){
            for(int i = 0; i < lru.size(); i++)
                if( lru.get(i).getName().equals(name) )
                    return lru.get(i);
                return null;
        }
    
    /*scoate un element din cache dupa nume*/
    @Override
        public void removeByName(String name){
            for(int i=0; i<lru.size(); i++)
                if(lru.get(i).getName().equals(name))
                    lru.remove(i);
            }
           
    @Override
        public int dim(){
            return lru.size();
        }
}
