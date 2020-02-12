//package main;

import java.util.*;

/**
 *
 * @author mihai
 */
public class Memorie {
    
     ArrayList<Subscriptie> memory = new ArrayList<>();

    public Memorie() {
    }
    
    /*nr elem*/
    public int dimensiune(){
        return memory.size();
    }
    
    /*adauga un element de tip subscriptie*/
    public void add(Subscriptie s){
        memory.add(s);
    }

    /*returneaza obiectul daca il gaseste in memorie sau null, altfel*/
    public Subscriptie searchInMemory(String name){
        for(int i = 0; i < memory.size(); i++ )
            if( (memory.get(i).getName()).equals(name) )
                return memory.get(i);
            return null;
    }

    /*scoate elem din memorie*/
    public void remove(String name){
        for(int i=0; i<memory.size(); i++)
            if(memory.get(i).getName().equals(name)){
               memory.get(i).setNrget(0); 
               memory.remove(i); 
            }
        }
    }
