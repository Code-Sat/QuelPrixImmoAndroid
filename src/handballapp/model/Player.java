/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package handballapp.model;

/**
 *
 * @author Peio
 */
public class Player {
    private String number;
    private String lastName;
   // private String age;
    private String post;
    //private String team;
    
    //------- CONSTRUCTEUR --------//
    public Player(String number, String lastName,/* String age, String team, */String post){
        this.number = number;
        this.lastName = lastName;
        //this.age = age;
        //this.team = team;
        this.post = post;
    }
    
    //-------ACCESSEUR -------//
    public String getNumber(){
        return number; 
    }
    
    public void setNumber(String pNumber){
        this.number = pNumber; 
    }
    
        public String getLastName(){
       return lastName; 
    }
    
    public void setLastName(String pLastName){
        this.lastName = pLastName; 
    }
    
 /*   public String getAge(){
       return age; 
    }
    
    public void setAge(String pAge){
        this.age = pAge; 
    }*/
        
    public String getPost(){
       return post; 
    }
    
    public void setPost(String post){
        this.post = post; 
    }

  /*  public String getTeam(){
       return team; 
    }
    
    public void setTeam(String pTeam){
        this.team = pTeam; 
    }*/
}
