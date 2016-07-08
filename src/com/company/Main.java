package com.company;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

import java.util.*;

/**
 * Created by professor on 04/07/2016.
 */
public class Main {

    static Scanner sc = new Scanner(System.in);
    static SocialNetwork socialNetwork = new SocialNetwork();

    public static void main(String[] args) {

        initializeSocialNetwork();




       // System.out.println("Query of person by Id:");
       // System.out.println("Id 1: " + socialNetwork.getPersona(1L));

        //System.out.println("Query of person by Name ");
        //System.out.println("Name Cristina: "
         //       + socialNetwork.getPersona("Cristina"));

    }
    private static void initializeSocialNetwork() {
        Persona cristina = new Persona("Cristina", "Duran", 1L);
        Persona juan = new Persona("Juan", "Martínez", 2L);
        Persona ana = new Persona("Ana", "Gómez", 3L);
        Persona marc = new Persona("Marc", "Fuentes", 4L);
        Persona antonio = new Persona("Antonio", "Martínez", 5L);
        Persona pedro = new Persona("Pedro", "González", 6L);
        Persona carol = new Persona("Carol", "Claris", 7L);
        Persona julia = new Persona("Julia", "Paez", 8L);


        socialNetwork.addPersona(cristina);
        socialNetwork.addPersona(juan);
        socialNetwork.addPersona(ana);
        socialNetwork.addPersona(marc);
        socialNetwork.addPersona(antonio);
        socialNetwork.addPersona(pedro);
        socialNetwork.addPersona(julia);
        socialNetwork.addPersona(carol);

        System.out.println("Consultar personas por id: ");
        System.out.println("Id 1: " + socialNetwork.getPersona(1L));

        System.out.println("Consultar persona por nombre: ");
        System.out.println("Nombre Cristina: " + socialNetwork.getPersona("Cristina"));

        socialNetwork.addCouple(cristina, juan);
        socialNetwork.addCouple(pedro, antonio);
        socialNetwork.addCouple(ana, marc);
        //Pedimos que nos de la pareja de una persona
        System.out.println("La pareja de Cristina es: " + socialNetwork.getCouple(cristina));
        System.out.println("La pareja de Juan es: " + socialNetwork.getCouple(juan));

        socialNetwork.addFriendship(cristina, pedro);
        socialNetwork.addFriendship(pedro, julia);
        socialNetwork.addFriendship(julia, carol);
        socialNetwork.addFriendship(julia, ana);
        socialNetwork.addFriendship(ana, antonio);
        socialNetwork.addFriendship(juan, antonio);
        socialNetwork.addFriendship(juan, marc);
        socialNetwork.addFriendship(antonio, marc);
        System.out.println("El amigo de cristina es: "+socialNetwork.getFriends(cristina));

        //amigos de las parejas:
        System.out.println("Los amigos de la pareja de Cristina (juan) son: "+socialNetwork.getCouplesFriends( cristina));
        //parejas de los amigos:
        System.out.println("Las parejas de los amigos de Julia son: " + socialNetwork.getFriendsCouple(julia));
        //Número de amigos de las personas:
        System.out.println("El numero de amigos de Julia es: "+socialNetwork.getNumberOfFriends(julia));


        //Personas ordenadas segun numero de amigos: cal especificar alguna persona en getPopularity(juan p.ej) para que tenga con quien empezar a comparar.
       System.out.println("Muestro las personas ordenadas segun su popularidad: "+socialNetwork.getPopularity(juan));


     //   socialNetwork.addCouplesFriends();


    }

}
