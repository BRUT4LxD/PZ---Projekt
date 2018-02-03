package Database;

import Exceptions.PlayerAlreadyExists;
import Exceptions.PlayerNotFound;
import Misc.Utils;

import javax.persistence.*;
import java.util.List;

public final class Queries {

    public static EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("PZ");

    public static Player checkLogIn(String name, String password) throws PlayerNotFound {


        EntityManager em = entityManagerFactory.createEntityManager();


        try{
            em.getTransaction().begin();
            List<Player> players =em.createQuery("SELECT p FROM Player p WHERE p.nick =:name AND p.password =:password", Player.class)
                    .setParameter("name", name)
                    .setParameter("password",password)
                    .getResultList();

            em.close();
            if(players.isEmpty()) throw new PlayerNotFound();

            return players.get(0);
        }catch (NoResultException e){
            System.out.println("Nie znaleziono gracza!");
            throw new PlayerNotFound();
        }


    }
    public static boolean checkRegistration(String name) throws PlayerAlreadyExists {
        EntityManager em = entityManagerFactory.createEntityManager();

        try{
            em.getTransaction().begin();
            Player player =em.createQuery("SELECT p FROM Player p WHERE p.nick =:name", Player.class)
                    .setParameter("name", name)
                    .getSingleResult();

            System.out.println("Gracz juz istnieje!");
            em.close();
            throw new PlayerAlreadyExists();

        }catch (NoResultException e){
            System.out.println(" No results");
            em.close();
            return true;
        }

    }
    public static void addPlayer(String name, String password) {
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.persist(new Player(name,password));

        em.getTransaction().commit();

        em.close();

    }
    public static void addMatch(Player player){
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        Match newMatch = Utils.match;
        newMatch.setPlayer(player);
        em.persist(newMatch);

        em.getTransaction().commit();

        em.close();
    }
    public static void clearDatabase(){
        EntityManager em = entityManagerFactory.createEntityManager();

        em.getTransaction().begin();

        em.createQuery("DELETE FROM Player").executeUpdate();
        em.createQuery("DELETE FROM Match").executeUpdate();

        em.getTransaction().commit();
        em.close();


    }
    public static void printDatabase() {

        try{

            EntityManager em = entityManagerFactory.createEntityManager();

            em.getTransaction().begin();

            List<Match> matches = em.createQuery("SELECT m FROM Match m", Match.class).getResultList();
            List<Player> players = em.createQuery("SELECT p FROM Player p", Player.class).getResultList();
            em.getTransaction().commit();

            for( Player p: players){
                System.out.println(p.toString());
            }
            for( Match p: matches){
                System.out.println(p.toString());
            }
            em.close();
        }catch (NoResultException e){
            System.out.println(" No results");
        }
    }

    public static void deleteByName(String name){
        EntityManager em = entityManagerFactory.createEntityManager();


        em.getTransaction().begin();

        try{
            em.createQuery("DELETE FROM Player p WHERE p.nick =:name", Player.class).setParameter("name", name);
        }
        catch(NoResultException e){
            System.out.println(" No results");
        }

        em.getTransaction().commit();
        em.close();
    }
}
