package com.ee5453.calculator_project_1;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity implements OnClickListener {

    public final static String EXTRA_MESSAGE = "com.mycompany.myfirstapp.MESSAGE";
    private float op1 = 0;
    private float op2 = 0;
	/*
	 *
	 * Array of operands and operators for the purpose of parentheses
	 */

    private float[] ConditionalOperand = new float[6];

    private String[] conditionalOperatorArray = new String[8];

    private float[] operandArray = new float[10];

    private String[] operatorArray = new String[10];

    private static float result = 0;

    boolean bracketStatus = false;


    private EditText calcResult;
    // static variable to determine the number of brackets

    private static int count = 0;

    private static int operandCount = 0;

    private static int position = 0;

    private static int addTotempCount = 0;
    private static String tempString = "";

    String currentString = "";

    private static float memoryValue = 0;

    private float valueOperationMemory = 0;
	/*
	 * Declaring ERRORS
	 */

    static String errorDiv = "DIVISION BY 0 NOT DEFINED";

    static String errorPARANTHESES = "ERROR IN PARENTHESIS";

    static String errorMATH = "MATH ERROR";

    static String STATE_MEMORY = "Memory";

    static String STATE_TextField = "TextField";

    private static String ERROR = null;

    static String errorMemory = "NO CONTENTS IN MEMORY";



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
		/*
		 * getting values from the text field and buttons
		 */

        calcResult = (EditText) findViewById(R.id.displayResult);

        calcResult.setEnabled(false);

        int idList[] = { R.id.zero, R.id.one, R.id.two, R.id.three, R.id.numfour,
                R.id.numfive, R.id.numsix, R.id.numseven, R.id.numeight, R.id.numnine,
                R.id.add, R.id.sub, R.id.nummul, R.id.divide, R.id.square,
                R.id.button, R.id.equal, R.id.openBracket, R.id.closeBracket,
                R.id.cancel, R.id.memMC, R.id.memMMinus, R.id.memMPlus, R.id.memMR, R.id.memMS, R.id.backspace,
                R.id.CE, R.id.percentage, R.id.onebyx, R.id.sqroot, R.id.plusorminus, R.id.factorial,
                R.id.xpowery, R.id.yrootx, R.id.xcube,R.id.cuberootofx, R.id.log, R.id.tenpowerx,
                R.id.pi, R.id.mod};
		/*
		 * Adding listener to each button
		 */

        for (int id : idList)
        {

            View v = (View) findViewById(id);

            v.setOnClickListener(this);

        }

    }



    @Override

    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.

        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;

    }


    @Override

    public void onSaveInstanceState(Bundle savedInstanceState) {
        // Save the user's current Answer in memory

        System.out.println("In save class before closing the app");

        savedInstanceState.putString(STATE_TextField,calcResult.getText().toString().trim());

        savedInstanceState.putFloat(STATE_MEMORY, memoryValue);
        // Always call the superclass so it can save the view hierarchy state

        super.onSaveInstanceState(savedInstanceState);
    }



    public void onRestoreInstanceState(Bundle savedInstanceState) {

        super.onRestoreInstanceState(savedInstanceState);

        System.out.println("In Restore Class");
        // Restore state members from saved instance

        calcResult.setText(savedInstanceState.getString(STATE_TextField));


        memoryValue = savedInstanceState.getFloat(STATE_MEMORY);

        System.out.println("Saved value in memory: " + memoryValue);

    }


