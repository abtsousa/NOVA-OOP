package cloud;

import dataStructures.*;

/**
 * An abstract class containing methods common to basic and premium users
 */
abstract class AbstractUserClass implements User {
    //Instance variables
    private String email; //the user's email
    private Array<File> files; //the user's files
    protected int capacity; //the user account's maximum space
    protected double sharedFilesRatio; // how much % do shared files count towards the account's size limit

    /**
     * Constructor
     * @param email the user's email
     */
    public AbstractUserClass(String email) {
        this.email = email;
        this.files = new ArrayClass<File>();
    }

    @Override
    public String getEmail() {
        return email;
    }

    @Override
    public abstract String getType();

    @Override
    public File getFile(String file) {
        Iterator<File> it = listFiles();
        while (it.hasNext()) {
            File f = it.next();
            if (f.getName().equals(file)) return f;
        }
        return null;
    }

    @Override
    public void addFile(File file) {
        files.insertLast(file);
    }

    @Override
    public Iterator<File> listFiles() {
        return files.iterator();
    }

    private int getUsedSpace() {
        Iterator<File> it = listFiles();
        int space = 0;
        while (it.hasNext()) {
            File file = it.next();
            if (file.getOwner() == email) {
                space += file.getSize();
            } else {
                space += file.getSize() * sharedFilesRatio;
            }
        }
        return space;
    }

    @Override
    public int getAvailableSpace()   {return capacity-getUsedSpace();}
}
