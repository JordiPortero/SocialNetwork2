package com.company;

import com.google.common.collect.*;
import com.sun.deploy.util.OrderedHashSet;

import java.util.*;

/**
 * Created by professor on 04/07/2016.
 */
public class SocialNetwork {

    private Map<String, Persona> peopleByName = new HashMap<>();
    private Map<Long, Persona> peopleById = new HashMap<>();
    private static BiMap<Persona, Persona> couples = HashBiMap.create();
    private TreeMultimap<Persona, Persona> multimapFriends = TreeMultimap.create();
    private TreeMultimap<Persona, Persona> multimapCouplesFriends = TreeMultimap.create();
    private TreeMultimap<Persona, Persona> multimapFriendsCouple = TreeMultimap.create();

    public void addPersona(Persona persona) {

        peopleByName.put(persona.getName(), persona);
        peopleById.put(persona.getId(), persona);
    }

    public Persona getPersona(Long id) {
        return peopleById.get(id);
    }

    public Persona getPersona(String name) {
        return peopleByName.get(name);
    }

    public void addCouple(Persona persona1, Persona persona2) {

        checkIfCouple(persona1);
        checkIfCouple(persona2);


        couples.put(persona1, persona2);
    }

    private void checkIfCouple(Persona persona1) {
        if (couples.containsKey(persona1)
                || couples.values().contains(persona1)) {
            System.out.println("La persona: " + persona1.getName() + " ya tiene pareja");
            throw new RuntimeException("La persona " + persona1.getName() + " ya tiene pareja");
            //runtimeexception rompe el flujo de ejecucion i el codigo vuelve al metodo, no sigue ejectuando a continuacion
        }
    }

    public Persona getCouple(Persona persona) {
        Persona couple = couples.get(persona);
        if (couple != null) {
            return couple;
        } else {
            couple = couples.inverse().get(persona);
            return couple;
        }


    }

    public void addFriendship(Persona persona, Persona anotherPerson) {

        if (multimapFriends.containsKey(persona) && multimapFriends.get(persona).contains(anotherPerson)) {
            System.out.println(anotherPerson.getName() + "ya es amigo de " + persona.getName());
        } else {
            multimapFriends.put(persona, anotherPerson);
            multimapFriends.put(anotherPerson, persona);
        }
    }


    public Set<Persona> getFriends(Persona persona) {

        //set y no list para que no haya amigos duplicados

        return multimapFriends.get(persona);

    }


    public Set<Persona> getCouplesFriends(Persona persona) {

        Persona couple = getCouple(persona);

        if (couple == null) {
            System.out.println("La persona " + persona.getName() + " no tiene pareja.");
            return new TreeSet<Persona>();
        } else {
            return getFriends(couple);
        }

    }

    public Set<Persona> getFriendsCouple(Persona persona) {
        //Le pido k use los amigos de la persona k le he entrado.Si no tiene amigos, ya no entra en bucle for.
        Set<Persona> friends = getFriends(persona);
        //creo un hashset vacio para ir metiendo las parejas de los amigos.
        Set<Persona> couples = new HashSet<>();
        //Me guarda en friend a todos los friends k tiene la persona k le entro.
        for (Persona friend : friends) {
            Persona couple = getCouple(friend);

            if (couple != null) {//si couple no es null, k se añada a la lista de couples k he creado.
                couples.add(couple);
            }

        }
        return couples;

    }

    public Integer getNumberOfFriends(Persona persona) {
        return getFriends(persona).size();
    }

    public List<Persona> getPopularity(Persona persona) {

        List<Persona> personaList = new ArrayList<>(peopleByName.values());//guardamos el map de personas en una arraylist
        Collections.sort(personaList, new Comparator<Persona>() {
            @Override
            public int compare(Persona persona1, Persona persona2) {
                int numFriendsPersona1 = getNumberOfFriends(persona1);
                int numFriendsPersona2 = getNumberOfFriends(persona2);
                if (numFriendsPersona1 < numFriendsPersona2) {
                    //siempre se asigna a return 1 cuando ponemos que la primera es mas peke que la segunda.
                    return 1;
                }
                if (numFriendsPersona1 > numFriendsPersona2) {
                    return -1;
                } else return 0;
            }
        });

        return personaList;
    }

}





/**

// public  Set<Persona> getFriendsCouple (Persona persona){return null;}
 //public  Set<Persona> getPopularity (Persona persona){return null;}
 public  int getConnectionDegree (Persona persona1, Persona persona2){return 0;}
 public SortedSet<Persona> getConnectionDegreePath (Persona persona1, Persona persona2){return null;}*/