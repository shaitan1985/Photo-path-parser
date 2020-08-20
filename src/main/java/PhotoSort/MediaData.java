package PhotoSort;

import java.util.Calendar;
import java.util.Date;
import java.util.StringJoiner;
import java.util.TimeZone;

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

    public String dateToPath(){
        Calendar cal = Calendar.getInstance(TimeZone.getDefault());
        cal.setTime(fDate);
        StringJoiner sj = new StringJoiner(FSConnector.getSeparator());
        sj.add(Integer.toString(cal.get(Calendar.YEAR)));
        sj.add(Integer.toString(cal.get(Calendar.MONTH)));
        sj.add(Integer.toString(cal.get(Calendar.DAY_OF_MONTH)));

        return sj.toString();
    }
}
