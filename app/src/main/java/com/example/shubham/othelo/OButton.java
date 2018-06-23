package com.example.shubham.othelo;

import android.content.Context;
import android.graphics.drawable.GradientDrawable;
import android.widget.Button;

/**
 * Created by shubham on 6/22/2018.
 */

public class OButton extends android.support.v7.widget.AppCompatButton {
   private int player=MainActivity.NO_PLAYER;
   private  int x,y;
   private boolean validStatus=false;
   public int blackstatus=0,whitestatus=0;

   public OButton(Context context) {
        super(context);
    }
    public void setXY(int i,int j)
    {
        x=i;
        y=j;
    }
    public void setPlayer(int currentPlayer,OButton board[][],int size) {
        this.player=currentPlayer;
        blackstatus=0;
        whitestatus=0;
        if(player==MainActivity.BLACK){
            setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
            System.out.println(MainActivity.BLACK);
            aroundBlackStatus(board,size);
        }
        else if(player==MainActivity.WHITE){
           setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
            System.out.println(MainActivity.WHITE);
            aroundWhiteStatus(board,size);
        }
        setEnabled(false);
        checkValidStatus(currentPlayer,board,size);
        setValidStatus(board,size);

    }



    private void aroundWhiteStatus(OButton[][] board, int size) {
        int  i,j;
        for(i=this.x-1;i<this.x+2;i++)
        {
            for (j=this.y-1;j<this.y+2;j++)
            {
                if(i>=0&&i<size&&j>=0&&j<size&&board[i][j].isEmpty())
                    board[i][j].whitestatus++;

            }
        }
    }

    private void aroundBlackStatus(OButton[][] board, int size) {
        int  i,j;
        for(i=this.x-1;i<this.x+2;i++)
        {
            for (j=this.y-1;j<this.y+2;j++)
            {
                if(i>=0&&i<size&&j>=0&&j<size&&board[i][j].isEmpty())
                    board[i][j].blackstatus++;

            }
        }
    }


    public void checkValidStatus(int currentPlayer, OButton[][] board, int size) {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
              int status,otherPlayer,a;
              if(currentPlayer==MainActivity.BLACK){
                  status=board[i][j].blackstatus;
                  otherPlayer=MainActivity.WHITE;
              }
              else {
                  status=board[i][j].whitestatus;
                  otherPlayer=MainActivity.BLACK;
              }
              if(status!=0) {
                 for(int b=0;b<8;b++) {
                    for (int k = 1; j - k >= 0; k++) {
                        if(b==0) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==1) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==2) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==3) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==4) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==5) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else if(b==6) {
                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                        else {

                            if (board[i][j - k].isEmpty() || (board[i][j - k].player == otherPlayer && k == 1)) {
                                a = 0;
                                break;
                            }
                            else if (board[i][j].player == otherPlayer) {
                                a = 1;
                                break;
                            }
                        }
                     }
                  }
              }
            }
        }
    }

    private void setValidStatus( OButton[][] board, int size) {
        for(int i=0;i<size;i++)
        {
            for(int j=0;j<size;j++)
            {
                if(board[i][j].validStatus){
                    board[i][j].validStatus=false;
                    if(board[i][j].isEmpty())
                    {
                        board[i][j].setText(".");
                    }
                }

            }
        }
    }
    public boolean isEmpty()
    {
        return this.getPlayer()==MainActivity.NO_PLAYER;
    }

    private int getPlayer() {
        return this.player;
    }
}
