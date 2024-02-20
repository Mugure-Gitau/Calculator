import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.lang.Math;
import java.awt.event.ActionListener;

public class Calculator extends JFrame {

    private JTextField textField;
    private String input = "";

    public Calculator() {
        initUI();
    }

    private void initUI() {
        setTitle("Calculator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Main panel
        JPanel mainPanel = new JPanel(new BorderLayout());

        // Text field
        textField = new JTextField();
        textField.setFont(new Font("Arial", Font.PLAIN, 20));
        textField.setHorizontalAlignment(JTextField.RIGHT);
        textField.setPreferredSize(new Dimension(200, 50)); // Set preferred size
        textField.setBackground(Color.decode("#f3ccff"));
        textField.setEditable(true);
        mainPanel.add(textField, BorderLayout.NORTH);

        // Button panel
        JPanel buttonPanel = new JPanel(new GridLayout(5, 10, 5, 5)); // Grid layout for buttons
        buttonPanel.setBackground(Color.decode("#53385d"));
        String[] buttonLabels = { "(", ")", "mc", "m+", "m-", "mr", "C", "+/-", "%","÷",
                "2ⁿᵈ", "x²", "x³", "xʸ", "eˣ", "10ˣ", "7", "8", "9", "×",
                "¹⁄ₓ", "√x", "∛x", "ʸ√x", "ln", "log₁₀", "4", "5", "6", "-",
                "x!", "sin", "cos", "tan", "e", "EE", "1", "2", "3", "+",
                "Rad", "sinh", "cosh", "tanh", "\u03C0", "Rand", ".", "0","DEL", "=",};
        Font buttonFont = new Font("Segoe U.I", Font.BOLD, 15);
        for (String label : buttonLabels) {
            JButton button = new JButton(label);
            button.setBackground(Color.decode("#e79aff"));
            button.setFont(buttonFont);
            button.addActionListener(new ButtonClickListener());
            buttonPanel.add(button);
            if (label.equals("0")||label.equals("1")||label.equals("2")||label.equals("3")||label.equals("4")
                    ||label.equals("5")||label.equals("6")||label.equals("7")||label.equals("8")
                    ||label.equals("9")||label.equals(".")||label.equals("DEL")) {
                button.setFont(new Font("Arial", Font.BOLD, 20)); // Make zero button bigger
                button.setPreferredSize(new Dimension(100, 50)); // Increase size of zero button
                button.setBackground(Color.decode("#f3ccff"));
            }
            if (label.equals("÷")){
                button.setBackground(Color.decode("#bb44f0"));
                button.setFont(new Font("Arial", Font.BOLD, 24));
            }
            if (label.equals("×")){
                button.setBackground(Color.decode("#bb44f0"));
                button.setFont(new Font("Arial", Font.BOLD, 24));
            }
            if (label.equals("-")){
                button.setBackground(Color.decode("#bb44f0"));
                button.setFont(new Font("Arial", Font.BOLD, 24));
            }
            if (label.equals("+")){
                button.setBackground(Color.decode("#bb44f0"));
                button.setFont(new Font("Arial", Font.BOLD, 24));
            }
            if (label.equals("=")){
                button.setBackground(Color.decode("#bb44f0"));
                button.setFont(new Font("Arial", Font.BOLD, 24));
            }
            // Mouse listener for hover
            button.addMouseListener(new MouseAdapter() {
                @Override
                public void mouseEntered(MouseEvent e) {
                    button.setBackground(Color.decode("#fbeeff"));
                }

                @Override
                public void mouseExited(MouseEvent e) {
                    button.setBackground(Color.decode("#e79aff"));
                    if (label.equals("÷")){
                        button.setBackground(Color.decode("#bb44f0"));
                    }
                    if (label.equals("×")){
                        button.setBackground(Color.decode("#bb44f0"));
                    }
                    if (label.equals("-")){
                        button.setBackground(Color.decode("#bb44f0"));
                    }
                    if (label.equals("+")){
                        button.setBackground(Color.decode("#bb44f0"));
                    }
                    if (label.equals("=")){
                        button.setBackground(Color.decode("#bb44f0"));}
                        if (label.equals("0")||label.equals("1")||label.equals("2")||label.equals("3")||label.equals("4")
                                ||label.equals("5")||label.equals("6")||label.equals("7")||label.equals("8")
                                ||label.equals("9")||label.equals(".")||label.equals("DEL")){
                            button.setBackground(Color.decode("#f3ccff"));
                }}
            });

            // Action listener for click
            button.addActionListener(e -> {
                button.setBackground(Color.decode("#9614d0"));

            });
        }


        mainPanel.add(buttonPanel, BorderLayout.CENTER);
        add(mainPanel);
        pack();
        setLocationRelativeTo(null);
    }
    private class ButtonClickListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            String command = e.getActionCommand();

            if ("0123456789.".contains(command)) {
                input += command;
            } else if ("+-×÷".contains(command)) {
                input += " " + command + " ";
            } else if ("sin cos tan  ln √x x² x³ ∛x eˣ 10ˣ ʸ√x Rad sinh cosh tanh log₁₀ Rad xʸ ¹⁄ₓ e EE \u03C0 x!".contains(command)) {
                handleSpecialOperations(command);
            } else if ("=".equals(command)) {
                evaluateExpression();
            } else if ("+/-".equals(command)) {
                handleNegation();
            } else if ("C".equals(command)) {
                input = "";
                textField.setText("");
            }
               else if ("%".equals(command)) {
                    input += " % ";}
             else if ("(".equals(command) || ")".equals(command)) {
                input += command;
            } else if ("DEL".equals(command)) {
                deleteLastCharacter();

            }


            textField.setText(input);
        }

