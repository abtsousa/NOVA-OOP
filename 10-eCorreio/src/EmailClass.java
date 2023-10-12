public class EmailClass implements Email {
    //variáveis
    private String subject;
    private String mail;
    private String body;
    private String date;

    //constructor
    public EmailClass(String subject, String mail, String body, String date) {
        this.subject = subject;
        this.mail = mail;
        this.body = body;
        this.date = date;
    }

    //Getters
    @Override
    public String getSubject() {
        return subject;
    }

    @Override
    public String getMail() {
        return mail;
    }

    @Override
    public String getBody() {
        return body;
    }

    @Override
    public String getDate() {
        return date;
    }

    //métodos
    @Override
    public int compareTo(Email other) {
        int result;
        result = date.compareTo(other.getDate());
        if (result == 0) {result = subject.compareTo(other.getSubject());}
        if (result == 0) {result = mail.compareTo(other.getMail());}
        return result;
    }

}