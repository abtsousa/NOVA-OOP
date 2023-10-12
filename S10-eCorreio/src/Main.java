/**
 * A simple email client
 *  @author Afonso Brás Sousa (LEI n.º 65263)
 *  @author Alexandre Cristóvão (LEI n.º 65143)
 */

import Exceptions.*;

import java.util.Iterator;
import java.util.Scanner;

public class Main {

    //Messages
    private enum MSG {
        //Error messages
        ERROR_DUPLICATE_MSG("Mensagem duplicada."),
        ERROR_SUBJECT_NOT_FOUND("Nao existem mensagens trocadas com esse assunto."),
        ERROR_EMAIL_NOT_FOUND("Nao existem mensagens trocadas com esse email."),

        //Success messages
        SUCCESS("Mensagem registada."),
        EXIT("A terminar.");

        private final String msg;

        @Override
        public String toString() {
            return msg;
        }

        MSG(String msg) {
            this.msg = msg;
        }
    }

    //Commands
    private enum CMD {
        //Register commands
        SEND_MSG("ENVIAR"), RECEIVE_MSG("RECEBER"),
        //List commands
        LIST_SENT("ENVIADAS"), LIST_RECEIVED("RECEBIDAS"),
        LIST_SUBJECT("ASSUNTO"), LIST_EMAIL("EMAIL"),
        LISTALL_SUBJECTS("ASSUNTOS"),
        //Other
        QUIT("SAIR"), UNKNOWN_COMMAND("");

        private final String input;

        CMD(String input) {
            this.input = input;
        }

        public String getInput() {
            return input;
        }
    }

    public static void main(String[] args) {Main.execute_commands();}

    /**
     * Command interpreter
     */
    private static void execute_commands() {
        Scanner in = new Scanner(System.in);
        eCorreio sp = new eCorreioClass();
        CMD comm = readCommand(in);
        while (!comm.equals(CMD.QUIT)) {
            //switch with all commands from command enum
            switch (comm) {
                case SEND_MSG -> sendMsg(in, sp);
                case RECEIVE_MSG -> receiveMsg(in, sp);
                case LIST_SENT -> listSent(sp);
                case LIST_RECEIVED -> listReceived(sp);
                case LIST_SUBJECT -> listSubject(in, sp);
                case LIST_EMAIL -> listEmail(in, sp);
                case LISTALL_SUBJECTS -> listAllSubjects(sp);
            }
            System.out.println();
            comm = readCommand(in);
        }
        System.out.println(MSG.EXIT);
        System.out.println();
        in.close();
    }

    /**
     * Lê o novo comando a executar.
     * @param in input de onde os dados vao ser lidos.
     */
    private static CMD readCommand(Scanner in) {
        String input = in.nextLine().toUpperCase().trim();
        for (CMD c : CMD.values())
            if (c.getInput().equals(input))
                return c;
        return CMD.UNKNOWN_COMMAND;
    }

    private static void sendMsg(Scanner in, eCorreio sp) {
        String subject = in.nextLine().trim();
        String to = in.nextLine().trim();
        String body = in.nextLine().trim();
        String date = in.nextLine().trim();
        try {
            sp.sendMsg(subject,to,body,date);
            System.out.println(MSG.SUCCESS);
        } catch (DuplicateMsgException e) {
            System.out.println(MSG.ERROR_DUPLICATE_MSG);
        }
    }

    private static void receiveMsg(Scanner in, eCorreio sp) {
        String subject = in.nextLine().trim();
        String from = in.nextLine().trim();
        String body = in.nextLine().trim();
        String date = in.nextLine().trim();
        try {
            sp.receiveMsg(subject,from,body,date);
            System.out.println(MSG.SUCCESS);
        } catch (DuplicateMsgException e) {
            System.out.println(MSG.ERROR_DUPLICATE_MSG);
        }
    }

    private static void listSent(eCorreio sp) {
        Iterator<Email> it = sp.listSentMsgs();
        System.out.println("data | assunto | email");
        while (it.hasNext()) {
            Email mail = it.next();
            System.out.printf("%s | %s | %s\n",mail.getDate(),mail.getSubject(),mail.getMail());
        }
    }

    private static void listReceived(eCorreio sp) {
        Iterator<Email> it = sp.listRcvdMsgs();
        System.out.println("data | assunto | email");
        while (it.hasNext()) {
            Email mail = it.next();
            System.out.printf("%s | %s | %s\n",mail.getDate(),mail.getSubject(),mail.getMail());
        }
    }

    private static void listSubject(Scanner in, eCorreio sp) {
        String subject = in.nextLine().trim();
        try {
            Iterator<Email> it = sp.listBySubject(subject);
            System.out.println("data | assunto | email | texto");
            while (it.hasNext()) {
                Email mail = it.next();
                System.out.printf("%s | %s | %s | %s\n", mail.getDate(), mail.getSubject(), mail.getMail(), mail.getBody());
            }
        } catch (SubjectNotFoundException e) {
            System.out.println(MSG.ERROR_SUBJECT_NOT_FOUND);
        }
    }

    private static void listEmail(Scanner in, eCorreio sp) {
        String email = in.nextLine().trim();
        try {
            Iterator<Email> it = sp.listByEmail(email);
            System.out.println("data | assunto | email | texto");
            while (it.hasNext()) {
                Email mail = it.next();
                System.out.printf("%s | %s | %s | %s\n", mail.getDate(), mail.getSubject(), mail.getMail(), mail.getBody());
            }
        } catch (EmailNotFoundException e) {
            System.out.println(MSG.ERROR_EMAIL_NOT_FOUND);
        }
    }

    private static void listAllSubjects(eCorreio sp) {
        Iterator<String> subjects = sp.listSubjects();
        while (subjects.hasNext()) {
            System.out.println(subjects.next());
        }

    }
}