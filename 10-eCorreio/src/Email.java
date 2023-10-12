public interface Email extends Comparable<Email> {
    //Getters
    String getSubject();

    String getMail();

    String getBody();

    String getDate();

    //métodos
    int compareTo(Email other);
}
