package made.sub3.widgets;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "widget")
public class WidgetItem {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String title;
    private String absolute_path;
    private String child_path;

    public WidgetItem(int id, String title, String absolute_path, String child_path) {
        this.id = id;
        this.title = title;
        this.absolute_path = absolute_path;
        this.child_path = child_path;
    }

    @Ignore
    public WidgetItem(String title, String absolute_path, String child_path) {
        this.title = title;
        this.absolute_path = absolute_path;
        this.child_path = child_path;
    }

    public String getChild_path() {
        return child_path;
    }

    public String getAbsolute_path() {
        return absolute_path;
    }

    public int getId() {
        return id;
    }

    public String getTitle() {
        return title;
    }

    public String getPath() {
        return absolute_path;
    }
}