/*
	 * Method to Decide upon the mathematical function
	 */

    public void mathematicalOperation(String str)
    {

        try {

            int y = operandCount;

            System.out.println("Operand for position"+y+"\t" + tempString);

            if (tempString.equals("") && operandArray[position] != 0) {

                tempString = String.valueOf(operandArray[position]);

                System.out.println("After paranthesis tempStringValue "+tempString);

            }

            if (count != 0 || bracketStatus == true) {

                ConditionalOperand[count] = Float.parseFloat(tempString.trim());

                System.out.println("Operand of " + count + "is "
                        + ConditionalOperand[count]);

            }
            else {

                operandArray[y + addTotempCount] = Float.parseFloat(tempString
                        .trim());

                System.out.println("Operator of " + (y + addTotempCount)
                        + " is " + operandArray[y + addTotempCount]);

            }

            if (str != null) {

                System.out.println("operand op1 " + op1 + "operator str" + str);

                if (count != 0 || bracketStatus == true) {

                    conditionalOperatorArray[count] = str;

                    System.out.println("Operator of " + count + "is "
                            + conditionalOperatorArray[count]);

                    count = count + 1;

                }
                else {

                    operatorArray[y+addTotempCount] = str;

                    System.out.println("operator is " +(y + addTotempCount)
                            + operatorArray[y+addTotempCount]);

                    operandCount = operandCount + 1;

                }

            }

            tempString = "";

        } catch (Exception e) {

            calcResult.setText(errorMATH);

        }

    }

	/*
	 * Appending a string to be displayed
	 */

    public void enterValue(String str) {

        try {

            currentString = calcResult.getText().toString().trim();

            if (currentString.equals("0"))

                currentString = "";

            currentString += str;

            calcResult.setText(currentString);

        } catch (Exception e) {

            calcResult.setText(errorMATH);

        }

    }


    public void memoryFunction(int memoryOperation, float value) {

        switch (memoryOperation) {

            case R.id.memMC: {


                memoryValue = 0;

                System.out.println("Memory cleared " + memoryValue);

                break;
            }

            case R.id.memMPlus: {


                memoryValue = memoryValue + value;

                System.out.println("Memory added  after addition" + memoryValue);


                break;
            }

            case R.id.memMMinus: {


                memoryValue = memoryValue - value;

                System.out.println("Memory subtracted " + memoryValue);



                break;
            }

        }
    }



	/*
	 * Performing the mathematical operation
	 */

    public void calculateResult() {

        try {


            if (count == 0 || bracketStatus == false) {

                int x = operandCount;

                System.out.println("x = " + operandCount);

                for (int i = x-1; i >= 0; i--) {


                    System.out.println("Looping " +i+"\t"+ operandArray[i] + "\t"
                            + operatorArray[i]);

                }

                if (!tempString.equals("")) {

                    System.out.println("inside not null temp string "
                            + tempString);

                    op2 = Float.parseFloat(tempString.trim());


                    System.out
                            .println("First Call" + op2 + "\t"
                                    + operatorArray[x-1] + "\t"
                                    + operandArray[x-1]);

                    operandArray[x - 1] = recurssiveOperationCall(
                            operandArray[x-1], op2, operatorArray[x-1]);

                    System.out.println("Result after first call: "
                            + operandArray[x - 1]);


                    for (int i = x - 1; i > 0; i--) {


                        System.out.println("Call i" + operandArray[i] + "\t"
                                + operandArray[i - 1] + "\t"
                                + operatorArray[i - 1]);

                        operandArray[i - 1] = recurssiveOperationCall(
                                operandArray[i - 1], operandArray[i],
                                operatorArray[i - 1]);

                    }


                } else {

                    System.out.println("else");

                    for (int i = x; i > 0; i--)
                    {

                        System.out.println("Else Call i" + operandArray[i]
                                + "\t" + operandArray[i - 1] + "\t"
                                + operatorArray[i - 1]);

                        operandArray[i - 1] = recurssiveOperationCall(
                                operandArray[i - 1], operandArray[i],
                                operatorArray[i - 1]);

                    }


                }

            } else
            {

                ERROR = errorPARANTHESES;
            }


            if (ERROR == null) {


                calcResult.setText(String.valueOf(operandArray[0]));

                tempString = String.valueOf(operandArray[0]);

                System.out.println("Final Result: " + tempString);


            } else {

                calcResult.setText(ERROR);

            }

            op1 = 0;

            op2 = 0;

            result = 0;

            count = 0;

            operandCount = 0;

            ERROR = null;

            bracketStatus = false;

            addTotempCount = 0;

            position = 0;

            for (int i = 0; i < operandArray.length; i++) {

                operandArray[i] = 0;

            }

            for (int i = 0; i < operatorArray.length; i++) {

                operatorArray[i] = null;

            }

            for (int i = 0; i < ConditionalOperand.length; i++) {

                ConditionalOperand[i] = 0;

            }

            for (int i = 0; i < conditionalOperatorArray.length; i++) {

                conditionalOperatorArray[i] = null;

            }

        } catch (Exception e) {

            calcResult.setText(errorMATH);

        }

    }


    private float recurssiveOperationCall(float operand1, float operand2,
                                          String functionOperator) {

        if (functionOperator.equals("+")) {

            try {

                result = operand1 + operand2;

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }

        if (functionOperator.equals("-")) {

            try {

                result = operand1 - operand2;

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }

        if (functionOperator.equals("*")) {

            try {

                result = operand1 * operand2;

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }

        if (functionOperator.equals("%")) {

            try {

                result = operand1*(operand2/100);

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }

        if (functionOperator.equals("10^x")) {

            try {

                result = (float) Math.pow(10.00, operand1);

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }
        if (functionOperator.equals("1/x")) {

            try {

                result = 1/operand1;

            } catch (Exception e) {

                ERROR = errorMATH;

            }

        }


        if (functionOperator.equals("/")) {

            try {

                if (op2 == 0) {

                    ERROR = errorDiv;

                } else {

                    result = operand1 / operand2;

                }

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("x^2")) {

            try {

                result = (float) Math.pow(operand1, 2);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("x^y")) {

            try {

                result = (float) Math.pow(operand1, operand2);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("y√x")) {

            try {

                result = (float) Math.pow(operand2, (1/operand1));

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("sqrt")) {

            try {

                result = (float) Math.sqrt(operand1);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("log")) {

            try {

                result = (float) Math.log10(operand1);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("x^3")) {

            try {

                result = (float) Math.pow(operand1, 3);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("n!")) {

            try {
                int fact=1;
                for(int i=1; i<=operand1; i++ )
                    fact= fact*i;
                result = fact;

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("3√x")) {

            try {

                result = (float) Math.pow(operand1, 0.3333333333);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("pi")) {

            try {

                result = (operand1*(22/7));

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        if (functionOperator.equals("mod")) {

            try {

                result = (operand1%operand2);

            } catch (Exception e) {

                ERROR = errorMATH;

            }
        }

        return result;

    }

	/*
	 * abstract method implemented
	 *
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */



    @Override

    public void onClick(View arg0) {


        switch (arg0.getId()) {


            case R.id.cancel: {

                calcResult.setText("0");

                op1 = 0;

                op2 = 0;

                result = 0;

                count = 0;

                operandCount = 0;

                ERROR = null;

                bracketStatus = false;

                tempString = "";

                addTotempCount = 0;

                position = 0;

                for (int i = 0; i < operandArray.length; i++) {

                    operandArray[i] = 0;

                }

                for (int i = 0; i < operatorArray.length; i++) {

                    operatorArray[i] = null;

                }

                for (int i = 0; i < ConditionalOperand.length; i++) {

                    ConditionalOperand[i] = 0;

                }

                for (int i = 0; i < conditionalOperatorArray.length; i++) {

                    conditionalOperatorArray[i] = null;

                }

                break;

            }

            case R.id.add:

                mathematicalOperation("+");

                enterValue("+");

                break;

            case R.id.sub:

                mathematicalOperation("-");

                enterValue("-");

                break;

            case R.id.nummul:

                mathematicalOperation("*");

                enterValue("*");

                break;

            case R.id.divide:

                mathematicalOperation("/");

                enterValue("/");

                break;

            case R.id.percentage:

                mathematicalOperation("%");

                enterValue("%");

                break;


            case R.id.square:

                mathematicalOperation("x^2");

                enterValue("^2");

                break;
            case R.id.sqroot:

                mathematicalOperation("sqrt");

                enterValue("sqrt");

                break;

            case R.id.yrootx:

                mathematicalOperation("y√x");

                enterValue("√");

                break;

            case R.id.xpowery:

                mathematicalOperation("x^y");

                enterValue("^");

                break;

            case R.id.log:

                mathematicalOperation("log");

                enterValue("log");

                break;

            case R.id.xcube:

                mathematicalOperation("x^3");

                enterValue("^3");

                break;

            case R.id.cuberootofx:

                mathematicalOperation("3√x");

                enterValue("3√x");

                break;

            case R.id.tenpowerx:

                mathematicalOperation("10^x");

                enterValue("10^");

                break;

            case R.id.onebyx:

                mathematicalOperation("1/x");

                enterValue("1/");

                break;

            case R.id.factorial:

                mathematicalOperation("n!");

                enterValue("!");

                break;

            case R.id.pi:

                mathematicalOperation("pi");

                enterValue("π");

                break;

            case R.id.mod:

                mathematicalOperation("mod");

                enterValue("mod");

                break;


            case R.id.equal:

                calculateResult();

                break;


            case R.id.openBracket: {

                ConditionalOperand[count] = op1;

                enterValue("(");

                bracketStatus = true;

                position = operandCount;

                op1 = 0;

                count = count + 1;

                addTotempCount = addTotempCount + 1;

                break;

            }

            case R.id.closeBracket: {

                bracketStatus = false;

                System.out.println("Call to Paranthesis function");

                paranthesisResult();

                count = 0;

                tempString="";

                enterValue(")");

                addTotempCount=addTotempCount-1;

                break;


            }

            case R.id.memMC: {

                System.out.println("in memory clear");

                memoryFunction(R.id.memMC, 0);

                break;

            }

            case R.id.memMS: {

                System.out.println("in memory save");


                memoryValue = Float.parseFloat(calcResult.getText().toString()
                        .trim());




                break;

            }

            case R.id.memMR: {

                System.out.println("in memory read");

                if (memoryValue == 0) {

                    ERROR = errorMemory;

                    calcResult.setText(ERROR);

                } else {

                    enterValue(String.valueOf(memoryValue));

                    tempString = String.valueOf(memoryValue);

                    System.out.println("Memory storing value" + tempString + "in temp String");

                }

                break;

            }

            case R.id.memMPlus: {

                System.out.println("in memory add");

                valueOperationMemory = Float.parseFloat(calcResult.getText()
                        .toString().trim());

                memoryFunction(R.id.memMPlus, valueOperationMemory);

                break;

            }

            case R.id.memMMinus: {

                System.out.println("in memory minus");

                valueOperationMemory = Float.parseFloat(calcResult.getText()
                        .toString().trim());

                memoryFunction(R.id.memMMinus, valueOperationMemory);

                break;

            }

            default: {

                String number = ((Button) arg0).getText().toString().trim();

                tempString += number;

                enterValue(number);

                break;

            }

        }


    }

	/*
	 * method call for the operation within the parenthesis
	 */

    private void paranthesisResult() {

        int tempCount = count;

        if (addTotempCount != 0) {

            try {

                if (tempString != null) {

                    op2 = Float.parseFloat(tempString.trim());

                }

                System.out.println("Paranthesis First Call" + op2 + "\t"
                        + ConditionalOperand[tempCount - 1] + "\t"
                        + conditionalOperatorArray[tempCount - 1]);

                for (int i = tempCount - 1; i > 0; i--) {


                    System.out.println("Looping" + ConditionalOperand[i] + "\t"
                            + conditionalOperatorArray[i]);

                }

                ConditionalOperand[tempCount - 1] = recurssiveOperationCall(
                        ConditionalOperand[tempCount - 1], op2,
                        conditionalOperatorArray[tempCount - 1]);


                System.out.println("Result after first call: "
                        + ConditionalOperand[tempCount - 1]);


                for (int i = tempCount - 1; i > 1; i--) {

                    System.out.println("Call i" + ConditionalOperand[i] + "\t"
                            + ConditionalOperand[i - 1] + "\t"
                            + conditionalOperatorArray[i - 1]);

                    ConditionalOperand[i - 1] = recurssiveOperationCall(
                            ConditionalOperand[i - 1], ConditionalOperand[i],
                            conditionalOperatorArray[i - 1]);

                }

                System.out.println("Final paranthesis result: "
                        + ConditionalOperand[1] + " put in position "
                        + position);

                operandArray[position] = ConditionalOperand[1];

            } catch (Exception e) {

                calcResult.setText(errorMATH);

            }


        } else {

            calcResult.setText(errorPARANTHESES);

        }

    }

}
