package com.example.shubham.othelo;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements  View.OnClickListener {
LinearLayout rootlayout;
    ArrayList<LinearLayout> rows;
    TextView tvb,tvw;
    public OButton[][] board;
    public static  final int size=8;
    public  static int blackT=0,whiteT=0;
    public static int currentStatus;
    public static final int INCOMPLETE=0,BLACK_WON=1,WHITE_WON=2;
    public static final int BLACK=0,WHITE=1,NO_PLAYER=2;
    public static int currentPlayer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        rootlayout=findViewById(R.id.RootLayout);
        tvb=findViewById(R.id.blackTiles);
        tvw=findViewById(R.id.whiteTiles);
        setBoard();
    }

    public void setBoard() {
        board=new OButton[size][size];
        rows=new ArrayList<>();
        currentStatus=INCOMPLETE;
        currentPlayer=BLACK;
        blackT=0;whiteT=0;
        rootlayout.removeAllViews();
        for(int i=0;i<size;i++)
        {
            LinearLayout linearLayout=new LinearLayout(this);
            linearLayout.setOrientation(LinearLayout.HORIZONTAL);
            LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,0,1);
            linearLayout.setLayoutParams(layoutParams);
            rootlayout.addView(linearLayout);

            rows.add(linearLayout);
        }
        for(int i=0;i<size;i++)
        {
            for (int j=0;j<size;j++)
            {
                OButton button=new OButton(this);
                LinearLayout.LayoutParams layoutParams=new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.MATCH_PARENT,1);
                button.setLayoutParams(layoutParams);

                button.setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonbg));
                button.setTextSize(40);
                button.setOnClickListener(this);
                LinearLayout row=rows.get(i);
                row.addView(button);
                button.setXY(i,j);
                board[i][j]=button;
            }
        }
        board[3][3].setPlayer(WHITE,board,size);
        board[3][4].setPlayer(BLACK,board,size);
        board[4][3].setPlayer(BLACK,board,size);
        board[4][4].setPlayer(WHITE,board,size);
        setNOT();
    }


    @Override
    public void onClick(View v) {
        if(currentStatus==INCOMPLETE) {
            OButton button = (OButton) v;
            button.setPlayer(currentPlayer,board,size);
            setNOT();
            setGameStatus();
            tooglePlayer();
        }
    }

    private void setNOT() {
        if(blackT<10)
            tvb.setText("0"+blackT);
        else{
            tvb.setText(blackT);
        }
        if(whiteT<10)
            tvw.setText("0"+whiteT);
        else{
            tvw.setText(whiteT);
        }

    }

    private void tooglePlayer() {
        if(currentPlayer==BLACK)
            currentPlayer=WHITE;
        else {
            currentPlayer=BLACK;
        }
    }

    private void setGameStatus() {

    }

}
