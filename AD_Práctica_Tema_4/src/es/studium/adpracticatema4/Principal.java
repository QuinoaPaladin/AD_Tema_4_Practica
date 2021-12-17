package es.studium.adpracticatema4;

import static com.mongodb.client.model.Filters.and;
import static com.mongodb.client.model.Filters.eq;
import static com.mongodb.client.model.Filters.in;
import static com.mongodb.client.model.Filters.lte;

import java.util.Scanner;

import org.bson.Document;

import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;

public class Principal
{

	static int numero;

	public static void main(String[] args)
	{
		
		MongoClient conexion = MongoClients.create("mongodb://localhost:27017");
		MongoDatabase database = conexion.getDatabase("harry");
		MongoCollection<Document> characters = database.getCollection("characters");
		
		System.out.println( "Introduzca un número del 1 al 4");
		Scanner escrito = new Scanner( System.in );
		numero = escrito.nextInt();
		
	
		if(numero==1)
		{
			consultarHumanos(characters);
		}
		if(numero==2)
		{
			consultarNacidos(characters);
		}
		if(numero==3)
		{
			consultarVaritas(characters);
		}
		if(numero==4)
		{
			consultarEstudiantesVivos(characters);
		}
		escrito.close();

	}

	public static void consultarHumanos(MongoCollection<Document> characters)
	{
		FindIterable<Document> buscarHumanos = characters.find(eq("species", "human"));		
		System.out.println("Personajes humanos:");
		
		for (Object character : buscarHumanos) 
		{
			System.out.println(((Document) character).toJson());
		}
	}
	
	public static void consultarNacidos(MongoCollection<Document> characters)
	{
		FindIterable<Document> busquedaAntes1979 = characters.find(lte("yearOfBirth", 1979));
		System.out.println("Personajes nacidos antes de 1979:");
		
		for (Object character : busquedaAntes1979) 
		{
			System.out.println(((Document) character).toJson());
		}
	}
	public static void consultarVaritas(MongoCollection<Document> characters)
	{
		FindIterable<Document> busquedaVarita1 = characters.find(in("wand.wood","holly"));  
		System.out.println("Personajes con varitas de madera de acebo:");
		
		for (Object character : busquedaVarita1) 
		{
			System.out.println(((Document) character).toJson());
		}
	}
	public static void consultarEstudiantesVivos(MongoCollection<Document> characters)
	{ 
		FindIterable<Document> busquedaEstudiantesVivos = characters.find(and(eq("alive", true), eq("hogwartsStudent", true)));
		System.out.println("Estudiantes vivos");
		
		for (Object character : busquedaEstudiantesVivos) 
		{
			System.out.println(((Document) character).toJson());
		}
	}

}
