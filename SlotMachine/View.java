import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.util.Random;

public class View {
    private JFrame finestra;

    private JButton pulsanteUscita;
    private JButton pulsanteAumentoSoldi;
    private JButton pulsanteDecrementoSoldi;
    private JButton pulsanteSocchiudi;
    private JButton pulsanteGioca;
    private JButton pulsanteAutoSpin;
    private JButton pulsanteMassimiza;

    private JLabel saldo;
    private JLabel saldoValuta;
    private JLabel vincita;
    private JLabel vincitaValuta;
    private JLabel puntata;

    private JPanel pannelloBarra;
    private JPanel pannelloHUD;
    private JPanel pannelloSaldo;
    private JPanel pannelloValuta;
    private JPanel pannelloSlot;
    private JPanel pannelloGioca;
    private JPanel pannelloPuntata;
    private JPanel pannelloPuntataLabel;

    private JLabel sfondoSlot;

    private JLabel[] caselle;
    private int a = 645;
    private int b = 30;
    private Random rnd = new Random();

    final Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    final int width = (int) screenSize.getWidth();
    final int height = (int) screenSize.getHeight();

    public View(Model model) throws Exception {
        model.carica();
        // Inizializzazione
        finestra = new JFrame("Slot Machine");
        pannelloBarra = new JPanel();
        pannelloHUD = new JPanel();
        pannelloSaldo = new JPanel();
        pannelloValuta = new JPanel();
        pannelloSlot = new JPanel();
        pannelloGioca = new JPanel();
        pannelloPuntata = new JPanel();
        pannelloPuntataLabel = new JPanel();

        // Impostiamo il layout
        finestra.setLayout(null);
        pannelloBarra.setLayout(null);
        pannelloHUD.setLayout(null);
        pannelloSaldo.setLayout(new BorderLayout());
        pannelloValuta.setLayout(new BorderLayout());
        pannelloSlot.setLayout(null);
        pannelloGioca.setLayout(null);
        pannelloPuntata.setLayout(null);
        pannelloPuntataLabel.setLayout(new BorderLayout());

        // SfondoSlot
        sfondoSlot = new JLabel();
        sfondoSlot.setIcon(new ImageIcon("src\\backgroundSlot.png"));
        sfondoSlot.setBounds(0, 0, width, 700);

        // PannelloBarra
        pannelloBarra.setBounds(0, 0, width, 30);
        pannelloBarra.setBackground(new Color(127, 127, 127));

        // PannelloHUD
        pannelloHUD.setBounds(0, 30, width, 150);
        pannelloHUD.setBackground(new Color(19, 8, 48));

        // PannelloSaldo
        pannelloSaldo.setBounds(10, 10, 500, 130);
        pannelloSaldo.setBackground(new Color(68, 25, 132));

        // PannelloValuta
        pannelloValuta.setBounds(width - 510, 10, 500, 130);
        pannelloValuta.setBackground(new Color(68, 25, 132));

        // PannelloSlot
        pannelloSlot.setBounds(0, 180, width, 700);
        pannelloSlot.setBackground(new Color(160, 160, 160));

        // PannelloGioca
        pannelloGioca.setBounds(0, 880, width, 400);
        pannelloGioca.setBackground(new Color(19, 8, 48));

        // PannelloPuntata
        pannelloPuntata.setBounds(610, 50, 700, 100);
        pannelloPuntata.setBackground(new Color(19, 8, 48));

        // PannelloPuntataLabel
        pannelloPuntataLabel.setBounds((pannelloPuntata.getWidth() / 2) - 200, 0, 400, 100);
        pannelloPuntataLabel.setBackground(new Color(68, 25, 132));

        // PulsanteUscita
        pulsanteUscita = new JButton();
        pulsanteUscita.setIcon(new ImageIcon("src\\exit.png"));
        pulsanteUscita.setBackground(new Color(127, 127, 127));
        pulsanteUscita.setFocusPainted(false);
        pulsanteUscita.setBorderPainted(false);
        pulsanteUscita.setBounds(width-40, 0, 40, 30);

        // PulsanteSocchiudi
        pulsanteSocchiudi = new JButton();
        pulsanteSocchiudi.setIcon(new ImageIcon("src\\minimize.png"));
        pulsanteSocchiudi.setBackground(new Color(127, 127, 127));
        pulsanteSocchiudi.setFocusPainted(false);
        pulsanteSocchiudi.setBorderPainted(false);
        pulsanteSocchiudi.setBounds(width - 80, 0, 40, 30);

        // PulsanteMassimiza
        pulsanteMassimiza = new JButton();
        pulsanteMassimiza.setIcon(new ImageIcon("src\\fullscreen.png"));
        pulsanteMassimiza.setBackground(new Color(127, 127, 127));
        pulsanteMassimiza.setFocusPainted(false);
        pulsanteMassimiza.setBorderPainted(false);

        // PulsanteGioca
        pulsanteGioca = new JButton();
        pulsanteGioca.setIcon(new ImageIcon("src\\play.png"));
        pulsanteGioca.setBackground(new Color(68, 25, 132));
        pulsanteGioca.setFocusPainted(false);
        pulsanteGioca.setBorderPainted(false);
        pulsanteGioca.setBounds(width - 190, 10, 180, 180);

        // PulsanteAutoSpin
        pulsanteAutoSpin = new JButton();
        pulsanteAutoSpin.setIcon(new ImageIcon("src\\autospin.png"));
        pulsanteAutoSpin.setBackground(new Color(68, 25, 132));
        pulsanteAutoSpin.setFocusPainted(false);
        pulsanteAutoSpin.setBorderPainted(false);
        pulsanteAutoSpin.setBounds(10, 10, 180, 180);

        // PulsanteAumentoSoldi
        pulsanteAumentoSoldi = new JButton();
        pulsanteAumentoSoldi.setIcon(new ImageIcon("src\\plus.png"));
        pulsanteAumentoSoldi.setBackground(new Color(68, 25, 132));
        pulsanteAumentoSoldi.setFocusPainted(false);
        pulsanteAumentoSoldi.setBorderPainted(false);
        pulsanteAumentoSoldi.setBounds(588, 13, 75, 75);

        // PulsanteDecrementoSoldi
        pulsanteDecrementoSoldi = new JButton();
        pulsanteDecrementoSoldi.setIcon(new ImageIcon("src\\minus.png"));
        pulsanteDecrementoSoldi.setBackground(new Color(68, 25, 132));
        pulsanteDecrementoSoldi.setFocusPainted(false);
        pulsanteDecrementoSoldi.setBorderPainted(false);
        pulsanteDecrementoSoldi.setBounds(75/2, 25/2, 75, 75);


        // Saldo
        saldo = new JLabel("SALDO");
        saldo.setFont(new Font("Impact", Font.PLAIN, 50));
        saldo.setForeground(Color.white);

        // SaldoValuta
        saldoValuta = new JLabel(model.getSaldo().toString() + "\u20ac");
        saldoValuta.setFont(new Font("Impact", Font.PLAIN, 50));
        saldoValuta.setForeground(Color.white);

        // Vincita
        vincita = new JLabel("VINCITA", SwingConstants.RIGHT);
        vincita.setFont(new Font("Impact", Font.PLAIN, 50));
        vincita.setForeground(Color.white);

        // VincitaValuta
        vincitaValuta = new JLabel(model.getVincita().toString() + "\u20ac", SwingConstants.RIGHT);
        vincitaValuta.setFont(new Font("Impact", Font.PLAIN, 50));
        vincitaValuta.setForeground(Color.white);

        // Puntata
        puntata = new JLabel(model.getPuntata() + "\u20ac", SwingConstants.CENTER);
        puntata.setFont(new Font("Impact", Font.PLAIN, 70));
        puntata.setForeground(Color.white);
        puntata.setBounds((pannelloPuntata.getWidth() / 2) - 50, (pannelloPuntata.getHeight() / 2) - 40, 200, 80);

        // Immagini per le caselle
        caselle = new JLabel[9];
        pannelloSlot.add(sfondoSlot);

        for (int i = 0; i < caselle.length; i++) {
            caselle[i] = new JLabel(new ImageIcon(
                    ImageIO.read(new File("src\\symbol\\start.png")).getScaledInstance(200, 200, Image.SCALE_DEFAULT)));
            caselle[i].setBounds(a, b, 200, 200);
            sfondoSlot.add(caselle[i]);
            a += 210;
            if ((i + 1) % 3 == 0) {
                b += 210;
                a = 645;
            }
        }

        // Aggiunta dei componenti
        pannelloBarra.add(pulsanteUscita);
        pannelloBarra.add(pulsanteSocchiudi);

        pannelloSaldo.add(saldo, BorderLayout.NORTH);
        pannelloSaldo.add(saldoValuta, BorderLayout.SOUTH);

        pannelloValuta.add(vincita, BorderLayout.NORTH);
        pannelloValuta.add(vincitaValuta, BorderLayout.SOUTH);

        pannelloHUD.add(pannelloSaldo);
        pannelloHUD.add(pannelloValuta);

        pannelloPuntataLabel.add(puntata, BorderLayout.CENTER);
        pannelloPuntata.add(pannelloPuntataLabel);
        pannelloPuntata.add(pulsanteAumentoSoldi);
        pannelloPuntata.add(pulsanteDecrementoSoldi);

        pannelloGioca.add(pulsanteGioca);
        pannelloGioca.add(pulsanteAutoSpin);
        pannelloGioca.add(pannelloPuntata);
        finestra.add(pannelloBarra);
        finestra.add(pannelloHUD);
        finestra.add(pannelloSlot);
        finestra.add(pannelloGioca);

        // Impostazioni finestra
        finestra.setExtendedState(JFrame.MAXIMIZED_BOTH);
        finestra.setUndecorated(true);
        finestra.setLocationRelativeTo(null);
        finestra.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        finestra.setIconImage(new ImageIcon("src\\slotMachineIcona.png").getImage());
        finestra.setVisible(true);
    }

