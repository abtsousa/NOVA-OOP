import Exceptions.DuplicateMsgException;
import Exceptions.EmailNotFoundException;
import Exceptions.SubjectNotFoundException;

import java.util.Iterator;

public interface eCorreio {
    //ENVIAR command
    void sendMsg(String subject, String to, String body, String date) throws DuplicateMsgException;

    //RECEBER command
    void receiveMsg(String subject, String from, String body, String date) throws DuplicateMsgException;

    //ENVIADAS command
    Iterator<Email> listSentMsgs();

    //RECEBIDAS command
    Iterator<Email> listRcvdMsgs();

    //ASSUNTO command
    Iterator<Email> listBySubject(String subject) throws SubjectNotFoundException;

    //EMAIL command
    Iterator<Email> listByEmail(String email) throws EmailNotFoundException;

    //ASSUNTOS command
    Iterator<String> listSubjects();
}
