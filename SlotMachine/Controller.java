import java.awt.Color;
import java.awt.Dimension;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Controller{
    private View view;
    private Model model;
    int[] temp = new int[9];
    

    public Controller(Model model, View view) throws Exception {
        this.view = view;
        this.model = model;
        UIManager.put("OptionPane.minimumSize",new Dimension(500,500)); 

        view.getPulsanteUscita().addActionListener(e -> {

            int dialogButton = 1;
            try {
                dialogButton = JOptionPane.showConfirmDialog(null, null, null, JOptionPane.YES_NO_OPTION, 1, new ImageIcon(ImageIO.read(new File("src\\minerGambling.png")).getScaledInstance(500, 500, Image.SCALE_DEFAULT)));
            } catch (HeadlessException | IOException e1) {
                e1.printStackTrace();
            }

            if (dialogButton == JOptionPane.NO_OPTION) {
                if (model.getSaldo()==0){
                    model.setSaldo(1000);
                    model.setPuntata(10);
                    model.setVincita(0);
                }
                model.salva();
                System.exit(0);
            }

            // System.exit(0);
        });

        view.getPulsanteUscita().addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                view.getPulsanteUscita().setBackground(Color.RED);
            };

            public void mouseExited(java.awt.event.MouseEvent e) {
                view.getPulsanteUscita().setBackground(new Color(127, 127, 127));
            };
        });

        view.getPulsanteSocchiudi().addActionListener(e -> {
            view.getFinestra().setState(JFrame.ICONIFIED);
        });

        view.getPulsanteSocchiudi().addMouseListener(new MouseAdapter() {
            public void mouseEntered(java.awt.event.MouseEvent e) {
                view.getPulsanteSocchiudi().setBackground(Color.lightGray);
            };

            public void mouseExited(java.awt.event.MouseEvent e) {
                view.getPulsanteSocchiudi().setBackground(new Color(127, 127, 127));
            };
        });

        view.getPulsanteAumentoSoldi().addActionListener(e -> {
            model.setPuntata(model.getPuntata() + 10);
            aggiorna();
        });

        view.getPulsanteDecrementoSoldi().addActionListener(e -> {
            if (model.getPuntata() > 10) {
                model.setPuntata(model.getPuntata() - 10);
                aggiorna();
            }
        });
        view.getPulsanteGioca().addActionListener(e -> spin());
        view.getPulsanteAutoSpin().addActionListener(new ActionListener() {
            boolean running = false;
            Thread thread;

            @Override
            public void actionPerformed(ActionEvent e) {
                if (!running) {
                    running = true;
                    view.getPulsanteAutoSpin().setBackground(Color.RED);
                    thread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            while (running) {
                                view.getPulsanteGioca().setEnabled(false);
                                spin();
                                try {
                                    Thread.sleep(1000);
                                } catch (InterruptedException ex) {
                                    //ex.printStackTrace();
                                }
                            }
                        }
                    });
                    thread.start();
                } else {
                    running = false;
                    view.getPulsanteAutoSpin().setBackground(new Color(68, 25, 132));
                    view.getPulsanteGioca().setEnabled(true);
                    thread.interrupt();
                }
            }
        }
        );
    }


    public void spin() {
        if (model.getSaldo() - model.getPuntata() >= 0) {
            model.setSaldo(model.getSaldo() - model.getPuntata());
            aggiorna();
            for (int i = 0; i < view.getCaselle().length; i++) {
                temp[i] = view.getRnd().nextInt(9) + 1;
                try {
                    view.getCaselle()[i].setBorder(null);
                    view.getCaselle()[i].setIcon(new ImageIcon(ImageIO.read(new File(cerca(temp[i]).toString()))
                            .getScaledInstance(180, 180, Image.SCALE_DEFAULT)));
                } catch (IOException e1) {
                    // TODO Auto-generated catch block
                    e1.printStackTrace();
                }
            }
        } else if (model.getSaldo() < model.getPuntata() && model.getSaldo()!=0) {
            JOptionPane.showMessageDialog(null, "Non hai abbastanza saldo!", null, JOptionPane.ERROR_MESSAGE);
            model.setPuntata(model.getSaldo());
            aggiorna();
        } else {
            JOptionPane.showMessageDialog(null, "Hai terminato i soldi!", null, JOptionPane.ERROR_MESSAGE);
            view.getPulsanteGioca().setEnabled(false);
            view.getPulsanteAutoSpin().setBackground(new Color(68, 25, 132));
            view.getPulsanteAutoSpin().setEnabled(false);
            view.getPulsanteAumentoSoldi().setEnabled(false);
            view.getPulsanteDecrementoSoldi().setEnabled(false);
        }

        int win = 0;
        if (temp[0] == temp[1] && temp[1] == temp[2]) {
            win = model.getPuntata() * moltiplicatore(temp[0]);
            view.getCaselle()[0].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[1].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[2].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
        } else if (temp[3] == temp[4] && temp[4] == temp[5]) {
            win = model.getPuntata() * moltiplicatore(temp[3]);
            view.getCaselle()[3].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[4].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[5].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
        } else if (temp[6] == temp[7] && temp[7] == temp[8]) {
            win = model.getPuntata() * moltiplicatore(temp[6]);
            view.getCaselle()[6].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[7].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[8].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
        } else if (temp[0] == temp[4] && temp[4] == temp[8]) {
            win = model.getPuntata() * moltiplicatore(temp[0]);
            view.getCaselle()[0].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[4].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[8].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
        } else if (temp[6] == temp[4] && temp[4] == temp[2]) {
            win = model.getPuntata() * moltiplicatore(temp[6]);
            view.getCaselle()[2].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[4].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
            view.getCaselle()[6].setBorder(BorderFactory.createLineBorder(Color.RED, 7));
        }

        model.setSaldo(model.getSaldo() + win);
        model.setVincita(win);
        model.fileLog(win);
        aggiorna();

    }

    public void aggiorna() {
        view.getPuntata().setText(model.getPuntata().toString() + "\u20ac");
        view.getSaldoValuta().setText(model.getSaldo().toString() + "\u20ac");
        view.getVincitaValuta().setText(model.getVincita().toString() + "\u20ac");
    }

    public File cerca(int numeroCercato) {
        for (Map.Entry<File, Integer> entry : model.getSimboli().entrySet()) {
            if (entry.getValue() == numeroCercato) {
                return entry.getKey();
            }
        }
        return null;
    }

    public int moltiplicatore(int temp) {
        switch (temp) {
            case 1:
                return 20;
            case 2:
                return 12;
            case 3:
                return 2;
            case 4:
                return 15;
            case 5:
                return 3;
            case 6:
                return 5;
            case 7:
                return 100;
            case 8:
                return 7;
            case 9:
                return 10;
            default:
                return 1;
        }
    }

    public View getView() {
        return view;
    }

    public void setView(View view) {
        this.view = view;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
    }

}