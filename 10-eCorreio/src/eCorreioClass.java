import Exceptions.*;

import java.util.*;

public class eCorreioClass implements eCorreio {

    // comando EMAIL
    private Map<String, List<Email>> msgs; // String = email
    // comando ENVIADAS
    private SortedSet<Email> sentMsgs;
    // comando RECEBIDAS
    private SortedSet<Email> rcvdMsgs;
    // comando ASSUNTO, ASSUNTOS
    private SortedMap<String, List<Email>> msgBySubject; // String = subject

    public eCorreioClass() {
        msgs = new HashMap<>();
        sentMsgs = new TreeSet<>();
        rcvdMsgs = new TreeSet<>();
        msgBySubject = new TreeMap<>();
    }

    //ENVIAR command
    @Override
    public void sendMsg(String subject, String to, String body, String date) throws DuplicateMsgException {
        Email email = new EmailClass(subject,to,body,date);
        if (sentMsgs.contains(email)) throw new DuplicateMsgException();

        sentMsgs.add(email);
        addToMap(msgs,to,email);
        addToMap(msgBySubject,subject,email);
    }

    //RECEBER command
    @Override
    public void receiveMsg(String subject, String from, String body, String date) throws DuplicateMsgException {
        Email email = new EmailClass(subject,from,body,date);
        if (rcvdMsgs.contains(email)) throw new DuplicateMsgException();

        rcvdMsgs.add(email);
        addToMap(msgs,from,email);
        addToMap(msgBySubject,subject,email);
    }

    //ENVIADAS command
    @Override
    public Iterator<Email> listSentMsgs() {
        return sentMsgs.iterator();
    }

    //RECEBIDAS command
    @Override
    public Iterator<Email> listRcvdMsgs() {
        return rcvdMsgs.iterator();
    }

    //ASSUNTO command
    @Override
    public Iterator<Email> listBySubject(String subject) throws SubjectNotFoundException {
        if (!msgBySubject.containsKey(subject)) throw new SubjectNotFoundException();
        return msgBySubject.get(subject).iterator();
    }

    //EMAIL command
    @Override
    public Iterator<Email> listByEmail(String email) throws EmailNotFoundException {
        if (!msgs.containsKey(email)) throw new EmailNotFoundException();
        return msgs.get(email).iterator();
    }

    //ASSUNTOS command
    @Override
    public Iterator<String> listSubjects() {
        return msgBySubject.keySet().iterator();
    }

    //Auxiliary method to update maps
    private void addToMap(Map<String, List<Email>> map, String key, Email value) {
        List<Email> list;
        if (!map.containsKey(key)) list = new ArrayList<>(); //if list doesn't exist create a new list
        else list = map.get(key);
        list.add(value);
        map.put(key,list);
    }
}
