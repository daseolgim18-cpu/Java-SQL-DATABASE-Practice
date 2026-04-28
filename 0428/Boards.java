import java.sql.Date;
import java.sql.Blob;

public class Boards {
    private int bno;
    private String btitle;
    private String bcontent;
    private String bwriter;
    private Date bdate;
    private String bfilename;
    private Blob bfiledata;

    public void setBno(int bno){
        this.bno = bno;
    }

    public void setBtitle(String btitle){
        this.btitle = btitle;
    }

    public void setBcontent(String bcontent){
        this.bcontent = bcontent;
    }

    public void setBwriter(String bwriter) {
        this.bwriter = bwriter;
    }

    public void setBdate(Date bdate) {
        this.bdate = bdate;
    }

    public void setBfilename(String bfilename) {
        this.bfilename = bfilename;
    }

    public void setBfiledata(Blob bfiledata) {
    this.bfiledata = bfiledata;
}
    public int getBno(){
        return bno;
    }

    public String getBtitle(){
        return btitle;
    }

    public String getBcontent(){
        return bcontent;
    }

    public String getBwriter(){
        return bwriter;
    }

    public Date getBdate(){
        return bdate;
    }

    public String getBfilename() {
        return bfilename;
    }

    public Blob getBfiledata() {
        return bfiledata;
    }
}
