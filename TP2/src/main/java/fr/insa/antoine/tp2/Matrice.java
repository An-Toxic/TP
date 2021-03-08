/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.insa.antoine.tp2;

/**
 *
 * @author aabboud01
 */
public class Matrice {
     int nbrLig , nbrCol;
    double[][] coeffs;
    //attribut
    public Matrice(int ligne , int colonne ) 
    {
        //constructor
        //we can remove the "this"
        this.nbrLig= ligne ;
        this.nbrCol= colonne ;
        this.coeffs = new double [nbrLig][nbrCol];
        for(int i = 0 ; i<nbrLig ; i++)
        {
            for (int j=0 ; j<nbrCol ; j++)
            {
                coeffs[i][j] = 0 ;
            }
        }
    }
    public static Matrice identite(int n)
    {
        Matrice nouv = new Matrice(n,n);
        for(int i = 0 ; i<n ; i++)
        {
            for (int j=0 ; j<n ; j++)
            {
                if (i==j)
                {
                    nouv.coeffs[i][j]= 1 ;
                }
                else 
                {
                    nouv.coeffs[i][j] = 0 ;
                }
            }
            
        }
        return nouv ;
    }
   

    public static Matrice matTest1(int n){
    Matrice m1 = new Matrice (n,n) ;
    int k =0 ;
    for (int i=0 ;i<n ;i++){
        for (int j=0 ; j<n ; j++){
        m1.coeffs[i][j]= k ;
        k=k+1 ;
        }
    }
    return m1 ;
}
    public static Matrice matTest2(int n){
        Matrice m2 = new Matrice (n,n);
        int f = 0 ;
        for (int i=0 ; i<n ;i++){
            for (int j=0 ; j<n ; j++){
                if (i==j){
                    m2.coeffs[i][j]=-f++;
                }
                else {
                    m2.coeffs[i][j]=f++;
                }
            }
        }
                return m2 ;
                        
                }
    public static int aleaUnouDeux() {
        double x = Math.random();
        if (x<= 0.5){
            return 1 ;
        }
        else {
            return 2 ;
        }
    }
    public static Matrice matAleaZeroUnDeux (int nl,int nc ,double p) {
        Matrice m3 = new Matrice (nl , nc);
        for (int i=0 ;i<nl ;i++){
            for (int j =0 ; j< nc ; j++){
        if (Math.random()<= p) {
            m3.coeffs[i][j]= 0 ;
        }
            else {
                    m3.coeffs[i][j]= aleaUnouDeux() ;
                    }
        }
    }
        return m3 ;
            }
    public Matrice creeVecteur (double[] tab){
        Matrice m4 = new Matrice (tab.length, 1);
        for (int i=0 ;i<tab.length ; i++){
            m4.coeffs[i][1]= tab[i];
        }
        return m4 ;
     
    }
    public double getcoeffs(int i , int j){
        return this.coeffs[i][j];
    } 
    public void set (int i , int j , double x){
        this.coeffs[i][j]= x ;
        System.out.println(this.coeffs[i][j]);
    }
  
    public static void test1(){
        Matrice m8 = new Matrice(4,6).matAleaZeroUnDeux(4, 6, 0.33);
        for (int i = 0 ;i<4 ; i++){
            for (int j = 0 ; j<6 ; j++){
            System.out.print(m8.coeffs[i][j]+" ");
            }
            System.out.println(" ");
        }
    }
    //affichage de Matrice sous forme de String
    public String toString(){
        String s ;
        s="";
        for (int i = 0; i < this.nbrLig; i++) {
            s=s+"[";
            for (int j = 0; j < this.nbrCol; j++) {
                s=s+this.coeffs[i][j];
                if(j!=this.nbrCol -1){
                s=s+" " ;
            }
            }
            s=s+"]\n";
        }
        return s ;
    }
    public Matrice concatLig(Matrice M , Matrice N ){
        if(M.nbrCol==N.nbrCol){
        Matrice R = new Matrice (M.nbrLig + N.nbrLig, M.nbrCol);
        for (int i = 0 ; i<R.nbrLig;i++){
            for (int j = 0 ; j<R.nbrCol; j++){
        
            if (i<M.nbrLig){
                R.coeffs[i][j]=M.coeffs[i][j];
            }
            else{
                R.coeffs[i][j]=N.coeffs[i-M.nbrLig][j];
            }
        }
        }
        return R ;
        }
        else {
            throw new Error ("Matrices non compatibles");
        } 
    }
   
