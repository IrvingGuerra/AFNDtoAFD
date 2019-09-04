#include <stdio.h>
#include <stdlib.h>	
#include <iostream>
#include <fstream>
using namespace std;

int main () {
	//Primero copiamos el el AFND al AFD	
	FILE *file1 , *file2;
    int data1 = 0;
    
    file1 = fopen ( "AFND.txt", "r" ); //Abrimos en NO determinista
    file2 = fopen ( "AFD.txt" , "w" ); //Creamos el DETERMINISTA
    
    while ( (data1 = fgetc ( file1 )) != EOF ) {
        fputc ( data1, file2 );
    }
    
    fclose ( file1 ); //Cerramos el NO determinista
	
	//Ahora seguimos leyendo los casos del AFND para agregarlos al AFD
	
	file1 = fopen ( "AFND.txt", "r" ); //Abrimos de nuevo el NO determinista
	
	char letra;

	int aux0or1 = 0; //auxiliar que nos dice si estara leyendo el 0 o 1
	
	while ( (letra = fgetc ( file1 )) != EOF ) {
       switch(letra){
			case '-': //Siguiente q
			
			break;
			case '{':
				//Empieza el primer estado que debemos de crear
			break;
			case '}':
				
			break;
		}
    }
    
    fclose ( file1 );
    fclose ( file2 );
	
 return 0;
}

