import java.util.Scanner;

/**
 * A social network
 * @author Afonso Brás Sousa (LEI nº 65263)
 * @author Alexandre Cristóvão (LEI nº 65143)
 */

public class Main {
    //Constantes que definem os comandos
    public static final String CONSULTA_PESSOA = "CONSULTAPESSOA";
    public static final String REGISTA = "REGISTA";
    public static final String CONSULTA_AMIZADE = "CONSULTAAMIZADE";
    public static final String AMIGOS = "AMIGOS";
    public static final String CONSULTA_AMIGOS = "CONSULTAAMIGOS";
    public static final String NOVO_ESTADO = "NOVOESTADO";
    public static final String CONSULTA_ESTADO = "CONSULTAESTADO";
    public static final String PESSOAS = "PESSOAS";

    public static final String POST_PESSOAL = "POSTARPUBLICO";

    public static final String POST_AMIGO = "POSTAL";

    public static final String MURAL = "MURAL";

    public static final String SAIR = "SAIR";

    //Constantes que definem as mensagens para o utilizador
    public static final String USER_EXIST = "Pessoa registada.\n";
    public static final String USER_NOT_EXIST = "Sem registo.\n";
    public static final String USER_REG = "Pessoa registada com sucesso.\n";
    public static final String FRIEND_REG = "Amizade criada.\n";
    public static final String FRIEND_EXIST = "Amizade existente.\n";
    public static final String FRIEND_NOT_EXIST = "Amizade inexistente.\n";
    public static final String FRIEND_NOT_POSSIBLE = "Amizade invalida.\n";
    public static final String FRIEND_LIST = "Lista de amigos:";
    public static final String FRIEND_LIST_NOT_EXIST = "Nao tem amigos registados.\n";
    public static final String STATUS_SET = "Estado alterado.\n";
    public static final String NETWORK_LIST = "Lista de pessoas registadas:";
    public static final String NETWORK_LIST_EMPTY = "Rede Social vazia.\n";

    public static final String POST_REGISTERED = "Post registado.\n";

    public static final String NO_REGISTRATION = "Sem registo.\n";

    public static final String MURAL_OF = "Mural de ";

    public static final String QUIT_MSG = "Adeus.";

    /**
     * Main
     *
     * @param args
     */
    public static void main(String[] args) {
        //Initialize the social network
        SocialNetwork sn = new SocialNetworkClass();

        //Command interpreter
        Scanner in = new Scanner(System.in);
        cmdIntrp(in, sn);

        //Quit message
        System.out.println(QUIT_MSG);
        System.out.println();
        in.close();
    }

    /**
     * Interprets commands
     *
     * @param in scanner input
     */
    private static void cmdIntrp(Scanner in, SocialNetwork sn) {
        String comm;
        do {
            comm = getCommand(in);
            switch (comm) {
                case CONSULTA_PESSOA:
                    verifyUser(in, sn);
                    break;
                case REGISTA:
                    register(in, sn);
                    break;
                case CONSULTA_AMIZADE:
                    verifyFriendship(in, sn);
                    break;
                case AMIGOS:
                    makeFriendship(in, sn);
                    break;
                case CONSULTA_AMIGOS:
                    listFriends(in, sn);
                    break;
                case NOVO_ESTADO:
                    setStatus(in, sn);
                    break;
                case CONSULTA_ESTADO:
                    printStatus(in, sn);
                    break;
                case PESSOAS:
                    listUsers(sn);
                    break;
                case POST_PESSOAL:
                    publicPost(sn);
                    break;
                case POST_AMIGO:
                    friendPost(sn);
                    break;
                case MURAL:
                    listMural(sn);
                    break;
            }
        } while (!comm.equals(SAIR));
    }

    /**
     * Converts user input into a command
     *
     * @param in scanner input
     * @return user command (uppercase)
     */
    private static String getCommand(Scanner in) {
        return in.nextLine().toUpperCase();
    }

