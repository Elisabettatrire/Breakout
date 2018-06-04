package ids.univpm.breakout.model;

import java.util.ArrayList;

/**
 *
 * @author costantino
 */
public class Piano {
    private long ID_piano;
    private String quota;
    private ArrayList<Mappa> mappe;
    private Long[] mappe_long;

    public Piano() {
    }

    public Long[] getMappe_long() {
        return mappe_long;
    }

    public void setMappe_long(Long[] mappe_long) {
        this.mappe_long = mappe_long;
    }

    public ArrayList<Mappa> getMappe() {
        return mappe;
    }

    public void setMappe(ArrayList<Mappa> mappe) {
        this.mappe = mappe;
    }

    public long getID_piano() {
        return ID_piano;
    }

    public void setID_piano(long ID_piano) {
        this.ID_piano = ID_piano;
    }

    public String getQuota() {
        return quota;
    }

    public void setQuota(String quota) {
        this.quota = quota;
    }
}