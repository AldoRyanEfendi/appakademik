package Form;
import java.sql.*;
public class database {
    //deklarasi variabel global
    Connection koneksi = null;
    Statement statemen = null;
    ResultSet hasil = null;
    //koneksi dan diskoneksi
    void connect(){
        try{
            Class.forName("net.ucanaccess.jdbc.UcanaccessDriver");
        }catch(ClassNotFoundException cnfex){
            System.out.println("Error");
            cnfex.printStackTrace();
        }
    }
    void disconnect(){
        try{
            if(null != koneksi){
                if(null != hasil){
                    hasil.close();
                }
                statemen.close();
                koneksi.close();
            }
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
    
    //method tambahan
    
    boolean login(String a, String b){
        boolean result = false;
        this.connect();
        try{
            String dbloc = "ekstensi/kampus.mdb";
            String dburl = "jdbc:ucanaccess://"+dbloc;
            koneksi = DriverManager.getConnection(dburl);
            statemen = koneksi.createStatement();
            hasil = statemen.executeQuery("Select username, password from user");
            while(hasil.next()){
                if(hasil.getString(1).equals(a) && hasil.getString(2).equals(b)){
                    result = true;
                    System.out.println(hasil.getString(1));
                    System.out.println(hasil.getString(2));
                }
            }
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        this.disconnect();
        return result;
    }
    void insertmhs(String np, String nam, int JK, int ting, String jur, String alam){
        this.connect();
        try{
            String dbloc = "ekstensi/kampus.mdb";
            String dburl = "jdbc:ucanaccess://"+dbloc;
            koneksi = DriverManager.getConnection(dburl);
            statemen = koneksi.createStatement();
            statemen.execute("insert into mhs values('"+np+"','"+nam+"','"+JK+"','"+ting+"','"+jur+"','"+alam+"')");
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
        this.disconnect();
    }
    void updatemhs(String np, String nam, int JK, int ting, String jur, String alam){
        this.connect();
        try{
            String dbloc = "ekstensi/kampus.mdb";
            String dburl = "jdbc:ucanaccess://"+dbloc;
            koneksi = DriverManager.getConnection(dburl);
            statemen = koneksi.createStatement();
            statemen.execute("update mhs set nama_mhs = '"+nam+"', jk_mhs = '"+JK+"', angkatan = '"+ting+"', kd_jurusan = '"+jur+"', alamat = '"+alam+"' where npm = '"+np+"'");
            statemen.close();
            koneksi.close();
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
    void delmhs(String np){
        this.connect();
        try{
            String dbloc = "ekstensi/kampus.mdb";
            String dburl = "jdbc:ucanaccess://"+dbloc;
            koneksi = DriverManager.getConnection(dburl);
            statemen = koneksi.createStatement();
            statemen.execute("delete from mhs where npm = '"+np+"'");
            statemen.close();
            koneksi.close();
        }catch(SQLException sqlex){
            sqlex.printStackTrace();
        }
    }
}
