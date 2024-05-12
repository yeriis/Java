import java.io.*;
import java.util.Calendar;
import java.util.HashMap;

public class Model implements Serializable {
    private Integer saldo = 1000;
    private Integer vincita = 0;
    private Integer puntata = 10;
    private HashMap<File, Integer> simboli = new HashMap<File, Integer>();
    

    public Model(){
        simboli.put(new File("src\\symbol\\1.png"), 1);
        simboli.put(new File("src\\symbol\\2.png"), 2);
        simboli.put(new File("src\\symbol\\3.png"), 3);
        simboli.put(new File("src\\symbol\\4.png"), 4);
        simboli.put(new File("src\\symbol\\5.png"), 5);
        simboli.put(new File("src\\symbol\\6.png"), 6);
        simboli.put(new File("src\\symbol\\7.png"), 7);
        simboli.put(new File("src\\symbol\\8.png"), 8);
        simboli.put(new File("src\\symbol\\9.png"), 9);
    }

    public Integer getSaldo() {
        return saldo;
    }
    public void setSaldo(Integer saldo) {
        this.saldo = saldo;
    }
    public Integer getVincita() {
        return vincita;
    }
    public void setVincita(Integer vincita) {
        this.vincita = vincita;
    }
    public Integer getPuntata() {
        return puntata;
    }
    public void setPuntata(Integer puntata) {
        this.puntata = puntata;
    }
    public HashMap<File, Integer> getSimboli() {
        return simboli;
    }

    public void setSimboli(HashMap<File, Integer> simboli) {
        this.simboli = simboli;
    }

    public void salva() {
        try {
            File file = new File("out\\dati.ser");
            if (file.exists())
                file.delete();

            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
            );
            oos.writeObject(this);

            oos.close();
        } catch (Exception __) { }
    }
    public void carica() {
        try {
            File file = new File("out\\dati.ser");
            if (!file.exists())
                return;

            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
            );

            Model modelCaricato = (Model) ois.readObject();
            this.saldo = modelCaricato.saldo;
            this.vincita = modelCaricato.vincita;
            this.puntata = modelCaricato.puntata;
            
            ois.close();
        } catch (Exception __) { }
    }
    public void fileLog(int temp) {
        try {
            File file = new File("out\\log.ser");
            if (file.exists())
                file.delete();

            ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(file)
            );
            Calendar cal = Calendar.getInstance();
            oos.writeObject("["+cal.get(Calendar.DAY_OF_MONTH)+"/"+cal.get(Calendar.MONTH)+"/"+cal.get(Calendar.YEAR)+"]"+"["+cal.get(Calendar.HOUR_OF_DAY)+":"+cal.get(Calendar.MINUTE)+":"+cal.get(Calendar.SECOND)+"]"+" Win="+temp);
            oos.close();
        } catch (Exception __) { }
    }
    public void viewLog(){
        try {
            File file = new File("out\\log.ser");
            if (!file.exists())
                return;

            ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(file)
            );

            System.out.println(ois.readObject());
            
            ois.close();
        } catch (Exception __) { }
    }
}
