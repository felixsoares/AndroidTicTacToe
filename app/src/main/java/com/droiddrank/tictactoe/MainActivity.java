package com.droiddrank.tictactoe;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private User user1, user2;

    private int TURNO_AUX = 1;
    private int TURNO = 1;
    private boolean over = false;

    private Button block1, block2, block3, block4, block5, block6, block7, block8, block9, restart;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        user1 = new User(1, getResources().getString(R.string.player_1_move));
        user2 = new User(2, getResources().getString(R.string.player_2_move));

        block1 = (Button) findViewById(R.id.bt_block1);
        block1.setOnClickListener(this);

        block2 = (Button) findViewById(R.id.bt_block2);
        block2.setOnClickListener(this);

        block3 = (Button) findViewById(R.id.bt_block3);
        block3.setOnClickListener(this);

        block4 = (Button) findViewById(R.id.bt_block4);
        block4.setOnClickListener(this);

        block5 = (Button) findViewById(R.id.bt_block5);
        block5.setOnClickListener(this);

        block6 = (Button) findViewById(R.id.bt_block6);
        block6.setOnClickListener(this);

        block7 = (Button) findViewById(R.id.bt_block7);
        block7.setOnClickListener(this);

        block8 = (Button) findViewById(R.id.bt_block8);
        block8.setOnClickListener(this);

        block9 = (Button) findViewById(R.id.bt_block9);
        block9.setOnClickListener(this);

        result = (TextView) findViewById(R.id.tv_show_result);
        restart = (Button) findViewById(R.id.bt_restart_game);

        /**
         * Restarts the game
         */
        restart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(MainActivity.this);
                builder.setTitle(R.string.app_name);
                builder.setMessage(R.string.restart_message);
                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                        clearAllGame();
                    }
                });
                builder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
                    }
                });

                Dialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    private void clearAllGame() {
        TURNO = user1.getId();
        result.setText("");
        over = false;

        block1.setText("");
        block2.setText("");
        block3.setText("");
        block4.setText("");
        block5.setText("");
        block6.setText("");
        block7.setText("");
        block8.setText("");
        block9.setText("");
    }

    @Override
    public void onClick(View view) {
        if (!over) {
            switch (view.getId()) {
                case R.id.bt_block1:
                    performClick(block1);
                    break;
                case R.id.bt_block2:
                    performClick(block2);
                    break;
                case R.id.bt_block3:
                    performClick(block3);
                    break;
                case R.id.bt_block4:
                    performClick(block4);
                    break;
                case R.id.bt_block5:
                    performClick(block5);
                    break;
                case R.id.bt_block6:
                    performClick(block6);
                    break;
                case R.id.bt_block7:
                    performClick(block7);
                    break;
                case R.id.bt_block8:
                    performClick(block8);
                    break;
                case R.id.bt_block9:
                    performClick(block9);
                    break;
            }

            verifyWinner();
            verifyDraw();
        }
    }

    private void verifyDraw() {
        if (hasText(block1, block2, block3, block4, block5, block6, block7, block8, block9) && !over) {
            over = true;
            setTextResult(getResources().getText(R.string.draw).toString());
        }
    }

    private boolean hasText(Button... buttons) {
        boolean hasText = true;

        for (Button button : buttons) {
            if (button.getText().toString().equals("")) {
                hasText = false;
                break;
            }
        }

        return hasText;
    }

    private void changeTurno() {
        TURNO_AUX = TURNO;
        if (TURNO == user1.getId()) {
            TURNO = user2.getId();
        } else {
            TURNO = user1.getId();
        }
    }

    private void verifyWinner() {
        if (verifySameText(block1, block2, block3)
                || verifySameText(block4, block5, block6)
                || verifySameText(block7, block8, block9)
                || verifySameText(block1, block4, block7)
                || verifySameText(block2, block5, block8)
                || verifySameText(block3, block6, block9)
                || verifySameText(block1, block5, block9)
                || verifySameText(block3, block5, block7)) {

            over = true;

            if (TURNO_AUX == user1.getId()) {
                setTextResult(getResources().getText(R.string.player_1_wins).toString());
            } else {
                setTextResult(getResources().getText(R.string.player_2_wins).toString());
            }

        }
    }

    private void setTextResult(String message) {
        result.setText(message);
    }

    private boolean verifySameText(Button btn1, Button btn2, Button btn3) {
        Boolean equal = false;

        if (btn1.getText().toString().equals(btn2.getText().toString())
                && btn1.getText().toString().equals(btn3.getText().toString())
                && !btn1.getText().toString().equals("")
                && !btn2.getText().toString().equals("")
                && !btn3.getText().toString().equals("")) {
            equal = true;
        }

        return equal;
    }

    private void performClick(Button button) {
        if (button.getText() == "") {
            if (TURNO == 1) {
                button.setText(user1.getSymbol());
            } else {
                button.setText(user2.getSymbol());
            }

            changeTurno();
        }
    }
}