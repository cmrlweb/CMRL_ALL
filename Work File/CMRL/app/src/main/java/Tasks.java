/**
 * Created by administrator on 10/12/15.
 */
public class Tasks {
    int id;
    String assetcode;
    int INTERNET;
    int SMS;

    Tasks(int rid,String assetcode,int internet,int sms) {
        this.id = rid;
        this.assetcode = assetcode;
        this.INTERNET = internet;
        this.SMS = sms;
    }
}
