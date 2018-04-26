package com.example.CalculatorApp;

import android.widget.Button;
import android.widget.Toast;

import java.math.BigDecimal;

import static java.lang.Character.isDigit;

public class AdvancedCalculatorCore extends SimpleCalculatorCore {
    Button sin, cos, tan, ln, sqrt, squared, power, log;

    @Override
    protected void setOperationType(char mark) {
        super.setOperationType(mark);
        isSquared = false;

        if (mark == '^') {
            isSquared = true;
        }
    }

    @Override
    protected void performOperation(String value) {
        super.performOperation(value);

        if (!value.endsWith("^") && !value.endsWith(".")) {
            if (isSquared) {
                if (firstValue == null && secondValue == null) {
                    if (value.startsWith("(")) {
                        firstValue = new BigDecimal(value.substring(value.indexOf("(") + 1, value.indexOf(")")));
                    } else {
                        firstValue = new BigDecimal(value.substring(0, value.indexOf("^")));
                    }
                    if (value.endsWith(")")) {
                        secondValue = new BigDecimal(value.substring(value.indexOf("^") + 3, value.length() - 1));
                    } else {
                        secondValue = new BigDecimal(value.substring(value.indexOf("^") + 1, value.length()));
                    }
                }

                try {
                    firstValue = new BigDecimal(Math.pow(firstValue.doubleValue(), secondValue.doubleValue()));
                } catch (NumberFormatException e) {
                    firstValue = new BigDecimal(0);
                    Toast.makeText(this, "Nieskończoność!", Toast.LENGTH_SHORT).show();
                    resultText.setText("");
                }
                if (firstValue.stripTrailingZeros().toString().length() > 5) {
                    resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
                } else {
                    resultText.setText(String.valueOf(firstValue.stripTrailingZeros()));
                }
                equals = true;
            }
        } else {
            Toast.makeText(this, "Użyto nieprawidłowego formatu!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void backspaceOperation(String value) {
        super.backspaceOperation(value);

        if (!value.equals("")) {
            if (isDigit(value.charAt(value.length() - 1))) {
                resultText.setText(value.substring(0, value.length() - 1));
            } else {
                if (value.endsWith("^")) {
                    isSquared = false;
                    resultText.setText(value.substring(0, value.length() - 1));
                }
            }
        } else {
            Toast.makeText(this, "Brak elementów do usunięcia!", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void deleteOperation() {
        super.deleteOperation();

        isSquared = false;
    }


    protected void sinusOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            firstValue = new BigDecimal(Math.sin(Math.toRadians(firstValue.doubleValue())));

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void cosinusOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            firstValue = new BigDecimal(Math.cos(Math.toRadians(firstValue.doubleValue())));

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void tangensOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            firstValue = new BigDecimal(Math.tan(Math.toRadians(firstValue.doubleValue())));

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void lnOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            try {
                firstValue = new BigDecimal(Math.log(firstValue.doubleValue()));
            } catch (NumberFormatException e) {
                firstValue = new BigDecimal(0);
                Toast.makeText(this, "Użyto nieprawidłowego formatu!", Toast.LENGTH_SHORT).show();
                resultText.setText("");
            }

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void logOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            try {
                firstValue = new BigDecimal(Math.log10(firstValue.doubleValue()));
            } catch (NumberFormatException e) {
                firstValue = new BigDecimal(0);
                Toast.makeText(this, "Użyto nieprawidłowego formatu!", Toast.LENGTH_SHORT).show();
                resultText.setText("");
            }

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }

    }

    protected void sqrtOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }
            try {
                firstValue = new BigDecimal(Math.sqrt(firstValue.doubleValue()));
            } catch (NumberFormatException e) {
                firstValue = new BigDecimal(0);
                Toast.makeText(this, "Użyto nieprawidłowego formatu!", Toast.LENGTH_SHORT).show();
                resultText.setText("");
            }

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void squaredOperation(String value) {
        if (!value.equals("") && !value.contains(" + ") && !value.contains(" - ") && !value.contains(" * ") && !value.contains(" / ") && !value.contains("^")) {
            if (value.startsWith("(-")) {
                firstValue = new BigDecimal(value.substring(1, value.length() - 1));
            } else {
                firstValue = new BigDecimal(value.replace(",", "."));
            }

            firstValue = new BigDecimal(Math.pow(firstValue.doubleValue(), 2));

            if (firstValue.stripTrailingZeros().toString().length() > 5) {
                resultText.setText(String.valueOf(decimalFormat.format(firstValue.stripTrailingZeros())));
            } else {
                resultText.setText(String.valueOf(firstValue.stripTrailingZeros().toPlainString().replace(",", ".")));
            }
            equals = true;
        }
    }

    protected void powerOperation(String value) {
        if (!value.equals("") && !equals) {
            if (!isMul && !isSub && !isAdd && !isDiv && !isSquared) {
                resultText.setText(String.valueOf(value + "^"));
                setOperationType('^');
            } else if ((isSub || isAdd || isDiv) && !isDigit(value.charAt(value.length() - 1))) {
                resultText.setText(String.valueOf(value.substring(0, value.length() - 3) + "^"));
                setOperationType('^');
            }
        }
    }

}