    public JFrame getFinestra() {
        return finestra;
    }

    public void setFinestra(JFrame finestra) {
        this.finestra = finestra;
    }

    public JButton getPulsanteUscita() {
        return pulsanteUscita;
    }

    public void setPulsanteUscita(JButton pulsanteUscita) {
        this.pulsanteUscita = pulsanteUscita;
    }

    public JButton getPulsanteAumentoSoldi() {
        return pulsanteAumentoSoldi;
    }

    public void setPulsanteAumentoSoldi(JButton pulsanteAumentoSoldi) {
        this.pulsanteAumentoSoldi = pulsanteAumentoSoldi;
    }

    public JButton getPulsanteDecrementoSoldi() {
        return pulsanteDecrementoSoldi;
    }

    public void setPulsanteDecrementoSoldi(JButton pulsanteDecrementoSoldi) {
        this.pulsanteDecrementoSoldi = pulsanteDecrementoSoldi;
    }

    public JButton getPulsanteSocchiudi() {
        return pulsanteSocchiudi;
    }

    public void setPulsanteSocchiudi(JButton pulsanteSocchiudi) {
        this.pulsanteSocchiudi = pulsanteSocchiudi;
    }

    public JButton getPulsanteGioca() {
        return pulsanteGioca;
    }

    public void setPulsanteGioca(JButton pulsanteGioca) {
        this.pulsanteGioca = pulsanteGioca;
    }

