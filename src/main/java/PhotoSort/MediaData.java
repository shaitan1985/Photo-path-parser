package PhotoSort;

import java.util.Date;

public class MediaData {
    int id;
    String path;
    Date fDate;
    String hash;

    @Override
    public String toString() {
        return "MediaData{" +
                "id=" + id +
                ", path='" + path + '\'' +
                ", fDate=" + fDate +
                ", hash='" + hash + '\'' +
                '}';
    }

    public MediaData(int id, String path, Date fDate, String hash) {
        this.id = id;
        this.path = path;
        this.fDate = fDate;
        this.hash = hash;
    }

    public int getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public Date getfDate() {
        return fDate;
    }

    public String getHash() {
        return hash;
    }
}