        private void handleSpecialOperations(String command) {
            switch (command) {
                case "sin":
                case "cos":
                case "tan":
                case "ln":
                    input += "ln";
                    break;
                case "√x":
                    input +=  "sqrt(";
                    break;
                case "∛x":
                    input +=  "cbrt(";
                    break;
                case "¹⁄ₓ":
                    input += "1/";
                    break;
                case "ʸ√x" :
                    input += "yroot";
                    break;
                case "EE":
                    input += "E";
                    break;
                case "x²":
                    input += "^2";
                    break;
                case "x³":
                    input += "^3";
                    break;
                case "Rad":
                    input += "Rad";
                    break;
                case "xʸ":
                    input += "^";
                    break;

                case "e":
                    break;
                case "\u03C0":
                    input += command;
                    break;
                case "eˣ":
                    input += "e^";
                    break;
                case "sinh":
                    input += "sinh(";
                    break;
                case "cosh":
                    input += "cosh(";
                    break;
                case "tanh":
                    input += "tanh(";
                    break;
                case "x!":
                    input += "!";
                    break;
                case "log₁₀":
                    input += "log";
                    break;
                case "10ˣ":
                    input += "10^";
                    break;


            }
        }

        private void evaluateExpression() {
            try {
                input = String.valueOf(eval(input));
            } catch (ArithmeticException ex) {
                input = "Error";
            }
        }

        private void handleNegation() {
            input = negateInput(input);
        }

        private String negateInput(String input) {
            if (input.isEmpty()) return input;

            String[] parts = input.split(" ");
            int lastIndex = parts.length - 1;
            String lastPart = parts[lastIndex];

            if (!lastPart.isEmpty() && Character.isDigit(lastPart.charAt(0))) {
                if (lastPart.charAt(0) == '-') {
                    parts[lastIndex] = lastPart.substring(1);
                } else {
                    parts[lastIndex] = "-" + lastPart;
                }
                return String.join(" ", parts);
            } else {
                return input;
            }
        }

        private void deleteLastCharacter() {
            if (!input.isEmpty()) {
                input = input.substring(0, input.length() - 1);
            }
        }

        private double eval(String expression) {
            return new Object() {
                int pos = -1, ch;

                void nextChar() {
                    ch = (++pos < expression.length()) ? expression.charAt(pos) : -1;
                }

                boolean isDigitChar() {
                    return Character.isDigit(ch);
                }

                double parse() {
                    nextChar();
                    double x = parseExpression();

                    if (pos < expression.length()) throw new RuntimeException("Unexpected: " + (char) ch);
                    return x;
                }

                double parseExpression() {
                    double x = parseTerm();
                    for (; ; ) {
                        if (eat('+')) x += parseTerm();
                        else if (eat('-')) x -= parseTerm();
                        else return x;
                    }
                }

                double parseTerm() {
                    double x = parseFactor();
                    for (; ; ) {
                        if (eat('×')) x *= parseFactor();
                        else if (eat('÷')) x /= parseFactor();
                        else if (eat('%')) x %= parseFactor();

                        else return x;
                        }
                        }


                double parseFactor() {
                    if (eat('+')) return parseFactor();
                    if (eat('-')) return -parseFactor();

                    double x;
                    int startPos = this.pos;
                    if (eat('(')) {
                        x = parseExpression();
                        eat(')');
                    } else if (isDigitChar() || ch == '.') {
                        while (isDigitChar() || ch == '.') nextChar();
                        x = Double.parseDouble(expression.substring(startPos, this.pos));
                    } else if (Character.isLetter(ch)) {
                        while (Character.isLetter(ch)) nextChar();
                        String func = expression.substring(startPos, this.pos);
                        x = parseFactor();
                        switch (func) {
                            case "sin":
                                x = Math.sin(Math.toRadians(x));
                                break;
                            case "cos":
                                x = Math.cos(Math.toRadians(x));
                                break;
                            case "tan":
                                x = Math.tan(Math.toRadians(x));
                                break;
                            case "sinh":
                                x = Math.asin(Math.toRadians(x));
                                break;
                            case "cosh":
                                x = Math.acos(Math.toRadians(x));
                                break;
                            case "tanh":
                                x = Math.atan(Math.toRadians(x));
                                break;
                            case "ln":
                                x = Math.log(x);
                                break;

                            case "^3":
                                x = Math.pow(x, 3);
                                break;
                            case "^2":
                                x = Math.pow(x, 2);
                                break;
                            case "Rad":
                                x = Math.toRadians(x);
                                break;
                            case "x!":
                                x = factorial((int) x);
                                break;
                            case "e^":
                                x = Math.exp(x);
                                break;
                            case "sqrt":
                                x = Math.sqrt(x);
                                break;

                            case "cbrt":
                                x = Math.cbrt(x);
                                break;
                            case "e":
                                x = Math.E;
                                break;
                            case "log":
                                x = Math.log10(x);
                                break;
                            case "1/":
                                x = 1/x;
                                break;
                            case "\u03C0":
                                x = Math.PI;
                                break;
                        }
                    } else {
                        throw new RuntimeException("Unexpected: " + (char) ch);
                    }

                    if (eat('^')) {
                        double y = parseFactor();
                        x = Math.pow(x, y);
                    }
                    if (eat('√')) {
                        double y = parseFactor();
                        x = Math.sqrt(y);}

                    return x;
                }

                boolean eat(int expected) {
                    while (ch == ' ') nextChar();
                    if (ch == expected) {
                        nextChar();
                        return true;
                    }
                    return false;
                } double factorial(int n) {
                    if (n == 0 || n == 1) {
                        return 1;
                    } else {
                        return n * factorial(n - 1);
                    }
                }
                    }.parse();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Calculator calculator = new Calculator();
            calculator.setVisible(true);
        });
    }


        }