    public JButton getPulsanteAutoSpin() {
        return pulsanteAutoSpin;
    }

    public void setPulsanteAutoSpin(JButton pulsanteAutoSpin) {
        this.pulsanteAutoSpin = pulsanteAutoSpin;
    }

    public JLabel getSaldo() {
        return saldo;
    }

    public void setSaldo(JLabel saldo) {
        this.saldo = saldo;
    }

    public JLabel getVincita() {
        return vincita;
    }

    public void setVincita(JLabel vincita) {
        this.vincita = vincita;
    }

    public JLabel getPuntata() {
        return puntata;
    }

    public void setPuntata(JLabel puntata) {
        this.puntata = puntata;
    }

    public JPanel getPannelloBarra() {
        return pannelloBarra;
    }

    public void setPannelloBarra(JPanel pannelloBarra) {
        this.pannelloBarra = pannelloBarra;
    }

    public JButton getPulsanteMassimiza() {
        return pulsanteMassimiza;
    }

    public void setPulsanteMassimiza(JButton pulsanteMassimiza) {
        this.pulsanteMassimiza = pulsanteMassimiza;
    }

    public JPanel getPannelloHUD() {
        return pannelloHUD;
    }

    public void setPannelloHUD(JPanel pannelloHUD) {
        this.pannelloHUD = pannelloHUD;
    }

    public JLabel getSaldoValuta() {
        return saldoValuta;
    }

    public void setSaldoValuta(JLabel saldoValuta) {
        this.saldoValuta = saldoValuta;
    }

    public JLabel getVincitaValuta() {
        return vincitaValuta;
    }

    public void setVincitaValuta(JLabel vincitaValuta) {
        this.vincitaValuta = vincitaValuta;
    }

    public JPanel getPannelloSaldo() {
        return pannelloSaldo;
    }

    public void setPannelloSaldo(JPanel pannelloSaldo) {
        this.pannelloSaldo = pannelloSaldo;
    }

    public JPanel getPannelloValuta() {
        return pannelloValuta;
    }

    public void setPannelloValuta(JPanel pannelloValuta) {
        this.pannelloValuta = pannelloValuta;
    }

    public JPanel getPannelloSlot() {
        return pannelloSlot;
    }

    public void setPannelloSlot(JPanel pannelloSlot) {
        this.pannelloSlot = pannelloSlot;
    }

    public Dimension getScreenSize() {
        return screenSize;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public JPanel getPannelloGioca() {
        return pannelloGioca;
    }

    public void setPannelloGioca(JPanel pannelloGioca) {
        this.pannelloGioca = pannelloGioca;
    }

    public JPanel getPannelloPuntata() {
        return pannelloPuntata;
    }

    public void setPannelloPuntata(JPanel pannelloPuntata) {
        this.pannelloPuntata = pannelloPuntata;
    }

    public JPanel getPannelloPuntataLabel() {
        return pannelloPuntataLabel;
    }

    public void setPannelloPuntataLabel(JPanel pannelloPuntataLabel) {
        this.pannelloPuntataLabel = pannelloPuntataLabel;
    }

    public JLabel[] getCaselle() {
        return caselle;
    }

    public void setCaselle(JLabel[] caselle) {
        this.caselle = caselle;
    }

    public int getA() {
        return a;
    }

    public void setA(int a) {
        this.a = a;
    }

    public int getB() {
        return b;
    }

    public void setB(int b) {
        this.b = b;
    }

    public Random getRnd() {
        return rnd;
    }

    public void setRnd(Random rnd) {
        this.rnd = rnd;
    }

    public JLabel getSfondoSlot() {
        return sfondoSlot;
    }

    public void setSfondoSlot(JLabel sfondoSlot) {
        this.sfondoSlot = sfondoSlot;
    }

}
