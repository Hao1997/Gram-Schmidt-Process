import java.util.*;
import java.lang.Math;
import java.util.Scanner;
import java.util.*;

public class Gram {
  public static void main(String[] args){
//     Scanner input = new Scanner(System.in);
//   LinkedList<Double> s = new LinkedList<Double>();
//   boolean more = true;
// while(more){
//          while (input.hasNext()) {
//          Integer[] temp = input.nextInt();
//        }
//      }
  double[][] d = {{1,0,0}, {7,1,2}, {1,4,5}};
    Gram g = new Gram(d);
    d = g.algorith();

    for (int i= 0 ; i<3;i++){
      System.out.println();
      String seperator = " ";
      for(int j=0 ; j<3; j++){
        System.out.print(seperator+(d[i][j]));
        seperator = ", ";
   }
  }
  System.out.println();
}


  private double[][] matrix;
  private int dimension;
  public Gram(double[][] m){
    //check if it is a valid matrix
    Boolean notValid = true;
    dimension = m[0].length;
    for (int i = 1; i<m.length; i++){
      if(m[i].length != dimension){
        notValid = false;
        break;
      }
    }
    if(notValid){
      matrix = m;
    }else {
      dimension  = 0;
      System.out.println("error encountered not a valid span");
    }System.out.println(matrix == null);
  }

  public double[][] algorith(){
    //goes through vectors

    for(int iteration = 1; iteration<matrix.length; iteration++){
      //goes through orthogonal vectors
      double[][] man = new double[matrix.length-iteration+1][3];
      for(int m = 0; m<iteration; m++){
        double multiplyer = (dotProduct(matrix[iteration], matrix[m]))/(dotProduct(matrix[m],matrix[m]));
        //goes through individual x1,x2,x3,x4,x5,x6
        for(int j = 0 ; j<dimension;j++){
           man[m][j]= matrix[m][j] *  multiplyer;
          //System.out.println(multiplyer + " from "+ m + ", "+j+ ", "+iteration); //  not sure if this gives correct result
        }

        }
      int i=0;
      double[] OrthoVector = matrix[iteration];
      while(i<man.length){
        OrthoVector = subtractVectors(OrthoVector, man[i++]);
      }matrix[iteration] = OrthoVector;
    }
    normilize();
    return matrix;
  }


  private double dotProduct(double[] one, double[] two){
    double dotProduct = 0;
    for (int j = 0 ; j<one.length; j++){
      dotProduct = dotProduct + one[j]*two[j];
    }return dotProduct;
  }

  private double[] subtractVectors(double[] v1, double[] v2){
    double[] dif = new double[dimension];
    for(int i = 0 ; i<dimension; i++){
      dif[i] = v1[i] - v2[i];
    }return dif;
  }

  private double[] multiplication(double[] c, double constant){
    for(int i =0 ; i<c.length; i++){
      c[i] = c[i]*constant;
    }return c;
  }

  private void normilize(){
    for(int i = 0; i<matrix.length; i++){
      double constant = 1/Math.sqrt(dotProduct(matrix[i], matrix[i]));
      matrix[i] = multiplication(matrix[i], constant);
    }
  }



}