    /**
     * Verify if user exists.
     *
     * @param in scanner input
     * @param sn the social network
     */
    private static void verifyUser(Scanner in, SocialNetwork sn) {
        String user = in.nextLine();
        if (sn.hasUser(user)) System.out.println(USER_EXIST);
        else System.out.println(USER_NOT_EXIST);
    }

    /**
     * Register new user.
     *
     * @param in scanner input
     * @param sn the social network
     */
    private static void register(Scanner in, SocialNetwork sn) {
        String name = in.nextLine(); //user's name
        String email = in.nextLine(); //user's email
        String status = in.nextLine(); //user's status
        if (sn.hasUser(name)) System.out.println(USER_EXIST);
        else {
            sn.newUser(name, email, status);
            System.out.println(USER_REG);
        }
    }

    /**
     * Verify friendship between users.
     *
     * @param in scanner input
     * @param sn the social network
     */
    private static void verifyFriendship(Scanner in, SocialNetwork sn) {
        String name1 = in.nextLine(); //first user's name
        String name2 = in.nextLine(); //second user's name
        if (!sn.hasUser(name1) || !sn.hasUser(name2)) System.out.println(FRIEND_NOT_EXIST);
        else {
            if (!sn.areFriends(name1, name2)) System.out.println(FRIEND_NOT_EXIST);
            else {
                System.out.println(FRIEND_EXIST);
            }

        }
    }
    /**
     * Create friendship between users.
     *
     * @param in scanner input
     * @param sn the social network
     */
    private static void makeFriendship(Scanner in, SocialNetwork sn) {
        String name1 = in.nextLine(); //first user's name
        String name2 = in.nextLine(); //second user's name
        if (!sn.hasUser(name1) || !sn.hasUser(name2)) System.out.println(USER_NOT_EXIST);
        else {
            if (sn.areFriends(name1, name2)) System.out.println(FRIEND_EXIST);
            else {
                if (sn.getUser(name1) == sn.getUser(name2)) System.out.println(FRIEND_NOT_POSSIBLE);
                else {
                    sn.registerFriends(name1, name2);
                    System.out.println(FRIEND_REG);
                }
            }
        }
    }

        /**
         * List user's friends.
         *
         * @param in scanner input
         * @param sn the social network
         */
        private static void listFriends(Scanner in, SocialNetwork sn) {
            String name = in.nextLine(); //user's name
            if (!sn.hasUser(name)) System.out.println(USER_NOT_EXIST);
            else {
                if (sn.getUser(name).isEmpty()) System.out.println(FRIEND_LIST_NOT_EXIST);
                else {
                    System.out.println(FRIEND_LIST);
                    sn.getUser(name).listFriends();
                    System.out.println();
                }
            }
        }

        /**
         * Set the user's status.
         *
         * @param in scanner input
         * @param sn the social network
         */
        private static void setStatus(Scanner in, SocialNetwork sn){
            String name = in.nextLine(); //user's name
            String status = in.nextLine(); //user's new status
            if (!sn.hasUser(name)) System.out.println(USER_NOT_EXIST);
            else {
                sn.getUser(name).changeStatus(status);
                System.out.println(STATUS_SET);
            }
        }

        /**
         * Print the user's status.
         *
         * @param in scanner input
         * @param sn the social network
         */
        private static void printStatus (Scanner in, SocialNetwork sn){
            String name = in.nextLine(); //user's name
            if (!sn.hasUser(name)) System.out.print(USER_NOT_EXIST);
            else System.out.println(sn.getUser(name).getStatus());
            System.out.println();
        }

        /**
         * List all users.
         *
         * @param sn the social network
         */
        private static void listUsers (SocialNetwork sn){
            if (sn.isEmpty()) System.out.println(NETWORK_LIST_EMPTY);
            else {
                System.out.println(NETWORK_LIST);
                sn.listUsers();
                System.out.println();
            }
        }
    private static void publicPost (SocialNetwork sn){

    }
    private static void friendPost (SocialNetwork sn){

    }
    private static void listMural (SocialNetwork sn){

    }
}