    public Matrice concatCol (Matrice M , Matrice N){
          if(M.nbrLig==N.nbrLig){
        Matrice R = new Matrice (M.nbrLig, M.nbrCol+ N.nbrCol);
        for (int i = 0 ; i<R.nbrLig;i++){
            for (int j = 0 ; j<R.nbrCol; j++){
        
            if (j<M.nbrCol){
                R.coeffs[i][j]=M.coeffs[i][j];
            }
            else{
                R.coeffs[i][j]=N.coeffs[i][j-M.nbrCol];
    }
            }
        }
          return R ;
          }
          else {
              throw new Error ("Matrice non compatibles ");
          }
    }
    
    public Matrice subLignes (Matrice M , int nMin , int nMax) {
        if (nMin<=nMax && nMax<=M.nbrLig){
            Matrice R = new Matrice (nMax-nMin+1 , M.nbrCol);
            for (int i = 0; i < R.nbrLig; i++) {
                for(int j = 0 ; j< R.nbrCol; j++){
                    R.coeffs[i][j]=M.coeffs[nMin+i][j] ;
                }
            }
            return R ;
            }
        else {
            throw new Error ("Erreur de valeurs des entiers ");
        }
    }
    public Matrice subCols ( int nMin , int nMax ){
         if (nMin<=nMax && nMax<=this.nbrCol){
            Matrice R = new Matrice ( this.nbrLig, nMax-nMin+1);
            for (int i = 0; i < R.nbrLig; i++) {
                for(int j = 0 ; j< R.nbrCol; j++){
                    R.coeffs[i][j]=this.coeffs[i][nMin+j] ;
                }
            }
            return R ;
            }
        else {
            throw new Error ("Erreur de valeurs des entiers ");
        }
    }
    public Matrice transposee (){
        Matrice T = new Matrice (this.nbrCol,this.nbrLig);
        for (int i = 0 ; i<T.nbrLig;i++){
            for (int j =0 ; j<T.nbrCol ; j++){
                T.coeffs[i][j]= this.coeffs[j][i];
            }
        }
        return T ;
    }
    public Matrice metAucarre (){
        Matrice idn = identite(this.nbrLig);
        Matrice idc = identite (this.nbrCol);
        Matrice Mt= this.transposee() ;
        Matrice R ;
        //new Matrice(this.nbrCol+this.nbrLig , this.nbrCol+ this.nbrLig);
        Matrice R1;
        Matrice R2;
        R1 = concatLig(this , idn);
        R2 = concatLig(idc,Mt);
        R=concatCol(R1 , R2);
        return R ;
        
    }
    public static  Matrice addition(Matrice m1, Matrice m2){
        if(m1.nbrCol==m2.nbrCol&& m1.nbrLig==m2.nbrCol){
        
        Matrice m = new Matrice (m1.nbrLig , m1.nbrCol) ;
        for (int i = 0; i < m.nbrLig; i++) {
            for (int j = 0; j < m.nbrCol; j++) {
                m.coeffs[i][j]=m1.coeffs[i][j]+m2.coeffs[i][j]; 
            }   
        }
        return m ;
        }
        else {
            throw new Error("incompatibles");
        }
    }
    public int permuteLigne(int a,int b){
        if(a<=this.nbrLig && b<= this.nbrLig){
             
        double c ;
        int k=0;
        for (int j = 0; j < this.nbrCol; j++) {
            if(this.coeffs[a][j]==this.coeffs[b][j]){
                k=k+1;
            }
            c=this.coeffs[b][j];
            this.coeffs[b][j]=this.coeffs[a][j];
            this.coeffs[a][j]=c ;
            
        }
        if (k==this.nbrCol){
            return 1 ;
            
        }
        else {
            return -1; 
               
        }
    }
        else {
            throw new Error("erreur de ligne");
        }
    }
    //changeeeee
    
    }
    

