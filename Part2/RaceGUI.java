import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

@SuppressWarnings("ALL")
public class RaceGUI extends JFrame {
    // Define GUI components
    private JPanel trackLengthPanel;
    private JPanel horseCustomisationPanel1;
    private JPanel horseCustomisationPanel2;
    private JPanel horseCustomisationPanel3;
    private JPanel raceSimulationPanel;
    private JPanel statisticsPanel;
    private JLabel winningHorseLabel;
    private JLabel averageSpeedLabel;
    private JLabel finishTimeLabel;
    private Race race;

    public RaceGUI() {
        // Set up the main frame
        setTitle("Racing Application");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new GridLayout(2, 2)); // Divide the frame into four quadrants

        // Initialize GUI components
        initializeTrackDesignPanel();
        initializeHorseCustomisationPanels();
        initializeRaceSimulationPanel();
        initializeStatisticsPanel();

        // Add GUI components to the frame
        add(trackLengthPanel);
        add(horseCustomisationPanel1);
        add(horseCustomisationPanel2);
        add(horseCustomisationPanel3);
        add(raceSimulationPanel);
        add(statisticsPanel);

        // Display the frame
        setVisible(true);
    }

    private void initializeTrackDesignPanel() {
        trackLengthPanel = new JPanel();
        trackLengthPanel.setLayout(new BorderLayout());
        trackLengthPanel.setBackground(new Color(173, 216, 230));

        // Track length slider
        JSlider trackLengthSlider = new JSlider(JSlider.HORIZONTAL, 10, 350, 100);
        trackLengthSlider.setMajorTickSpacing(100);
        trackLengthSlider.setMinorTickSpacing(50);
        trackLengthSlider.setPaintTicks(true);
        trackLengthSlider.setPaintLabels(true);
        trackLengthPanel.add(trackLengthSlider, BorderLayout.CENTER);

        // Add track obstacles button
        JButton addObstaclesButton = new JButton("Add Obstacles");
        trackLengthPanel.add(addObstaclesButton, BorderLayout.SOUTH);

        trackLengthPanel.setBorder(BorderFactory.createTitledBorder("Track Design"));
    }

    private void initializeHorseCustomisationPanels() {
        horseCustomisationPanel1 = new JPanel();
        horseCustomisationPanel1.setLayout(new GridLayout(4, 1));
        horseCustomisationPanel1.setBackground(new Color(173, 216, 230));
        JTextField horse1NameTextField = new JTextField("Horse 1");
        JComboBox<String> breedCBox1 = new JComboBox<>(new String[]{"Mustang", "Thoroughbred", "Arabian", "Breton"});
        JComboBox<String> coatColourCBox1 = new JComboBox<>(new String[]{"Black", "Chestnut", "Bay", "Gray"});
        JComboBox<Character> symbolsCBox1 = new JComboBox<>(new Character[]{'♘', '♞', '♖'});
        horseCustomisationPanel1.add(createLabeledPanel(horse1NameTextField, "Horse", 1));
        horseCustomisationPanel1.add(createLabeledPanel(breedCBox1, "Breed", 1));
        horseCustomisationPanel1.add(createLabeledPanel(coatColourCBox1, "Coat Colour", 1));
        horseCustomisationPanel1.add(createLabeledPanel(symbolsCBox1, "Horse Symbol", 1));

        horseCustomisationPanel2 = new JPanel();
        horseCustomisationPanel2.setLayout(new GridLayout(4, 1));
        horseCustomisationPanel2.setBackground(new Color(173, 216, 230));
        JTextField horse2NameTextField = new JTextField("Horse 2");
        JComboBox<String> breedCBox2 = new JComboBox<>(new String[]{"Mustang", "Thoroughbred", "Arabian", "Breton"});
        JComboBox<String> coatColourCBox2 = new JComboBox<>(new String[]{"Black", "Chestnut", "Bay", "Gray"});
        JComboBox<Character> symbolsCBox2 = new JComboBox<>(new Character[]{'♘', '♞', '♖'});
        horseCustomisationPanel2.add(createLabeledPanel(horse2NameTextField, "Horse", 2));
        horseCustomisationPanel2.add(createLabeledPanel(breedCBox2, "Breed", 2));
        horseCustomisationPanel2.add(createLabeledPanel(coatColourCBox2, "Coat Colour", 2));
        horseCustomisationPanel2.add(createLabeledPanel(symbolsCBox2, "Horse Symbol", 2));

        horseCustomisationPanel3 = new JPanel();
        horseCustomisationPanel3.setLayout(new GridLayout(4, 1));
        horseCustomisationPanel3.setBackground(new Color(173, 216, 230));
        JTextField horse3NameTextField = new JTextField("Horse 3");
        JComboBox<String> breedCBox3 = new JComboBox<>(new String[]{"Mustang", "Thoroughbred", "Arabian", "Breton"});
        JComboBox<String> coatColourCBox3 = new JComboBox<>(new String[]{"Black", "Chestnut", "Bay", "Gray"});
        JComboBox<Character> symbolsCBox3 = new JComboBox<>(new Character[]{'♘', '♞', '♖'});
        horseCustomisationPanel3.add(createLabeledPanel(horse3NameTextField, "Horse", 3));
        horseCustomisationPanel3.add(createLabeledPanel(breedCBox3, "Breed", 3));
        horseCustomisationPanel3.add(createLabeledPanel(coatColourCBox3, "Coat Colour", 3));
        horseCustomisationPanel3.add(createLabeledPanel(symbolsCBox3, "Horse Symbol", 3));
    }


    private JPanel createLabeledPanel(Component component, String labelText, int panelNumber) {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        JLabel label = new JLabel(labelText + " " + panelNumber);
        panel.add(label);
        panel.add(component);
        return panel;
    }


    private void initializeRaceSimulationPanel() {
        raceSimulationPanel = new JPanel();
        raceSimulationPanel.setLayout(new GridLayout(4, 1));
        raceSimulationPanel.setBackground(new Color(173, 216, 230));

        // Race control buttons
        JButton startButton = new JButton("Start Race");
        startButton.setForeground(Color.GREEN);
        startButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int raceDistance = ((JSlider) trackLengthPanel.getComponent(0)).getValue();
                race = new Race(raceDistance,3);
                // Get horse names and symbols
                String horse1Name = "horse 1";
                String horse2Name = "horse 2";
                String horse3Name = "horse 3";
                Character horse1Symbol ='♘' ;
                Character horse2Symbol = '♞';
                Character horse3Symbol = '♖';

                if (horseCustomisationPanel1.getComponent(0) instanceof JTextField) {
                    JTextField textField = (JTextField) horseCustomisationPanel1.getComponent(0);
                    horse1Name = textField.getText();

                    JTextField textField2 = (JTextField) horseCustomisationPanel2.getComponent(0);
                    horse2Name = textField2.getText();

                    JTextField textField3 = (JTextField) horseCustomisationPanel3.getComponent(0);
                    horse3Name = textField3.getText();
                }

                if (horseCustomisationPanel1.getComponent(3) instanceof JComboBox) {
                    horse1Symbol = (Character) ((JComboBox) horseCustomisationPanel1.getComponent(3)).getSelectedItem();
                    horse2Symbol = (Character) ((JComboBox) horseCustomisationPanel2.getComponent(3)).getSelectedItem();
                    horse3Symbol = (Character) ((JComboBox) horseCustomisationPanel3.getComponent(3)).getSelectedItem();
                }
                // Create horses
                Horse horse1 = new Horse(horse1Symbol, horse1Name, 0.2);
                Horse horse2 = new Horse(horse2Symbol, horse2Name, 0.5);
                Horse horse3 = new Horse(horse3Symbol, horse3Name, 0.4);



                // Add horses to the race
                race.addHorse(horse1, 1);
                race.addHorse(horse2, 2);
                race.addHorse(horse3, 3);

                // Start the race
                race.startRace();

                // Update statistics panel with winning horse's name or message
                if (race.getWinningHorseName() != null) {
                    winningHorseLabel.setText("Winner: " + race.getWinningHorseName());
                    averageSpeedLabel.setText(race.getAverageSpeed());
                    finishTimeLabel.setText("finish time is: " + race.getWinTime()+ "s");
                } else {
                    winningHorseLabel.setText("No winner. All horses have fallen.");
                    averageSpeedLabel.setText("no average speed");
                    finishTimeLabel.setText("no finish time");
                }
            }
        });

        JButton stopButton = new JButton("Close");
        stopButton.setForeground(Color.RED);
        stopButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0); // Stop the program running
            }
        });

        raceSimulationPanel.add(startButton);
        raceSimulationPanel.add(stopButton);

        raceSimulationPanel.setBorder(BorderFactory.createTitledBorder("Race Simulation"));
    }

    private void initializeStatisticsPanel() {
        statisticsPanel = new JPanel();
        statisticsPanel.setLayout(new BorderLayout());
        statisticsPanel.setBackground(new Color(173, 216, 230));

        winningHorseLabel = new JLabel();
        winningHorseLabel.setText("race is ongoing");
        statisticsPanel.add(winningHorseLabel, BorderLayout.NORTH);

        finishTimeLabel = new JLabel();
        statisticsPanel.add(finishTimeLabel, BorderLayout.CENTER);

        averageSpeedLabel = new JLabel();
        winningHorseLabel.setText("calculating average speed of winner");
        statisticsPanel.add(averageSpeedLabel,BorderLayout.SOUTH);

        statisticsPanel.setBorder(BorderFactory.createTitledBorder("Statistics"));
    }

    public static void main(String[] args) {
        // Run the application
        SwingUtilities.invokeLater(RaceGUI::new);
    }
}
