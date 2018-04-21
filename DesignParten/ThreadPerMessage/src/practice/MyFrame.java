package practice;

import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class MyFrame extends JFrame implements ActionListener {
    private final ServiceHandler handler = new ServiceHandler();

    public MyFrame() {
        super("MyFrame");
        getContentPane().setLayout(new FlowLayout());
        getContentPane().add(new JLabel("Thread-Per-Message Sample"));
        JButton button = new JButton("Execute");
        getContentPane().add(button);
        button.addActionListener(this);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        pack();
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        handler.handle();
    }
}