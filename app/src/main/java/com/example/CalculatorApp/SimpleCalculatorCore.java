package com.example.CalculatorApp;

import android.app.Activity;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import static java.lang.Character.isDigit;

public class SimpleCalculatorCore extends Activity {
    Button zero, one, two, three, four, five, six,
            seven, eight, nine, point, addition,
            multiplication, division, subtraction,
            backspace, equal, delete, plus_minus;

    TextView resultText;

    protected BigDecimal firstValue = null, secondValue = null;
    protected boolean isAdd = false, isSub = false, isMul = false, isDiv = false, equals = false, isSquared = false;
    protected DecimalFormat decimalFormat = new DecimalFormat("0.#####E0");

    protected void setOperationType(char mark) {
        isAdd = false;
        isSub = false;
        isMul = false;
        isDiv = false;
        if (mark == '+') {
            isAdd = true;
        } else if (mark == '-') {
            isSub = true;
        } else if (mark == '*') {
            isMul = true;
        } else if (mark == '/') {
            isDiv = true;
        }
    }

    protected void performOperation(String value) {
        if (!value.endsWith(" + ") && !value.endsWith(" - ") && !value.endsWith(" * ") && !value.endsWith(" / ") && !value.endsWith("^")) {
            if (isAdd) {
                if (firstValue == null && secondValue == null) {
                    if (value.startsWith("(")) {
                        firstValue = new BigDecimal(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
                    } else {
                        firstValue = new BigDecimal(value.substring(0, value.indexOf("+") - 1));
                    }
                    if (value.endsWith(")")) {
                        secondValue = new BigDecimal(value.substring(value.indexOf("+") + 3, value.length() - 1));
                    } else {
                        secondValue = new BigDecimal(value.substring(value.indexOf("+") + 2, value.length()));
                    }
                }
                firstValue = firstValue.add(secondValue);
                if (firstValue.stripTrailingZeros().toString().length() > 5) {
                    resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
                } else {
                    resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
                }
            } else if (isSub) {
                if (firstValue == null && secondValue == null) {
                    if (value.startsWith("(")) {
                        firstValue = new BigDecimal(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
                    } else {
                        firstValue = new BigDecimal(value.substring(0, value.indexOf("-") - 1));
                    }
                    if (value.endsWith(")")) {
                        secondValue = new BigDecimal(value.substring(value.lastIndexOf("-"), value.length() - 1));
                    } else {
                        secondValue = new BigDecimal(value.substring(value.lastIndexOf("-") + 2, value.length()));
                    }
                }
                firstValue = firstValue.subtract(secondValue);
                if (firstValue.stripTrailingZeros().toString().length() > 5) {
                    resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
                } else {
                    resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
                }
            } else if (isMul) {
                if (firstValue == null && secondValue == null) {
                    if (value.startsWith("(")) {
                        firstValue = new BigDecimal(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
                    } else {
                        firstValue = new BigDecimal(value.substring(0, value.indexOf("*") - 1));
                    }
                    if (value.endsWith(")")) {
                        secondValue = new BigDecimal(value.substring(value.indexOf("*") + 3, value.length() - 1));
                    } else {
                        secondValue = new BigDecimal(value.substring(value.indexOf("*") + 2, value.length()));
                    }
                }
                firstValue = firstValue.multiply(secondValue);
                if (firstValue.stripTrailingZeros().toString().length() > 5) {
                    resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
                } else {
                    resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
                }
            } else if (isDiv) {
                if (firstValue == null && secondValue == null) {
                    if (value.startsWith("(")) {
                        firstValue = new BigDecimal(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
                    } else {
                        firstValue = new BigDecimal(value.substring(0, value.indexOf("/") - 1));
                    }
                    if (value.endsWith(")")) {
                        secondValue = new BigDecimal(value.substring(value.indexOf("/") + 3, value.length() - 1));
                    } else {
                        secondValue = new BigDecimal(value.substring(value.indexOf("/") + 2, value.length()));
                    }
                }
                try {
                    firstValue = firstValue.divide(secondValue, 10, BigDecimal.ROUND_UP);
                    if (firstValue.stripTrailingZeros().toString().length() > 5) {
                        resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
                    } else {
                        resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
                    }
                } catch (ArithmeticException e) {
                    Toast.makeText(this, "Nie można dzielić przez zero!", Toast.LENGTH_SHORT).show();
                    resultText.setText("");
                }
            }
            equals = true;
        } else {
            Toast.makeText(this, "Użyto nieprawidłowego formatu!", Toast.LENGTH_SHORT).show();
            resultText.setText("");
        }
    }

    protected void addingOperation(String value) {
        if (!value.equals("") && !equals) {
            if (!isAdd && !isSub && !isMul && !isDiv && !isSquared) {
                resultText.setText(String.valueOf(value + " + "));
                setOperationType('+');
            } else if ((isSub || isMul || isDiv) && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 3) + " + "));
                setOperationType('+');
            } else if (isSquared && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 1) + " + "));
                setOperationType('+');
            }
        }
    }

    protected void subtractionOperation(String value) {
        if (!value.equals("") && !equals) {
            if (!isSub && !isAdd && !isMul && !isDiv && !isSquared) {
                resultText.setText(String.valueOf(value + " - "));
                setOperationType('-');
            } else if ((isAdd || isMul || isDiv) && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 3) + " - "));
                setOperationType('-');
            } else if (isSquared && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 1) + " - "));
                setOperationType('-');
            }
        }
    }

    protected void multiplicationOperation(String value) {
        if (!value.equals("") && !equals) {
            if (!isMul && !isSub && !isAdd && !isDiv && !isSquared) {
                resultText.setText(String.valueOf(value + " * "));
                setOperationType('*');
            } else if ((isSub || isAdd || isDiv) && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 3) + " * "));
                setOperationType('*');
            } else if (isSquared && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 1) + " * "));
                setOperationType('*');
            }
        }
    }

    protected void divisionOperation(String value) {
        if (!value.equals("") && !equals) {
            if (!isDiv && !isSub && !isMul && !isAdd && !isSquared) {
                resultText.setText(String.valueOf(value + " / "));
                setOperationType('/');
            } else if ((isSub || isMul || isAdd) && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 3) + " / "));
                setOperationType('/');
            } else if (isSquared && !isDigit(value.charAt(value.length() - 1)) && !value.endsWith(")")) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 1) + " / "));
                setOperationType('/');
            }
        }
    }

    protected void deleteOperation() {
        resultText.setText("");
        firstValue = null;
        secondValue = null;
        equals = false;
        isAdd = false;
        isSub = false;
        isMul = false;
        isDiv = false;
    }

    protected void backspaceOperation(String value) {
        if (!value.equals("") && !value.endsWith("^")) {
            if (isDigit(value.charAt(value.length() - 1)) || value.endsWith(".") || value.contains("E")) {
                resultText.setText(value.substring(0, value.length() - 1));
            } else if (value.endsWith(")")) {

            } else {
                if (value.endsWith(" + ")) {
                    isAdd = false;
                    resultText.setText(value.substring(0, value.length() - 3));
                } else if (value.endsWith(" - ")) {
                    isMul = false;
                    resultText.setText(value.substring(0, value.length() - 3));
                } else if (value.endsWith(" * ")) {
                    isMul = false;
                    resultText.setText(value.substring(0, value.length() - 3));
                } else if (value.endsWith(" / ")) {
                    isDiv = false;
                    resultText.setText(value.substring(0, value.length() - 3));
                }

            }
        } else {
            Toast.makeText(this, "Brak elementów do usunięcia!", Toast.LENGTH_SHORT).show();
        }
    }

    protected void insertPoint(String value) {
        if (value.equals("") || equals) {
            resultText.setText("0.");
            equals = false;
        } else if (!value.contains(".") && !value.endsWith(".") && !value.endsWith("+") && !value.endsWith("-") && !value.endsWith("*") && !value.endsWith("/") && !value.endsWith(")") && !value.contains("E")) {      //Pierwsza czesc rownania
            resultText.setText(String.valueOf(value + "."));
        } else if ((value.contains("+") || value.contains("-") || value.contains("*") || value.contains("/") || value.contains("^")) && isDigit(value.charAt(value.length() - 1)) && !value.contains("E")) {           //Druga czesc rownania
            if (value.contains(" + ")) {
                if (!(value.substring(value.indexOf("+"), value.length() - 1)).contains(".")) {
                    resultText.setText(String.valueOf(value + "."));
                }
            } else if (value.contains(" - ")) {
                if (!(value.substring(value.indexOf("-"), value.length() - 1)).contains(".")) {
                    resultText.setText(String.valueOf(value + "."));
                }
            } else if (value.contains(" * ")) {
                if (!(value.substring(value.indexOf("*"), value.length() - 1)).contains(".")) {
                    resultText.setText(String.valueOf(value + "."));
                }
            } else if (value.contains(" / ")) {
                if (!(value.substring(value.indexOf("/"), value.length() - 1)).contains(".")) {
                    resultText.setText(String.valueOf(value + "."));
                }
            } else if (value.contains("^")) {
                if (!(value.substring(value.indexOf("^"), value.length() - 1)).contains(".")) {
                    resultText.setText(String.valueOf(value + "."));
                }
            }
        }
    }

    protected void reverseNumber(String value) {
        if (!value.equals("") && !equals) {
            if (!value.startsWith("(-") && !value.contains("+") && !value.contains("-") && !value.contains("*") && !value.contains("/") && !value.contains(" ") && !value.contains("^")) {
                resultText.setText(String.valueOf("(-" + value + ")"));
            } else if (value.startsWith("(-") && !value.contains(" ") && !value.contains("^")) {
                resultText.setText(String.valueOf(value.substring(2, value.length() - 1)));
            } else if (value.contains("+") || value.contains(" - ") || value.contains("*") || value.contains("/") && value.contains(" ") && !value.contains("^")) {
                if (isDigit(value.charAt(value.length() - 1))) {
                    if (value.contains(" + ")) {
                        if (!value.substring(value.lastIndexOf("+") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("+") + 2) + "(-" + value.substring(value.lastIndexOf("+") + 2, value.length()) + ")"));
                        }
                    } else if (value.contains(" - ")) {
                        if (!value.substring(value.lastIndexOf("-") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("-") + 2) + "(-" + value.substring(value.lastIndexOf("-") + 2, value.length()) + ")"));
                        }
                    } else if (value.contains(" * ")) {
                        if (!value.substring(value.lastIndexOf("*") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("*") + 2) + "(-" + value.substring(value.lastIndexOf("*") + 2, value.length()) + ")"));
                        }
                    } else if (value.contains(" / ")) {
                        if (!value.substring(value.lastIndexOf("/") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("/") + 2) + "(-" + value.substring(value.lastIndexOf("/") + 2, value.length()) + ")"));
                        }
                    }
                } else if (value.endsWith(")")) {
                    if (value.contains(" + ")) {
                        if (value.substring(value.lastIndexOf("+") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("+") + 2) + value.substring(value.lastIndexOf("(") + 2, value.length() - 1)));
                        }
                    } else if (value.contains(" - ") && !value.contains(" + ") && !value.contains(" * ") && !value.contains(" / ")) {
                        if (value.startsWith("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("-") + -1) + value.substring(value.lastIndexOf("(") + 2, value.length() - 1)));
                        } else {
                            if (value.substring(value.indexOf("-") + 2, value.length()).contains("(-")) {
                                resultText.setText(String.valueOf(value.substring(0, value.indexOf("-") + 2) + value.substring(value.lastIndexOf("(") + 2, value.length() - 1)));
                            }
                        }
                    } else if (value.contains(" * ")) {
                        if (value.substring(value.lastIndexOf("*") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("*") + 2) + value.substring(value.lastIndexOf("(") + 2, value.length() - 1)));
                        }
                    } else if (value.contains(" / ")) {
                        if (value.substring(value.lastIndexOf("/") + 2, value.length()).contains("(-")) {
                            resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("/") + 2) + value.substring(value.lastIndexOf("(") + 2, value.length() - 1)));
                        }
                    }
                }
            } else if (value.contains("^")) {
                if (isDigit(value.charAt(value.length() - 1))) {
                    resultText.setText(String.valueOf(value.substring(0, value.lastIndexOf("^") + 1) + "(-" + value.substring(value.lastIndexOf("^") + 1, value.length()) + ")"));
                }
            }
        }
    }
}
