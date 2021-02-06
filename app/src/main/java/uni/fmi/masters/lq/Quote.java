package uni.fmi.masters.lq;

public class Quote {
    private long ID;
    private String content;
    private String author;
    private String date;
    private String time;

    Quote() {
    }

    Quote(String content, String author, String date, String time) {
        this.content = content;
        this.author = author;
        this.date = date;
        this.time = time;
    }

    Quote(long id,String content, String author, String date, String time){
        this.ID=id;
        this.content = content;
        this.author = author;
        this.date = date;
        this.time = time;
    }

    public long getID() {
        return ID;
    }

    public void setID(long ID) {
        this.ID = ID;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }
}
