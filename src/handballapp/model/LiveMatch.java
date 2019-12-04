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
public class LiveMatch {
    private static int nbrDeMatch; //affichera le nb de match Live en cours 
    private String team1;
    private String team2;
    private String lieu;
    private String ligue; 
    private int team1pts;
    private int team2pts;
  //  private Date tempsDeJeu;

    public LiveMatch(){
        System.out.println("Live Match créé.");
        this.team1pts = 0;
        this.team2pts = 0;
    }
    
    // ---------- CONSTRUCTEUR ----------- //
    public LiveMatch(String pTeam1, String pTeam2, String pLieu, String pligue, int pTeam1pts, int pTeam2pts){
        this.team1 = pTeam1;
        this.team2 = pTeam2;
        this.lieu = pLieu;
        this.ligue = pligue;
        this.team1pts = pTeam1pts;
        this.team2pts = pTeam2pts;
        this.nbrDeMatch++; //décrémenter au destructeur
    }
    
   /* public CLiveMatch(String pTeam1, String pTeam2, String pLieu, String pligue){
        team1 = pTeam1;
        team2 = pTeam2;
        lieu = pLieu;
        ligue = pligue;
        CLiveMatch(String pTeam1, String pTeam2, String pLieu, String pligue, 0, 0);
    }
    //https://www.developpez.net/forums/d354762/java/general-java/langage/passage-parametres-optionnels/
    */
          
  //*************   ACCESSEURS ************* //
    
  public String getTeam1()  {  
    return team1;
  }

  public String getTeam2()
  {
    return team2;
  }
  
  public String getLieu()
  {
    return lieu;
  }
  
  public String getLigue()
  {
    return ligue;
  }
  
  public int getTeam1pts()
  {
    return team1pts;
  }
  
    public int getTeam2pts()
  {
    return team2pts;
  }
    
    //*************   MUTATEURS   ************* //
    
  public void setTeam1(String pTeam1)  {  
     team1 = pTeam1;
  }

  public void setTeam2(String pTeam2)
  {
     team2 = pTeam2;
  }
  
  public void setLieu(String pLieu)
  {
     lieu = pLieu;
  }
  
  public void setLigue( String pLigue)
  {
     ligue = pLigue;
  }
  
  public void setTeam1pts(int pTeam1pts)
  {
     team1pts = pTeam1pts;
  }
  
   public void setTeam2pts(int pTeam2pts)
  {
     team2pts = pTeam2pts;
  }
}
