//package main;
import java.io.*;
import java.util.*;
/**
 *
 * @author mihai
 */
public class Main {

    public static void main(String[] args) throws IOException {
       
    try{ 
        /*input file*/
         File fileName = new File(args[0]);
         Scanner in;
         in = new Scanner(fileName); 
         
        /*ouput file*/
        File fileOut = new File(args[1]);
        if(!fileOut.exists()){
            fileOut.createNewFile();
        }
        
        try (PrintWriter out = new PrintWriter(fileOut)) {
            String line;
            String nameObj;
            String[] tok;    /*vectorul in care bag cele 2/3/4 stringuri*/
            int nrBasic;
            int nrPremium;
            /*citirea primelor 3 linii din fisierul de intrare*/
            String type = in.next();
            int maxCache = Integer.parseInt(in.next());
            int nrOp = Integer.parseInt(in.next());
            int times=0; 
            
            /*memoria principala*/
            Memorie m = new Memorie();
            
            /* declararea memoriei cache in functie de type*/
            Cache mCache;
             switch (type) {
                 case "FIFO":
                     mCache = new FIFOCache(maxCache);
                     break;
                 case "LRU":
                     mCache = new LRUCache(maxCache);
                     break;
                 default:
                     mCache = new LFUCache(maxCache);
                     break;
             }
            /* citirea fiecarei linii si parsarea acestora*/ 
            line = in.nextLine();
            for(int i=0; i<nrOp; i++){
                times+=1;
                line = in.nextLine();
                tok = line.split(" ");
            /*in functie de lungimea vectorului,iau decizia pt GET/ADD*/
                /* GET pentru cele 3 tipuri de memorie cache */
                if( tok.length == 2 ){
                    nameObj = tok[1];
                    Subscriptie elemM = m.searchInMemory(nameObj);
                    Subscriptie elemC = mCache.findInCache(nameObj);
                    
                   /*obiectul se gaseste in cache*/ 
                    if(elemC != null){
                        out.println("0 "+ elemC.printFBP());
                          elemC.setNrget(elemC.getNrget()+1);
                          elemC.setTimestamp(times);       
                    }
                    
                    /*doar in memoria principala*/
                    if(elemM != null && elemC == null){
                        switch (type) {
                                /*pt FIFO*/
                            case "FIFO":
                        /*mem. este plina si trebuie sa eliminam si apoi sa adaugam*/
                                if(mCache.dim() >= maxCache){
                                    mCache.remove();
                                    mCache.add(elemM);
                                    out.println("1 "+elemM.printFBP());
                                }else {
                                /*mai exista locuri disponibile in memorie*/
                                    mCache.add(elemM);
                                    out.println("1 "+elemM.printFBP());
                                }   break;
                                
                                /*pt LRU*/
                            case "LRU":
                                /*mai exista locuri disponibile in memorie*/
                                if(mCache.dim() < maxCache){
                                    mCache.add(elemM);
                                    elemM.setTimestamp(times);
                                    out.println("1 "+elemM.printFBP());
                                }else
                                /*mem. este plina si trebuie sa eliminam si apoi sa adaugam*/
                                if(mCache.dim() >= maxCache){
                                    out.println("1 "+elemM.printFBP());
                                    mCache.remove();
                                    elemM.setTimestamp(0);
                                    mCache.add(elemM);
                                    elemM.setTimestamp(times);
                                }
                                break;
                                
                                /*pt LFU*/
                            case "LFU":
                                /*mai exista locuri disponibile in memorie*/
                                if(mCache.dim() < maxCache){
                                    mCache.add(elemM);
                                    elemM.setNrget(0);
                                    out.println("1 "+elemM.printFBP());
                                    elemM.setTimestamp(times);
                                    elemM.setNrget(elemM.getNrget()+1);
                                }else 
                                /*mem. este plina si trebuie sa eliminam si apoi sa adaugam*/
                                    if (mCache.dim() >= maxCache){
                                    out.println("1 "+elemM.printFBP());
                                    mCache.remove();
                                    elemM.setTimestamp(0);
                                    elemM.setNrget(0);
                                    mCache.add(elemM);
                                    elemM.setTimestamp(times);
                                    elemM.setNrget(elemM.getNrget()+1);
                                }   
                                break;
                            default:
                                break;
                        }
                    }
                    
                    /*elementul nu se gaseste in memoria principala*/
                    if(elemM == null )
                        out.println("2");             
                }
                
                /*adaug in mem. pr. daca obiectul are doar cereri basic*/  
                if( tok.length == 3 ){
                    nameObj = tok[1];
                    nrBasic = Integer.parseInt(tok[2]);
                    Basic elemBsc = new Basic(nameObj,nrBasic);
                    /*cazul cand memoria este goala si adaug un element basic*/
                    if(m.dimensiune()==0)
                        m.add(elemBsc);
                    else {
                    /*atunci cand elem de adaugat nu se afla in memorie*/
                        if(m.searchInMemory(nameObj) == null )
                            m.add(elemBsc);  
                        else{/*atunci cand elem de adaugat se afla in memorie*/
                            m.remove(nameObj);
                            m.add(elemBsc);
                            elemBsc.setNrget(0);    /*pt bonus*/
                            mCache.removeByName(nameObj);
                        }
                    }      
                }
                
                /*adaug in mem. pr. daca obiectul are cereri basic si premium*/
                if( tok.length == 4 ){
                    nameObj = tok[1];
                    nrBasic = Integer.parseInt(tok[2]);
                    nrPremium = Integer.parseInt(tok[3]);
                    //todo ADD - premium
                    Premium elemPrm = new Premium(nrPremium, nrBasic, nameObj);
                    /*memoria este goala*/
                    if(m.dimensiune()==0)
                        m.add(elemPrm); 
                    else {
                        /*elem de adaugat nu se afla in memorie*/
                        if(m.searchInMemory(nameObj) == null )
                            m.add(elemPrm);  
                        else{/*atunci cand elem de adaugat se afla in memorie*/
                            m.remove(nameObj);
                            m.add(elemPrm);
                            elemPrm.setNrget(0);
                            mCache.removeByName(nameObj);
                        }
                    }              
                }

            }
            
            /*inchiderea fisierelor*/
            in.close();
            out.close();
        }
    }             
     catch(FileNotFoundException e){
         System.out.println("Could not find file");
        }     
    }
}
