package database;

import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

/**
 * Created by Olcha on 20.06.2016.
 */
@Entity
@Table(name = "history")
public class History {
    @Id
    @GenericGenerator(name="kaugen" , strategy="increment")
    @GeneratedValue(generator="kaugen")
    @Column(name="id")
    private int id;

    @Column(name = "requestNumber")
    private String requestNumber;

    @Column(name = "date")
    private String date;

    public int getId() {
        return id;
    }

    public String getRequestNumber() {
        return requestNumber;
    }

    public String getDate() {
        return date;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setRequestNumber(String requestNumber) {
        this.requestNumber = requestNumber;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
