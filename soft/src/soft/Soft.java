package soft;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

/**
 *
 * @author Irving-Guerra
 */
public class Soft {

    static String[] arrayEstados = new String[99];
    static String[] arrayCuando0 = new String[99];
    static String[] arrayCuando1 = new String[99];
    
    static int q = 0;

    static char letra;
    
    public static void main(String[] args) {
        try {
            FileInputStream fis = new FileInputStream("AFND.txt");
            while (fis.available() > 0) {
                    letra = (char) fis.read();
                    switch(letra){
                        case '-': //Siguiente q
                                q++;
                        break;
                        case '{':
                                //Empieza el primer estado que debemos de crear
                                letra = (char) fis.read(); //contiene la letra
                                arrayEstados[q] = String.valueOf(letra);
                        break;
                        case ',':
                                //Es un estado con mas de 1
                                letra = (char) fis.read(); //contiene la letra
                                arrayEstados[q] = arrayEstados[q] + "," + letra;
                        break;
                        case '}':
                                //Empezara a ver los estados
                                letra = (char) fis.read(); //contiene la letra //contiene | No nos interesa
                                letra = (char) fis.read(); //contiene la letra //Contiene { de el caso 0
                                while ( (letra = (char) fis.read()) != '}' ) {
                                        switch(letra){
                                                case ',':
                                                        //Es un estado con mas de 1
                                                        letra = (char) fis.read(); //contiene la letra
                                                        arrayCuando0[q] += "," + letra;
                                                break;
                                                default:
                                                        arrayCuando0[q] = String.valueOf(letra);
                                        }
                                }
                                letra = (char) fis.read();  //contiene | No nos interesa
                                letra = (char) fis.read();   //Contiene { de el caso 1
                                while ( (letra = (char) fis.read()) != '}' ) {
                                        switch(letra){
                                                case ',':
                                                        //Es un estado con mas de 1
                                                        letra = (char) fis.read(); //contiene la letra
                                                        arrayCuando1[q] += "," + letra;
                                                break;
                                                default:
                                                        arrayCuando1[q] = String.valueOf(letra);
                                        }
                                }
                        break;
                    }
            }
            fis.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        
                //Copiamos directamente el archivo
        try {
            FileInputStream fis = new FileInputStream("AFND.txt");
            FileOutputStream fos = new FileOutputStream("AFD.txt");
            while (fis.available() > 0) {
                    letra = (char) fis.read();
                    fos.write(letra);
            }
            fis.close();
            //Verificaremos si los estados en 0 o 1 estan en nuestro original

            //Convertimos el array en lista

            List<String> list = Arrays.asList(arrayEstados);
            
            int aux = q;
            
            for(int i = 0 ; i < aux ; i++){
                if(list.contains(arrayCuando0[i])){
                    System.out.println("Lo tiene");
                }else{
                    if(!arrayCuando0[i].equals("0")){
                        //Si no es 0 agrega el nuevo estado
                        arrayEstados[q] = arrayCuando0[i];
                        System.out.println(arrayEstados[q]);
                        //y aparte añadimos sus 0 y 1
                        String[] estados = arrayCuando0[i].split(",");
                        arrayCuando0[q] = "";
                        for(int j = 0 ; j<estados.length ; j++){
                            int index = list.indexOf(estados[j]);
                            if(!arrayCuando0[index].equals("0")){
                                arrayCuando0[q] += arrayCuando0[index]+",";
                            }
                        }
                        arrayCuando1[q] = "";
                        for(int j = 0 ; j<estados.length ; j++){
                            int index = list.indexOf(estados[j]);
                            if(!arrayCuando1[index].equals("0")){
                                arrayCuando1[q] += arrayCuando1[index]+",";
                            }
                        }
                        
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);
                      
                        //Eliminamos todas las comas
                        
                        arrayCuando0[q] = arrayCuando0[q].replaceAll(",", "");
                        arrayCuando1[q] = arrayCuando1[q].replaceAll(",", "");
                        
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);
                        
                        //Convertimos en arreglo
                        
                        String[] letras0 = arrayCuando0[q].split("");
                        Arrays.sort(letras0);
                        String[] letras1 = arrayCuando1[q].split("");
                        Arrays.sort(letras1);
                        
                        String nueva0 = "";
                        for(int j = 0 ; j<letras0.length ; j++){
                            nueva0 += letras0[j];
                        }
                        String nueva1 = "";
                        for(int j = 0 ; j<letras1.length ; j++){
                            nueva1 += letras1[j];
                        }
                        
                        System.out.println(nueva0);
                        System.out.println(nueva1);
                        
                        //Eliminamos repetidos
                        
                        nueva0 = nueva0.replaceAll("(.)\\1", "$1");
                        nueva1 = nueva1.replaceAll("(.)\\1", "$1");
                        
                        //Agregamos comas entre caracteres
                        
                        letras0 = nueva0.split("");
                        
                        letras1 = nueva1.split("");
                        
                        String nuevaC0 = "";
                        for(int j = 0 ; j<letras0.length ; j++){
                            nuevaC0 += letras0[j]+",";
                        }
                        String nuevaC1 = "";
                        for(int j = 0 ; j<letras1.length ; j++){
                            nuevaC1 += letras1[j]+",";
                        }
       
          
                        nuevaC0 = nuevaC0.substring(0, nuevaC0.length()-1); 
                        nuevaC1 = nuevaC1.substring(0, nuevaC1.length()-1); 
                        
                        arrayCuando0[q] = nuevaC0;
                        arrayCuando1[q] = nuevaC1;
                        
                        
                        aux++;
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);
                       
                        String cadena = "{"+ arrayEstados[q]+"}|{"+arrayCuando0[q]+"}|{"+arrayCuando1[q]+"}-\n";
                        fos.write(cadena.getBytes());
                    }
                }
                if(list.contains(arrayCuando1[i])){
                    System.out.println("Lo tiene");
                }else{
                    if(!arrayCuando1[i].equals("0")){
                        //Si no es 0 agrega el nuevo estado
                        arrayEstados[q] = arrayCuando1[i];
                        System.out.println(arrayEstados[q]);
                        //y aparte añadimos sus 0 y 1
                        String[] estados = arrayCuando1[i].split(",");
                        arrayCuando0[q] = "";
                        for(int j = 0 ; j<estados.length ; j++){
                            int index = list.indexOf(estados[j]);
                            if(!arrayCuando0[index].equals("0")){
                                arrayCuando0[q] += arrayCuando0[index]+",";
                            }
                        }
                        arrayCuando1[q] = "";
                        for(int j = 0 ; j<estados.length ; j++){
                            int index = list.indexOf(estados[j]);
                            if(!arrayCuando1[index].equals("0")){
                                arrayCuando1[q] += arrayCuando1[index]+",";
                            }
                        }
                        
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);
                      
                        //Eliminamos todas las comas
                        
                        arrayCuando0[q] = arrayCuando0[q].replaceAll(",", "");
                        arrayCuando1[q] = arrayCuando1[q].replaceAll(",", "");
                        
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);
                        
                        //Convertimos en arreglo
                        
                        String[] letras0 = arrayCuando0[q].split("");
                        Arrays.sort(letras0);
                        String[] letras1 = arrayCuando1[q].split("");
                        Arrays.sort(letras1);
                        
                        String nueva0 = "";
                        for(int j = 0 ; j<letras0.length ; j++){
                            nueva0 += letras0[j];
                        }
                        String nueva1 = "";
                        for(int j = 0 ; j<letras1.length ; j++){
                            nueva1 += letras1[j];
                        }
                        
                        System.out.println(nueva0);
                        System.out.println(nueva1);
                        
                        //Eliminamos repetidos
                        
                        nueva0 = nueva0.replaceAll("(.)\\1", "$1");
                        nueva1 = nueva1.replaceAll("(.)\\1", "$1");
                        
                        //Agregamos comas entre caracteres
                        
                        letras0 = nueva0.split("");
                        
                        letras1 = nueva1.split("");
                        
                        String nuevaC0 = "";
                        for(int j = 0 ; j<letras0.length ; j++){
                            nuevaC0 += letras0[j]+",";
                        }
                        String nuevaC1 = "";
                        for(int j = 0 ; j<letras1.length ; j++){
                            nuevaC1 += letras1[j]+",";
                        }
       
          
                        nuevaC0 = nuevaC0.substring(0, nuevaC0.length()-1); 
                        nuevaC1 = nuevaC1.substring(0, nuevaC1.length()-1); 
                        
                        arrayCuando0[q] = nuevaC0;
                        arrayCuando1[q] = nuevaC1;
                        
                        aux++;
                        System.out.println(arrayCuando0[q]);
                        System.out.println(arrayCuando1[q]);

                        String cadena = "{"+ arrayEstados[q]+"}|{"+arrayCuando0[q]+"}|{"+arrayCuando1[q]+"}-\n";
                        fos.write(cadena.getBytes());
                    }
                }
            }

            fos.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
        
        
            
        
        
    }
    
}
