#include <stdio.h>
#include <stdlib.h>	
#include <iostream>
#include <fstream>
#include <string.h> 

using namespace std;
	  
    string arrayEstados[99];
    string arrayCuando0[99];
    string arrayCuando1[99]; //|  ---
    
    bool esQ(int indice);
    bool valueInArray(string val, string arr[]);

int main () {
	//Primero copiamos el el AFND al AFD
	FILE *file1 , *file2;
    int data1 = 0;
    int q = 0;

    file1 = fopen ( "AFND.txt", "r" ); //Abrimos en NO determinista
    file2 = fopen ( "AFD.txt" , "w" ); //Creamos el DETERMINISTA
    
    while ( (data1 = fgetc ( file1 )) != EOF ) {
        fputc ( data1, file2 );
    	switch(data1){
			case '-': //Siguiente q
				q++;
			break;
			case '{':
				//Empieza el primer estado que debemos de crear
				data1 = fgetc ( file1 ); //contiene la letra
				arrayEstados[q] = data1;
			break;
			case ',':
				//Es un estado con mas de 1
				data1 = fgetc ( file1 ); //contiene la letra
				strcat(original,concat); 
				arrayEstados[q] = arrayEstados[q] + "," + data1;
			break;
			case '}':
				//Empezara a ver los estados
				data1 = fgetc ( file1 ); //contiene | No nos interesa
				data1 = fgetc ( file1 );  //Contiene { de el caso 0
				while ( (data1 = fgetc ( file1 )) != '}' ) {
				 	switch(data1){
				 		case ',':
							//Es un estado con mas de 1
							data1 = fgetc ( file1 ); //contiene la letra
							strcat(arrayCuando0[q], ",");
							strcat(arrayCuando0[q], data1);
						break;
						default:
							arrayCuando0[q] = data1;
					}
				}
				data1 = fgetc ( file1 ); //contiene | No nos interesa
				data1 = fgetc ( file1 );  //Contiene { de el caso 1
				while ( (data1 = fgetc ( file1 )) != '}' ) {
				 	switch(data1){
				 		case ',':
							//Es un estado con mas de 1
							data1 = fgetc ( file1 ); //contiene la letra
							strcat(arrayCuando1[q], ",");
							strcat(arrayCuando1[q], data1);
						break;
						default:
							arrayCuando1[q]= data1;
					}
				}
			break;
		}
    }
    
    fclose ( file1 ); //Cerramos el NO determinista
    
    for(int i=0 ; i < q ; i++){
    	//Preguntamos si el primer caso en 0 ya esta considerado en los casos normales
    	if(esQ(i)){
    		
		}
	}
   	
	
	//Ahora seguimos leyendo los casos del AFND para agregarlos al AFD
	
	file1 = fopen ( "AFND.txt", "r" ); //Abrimos de nuevo el NO determinista
	

    fclose ( file1 );
    fclose ( file2 );
	
 return 0;

 
}


 bool esQ(int indice){
 	
 	
 }
 
 int valueinarray(float val, float arr[])
{
    int i;
    for(i = 0; i < sizeof(arr) / sizeof(arr[0]); i++)
    {
        if(arr[i] == val)
            return 1;
    }
    return 0;
}
 
 
 


