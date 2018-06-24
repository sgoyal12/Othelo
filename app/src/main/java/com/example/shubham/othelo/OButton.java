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
        validStatus=false;
        if(player==MainActivity.BLACK){
            setText("");
            setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
            aroundBlackStatus(board,size);

        }
        else if(player==MainActivity.WHITE){
            setText("");
            setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
            aroundWhiteStatus(board,size);

        }
        perform(board,size);
        setEnabled(false);
        checkValidStatus(currentPlayer,board,size);
        setValidStatus(board,size);

    }
    private int [] check(OButton board[][],int size,int currentPlayer){
        int i,j;
        i=this.x;
        j=this.y;

        int dir[]=new int[size];
        for(int b=0;b<8;b++) {
            for (int k = 1;; k++) {

                if(b==0) {
                    if(j-k>=0) {
                        if (board[i][j - k].isEmpty() || (board[i][j - k].player ==currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        } else if (board[i][j - k].player == currentPlayer) {
                            dir[b] = 1;
                            break;
                        }
                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==1) {
                    if(i-k>=0&&j-k>=0)
                    {
                        if (board[i-k][j-k].isEmpty() || (board[i-k][j-k].player == currentPlayer && k == 1)) {
                            dir[b] = 0;
                            break;
                        }
                        else if (board[i-k][j-k].player == currentPlayer) {
                            dir[b]= 1;
                            break;
                        }


                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==2) {
                    if(i-k>=0)
                    {
                        if (board[i-k][j].isEmpty() || (board[i-k][j].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i-k][j].player == currentPlayer) {
                            dir[b] = 1;
                            break;
                        }


                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==3) {
                    if(i-(k)>=0&&j+(k)<size)
                    {
                        if (board[i-k][j+k].isEmpty() || (board[i-k][j+k].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i-k][j+k].player == currentPlayer) {
                            dir[b] = 1;
                            break;
                        }

                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==4) {
                    if(j+(k)<size)
                    {
                        if (board[i][j+k].isEmpty() || (board[i][j+k].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i][j+k].player == currentPlayer) {
                            dir[b]= 1;
                            break;
                        }
                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==5) {
                    if(i+(k)<size&&j+(k)<size)
                    {
                        if (board[i+k][j+k].isEmpty() || (board[i+k][j+k].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i+k][j+k].player == currentPlayer) {
                            dir[b]= 1;
                            break;
                        }

                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else if(b==6) {
                    if(i+(k)<size)
                    {
                        if (board[i+k][j].isEmpty() || (board[i+k][j].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i+k][j].player == currentPlayer) {
                            dir[b]= 1;
                            break;
                        }

                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
                else {
                    if(i+(k)<size&&j-(k)>=0)
                    {
                        if (board[i+k][j-k].isEmpty() || (board[i+k][j-k].player == currentPlayer && k == 1)) {
                            dir[b]= 0;
                            break;
                        }
                        else if (board[i+k][j-k].player == currentPlayer) {
                            dir[b] = 1;
                            break;
                        }

                    }
                    else{
                        dir[b]=0;
                        break;
                    }
                }
            }
        }
       return dir;
    }
    private void perform(OButton board[][],int size) {
        int dir[]=new int[size];
        dir=this.check(board,size,this.player);
        int otherPlayer,currentPlayer,i,j;
        i=this.x;
        j=this.y;
        if(this.player==MainActivity.BLACK){
            otherPlayer=MainActivity.WHITE;
            currentPlayer=MainActivity.BLACK;
        }
        else {
            otherPlayer=MainActivity.BLACK;
            currentPlayer=MainActivity.WHITE;
        }
          for(int b=0;b<size;b++)
          {
              if(dir[b]!=0)
              {
                  if(b==0)
                  {
                      for(int k=1;board[i][j-k].player!=currentPlayer;k++)
                      {
                          if(j-k>=0) {
                              board[i][j - k].player = currentPlayer;
                              if (currentPlayer == MainActivity.BLACK)
                                  board[i][j - k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                              else if (currentPlayer == MainActivity.WHITE)
                                  board[i][j - k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                              board[i][j - k].setAround(currentPlayer, board, size);
                          }
                      }
                  }
                  else if(b==1)
                  {
                      for(int k=1;board[i-k][j-k].player!=currentPlayer;k++)
                      {
                          if(i-k>=0&&j-k>=0) {
                              board[i - k][j - k].player = currentPlayer;
                              if (currentPlayer == MainActivity.BLACK)
                                  board[i - k][j - k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                              else if (currentPlayer == MainActivity.WHITE)
                                  board[i - k][j - k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                              board[i - k][j - k].setAround(currentPlayer, board, size);
                          }}
                  }
                  else if(b==2)
                  {
                      for(int k=1;board[i-k][j].player!=currentPlayer;k++)
                      {
                          if(i-k>=0){
                          board[i-k][j].player=currentPlayer;
                          if(currentPlayer==MainActivity.BLACK)
                              board[i-k][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i-k][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i-k][j].setAround(currentPlayer,board,size);
                      }}
                  }
                  else if(b==3)
                  {
                      for(int k=1;board[i-k][j+k].player!=currentPlayer;k++)
                      {
                        if(i-k>=0&&j+k<size) { board[i-k][j+k].player=currentPlayer;
                          if(currentPlayer==MainActivity.BLACK)
                              board[i-k][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i-k][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i-k][j+k].setAround(currentPlayer,board,size);
                      }}
                  }
                  else if(b==4)
                  {
                      for(int k=1;board[i][j+k].player!=currentPlayer;k++)
                      {
                          if(j+k<size){board[i][j+k].player=currentPlayer;
                          if(currentPlayer==MainActivity.BLACK)
                              board[i][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i][j+k].setAround(currentPlayer,board,size);
                      }}
                  }
                  else if(b==5)
                  {
                      for(int k=1;board[i+k][j+k].player!=currentPlayer;k++)
                      {
                          if(i+k<size&&j+k<size){
                              board[i+k][j+k].player=currentPlayer;
                          if(currentPlayer==MainActivity.BLACK)
                              board[i+k][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i+k][j+k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i+k][j+k].setAround(currentPlayer,board,size);
                      }}
                  }
                  else if(b==6)
                  {
                      for(int k=1;board[i+k][j].player!=currentPlayer;k++)
                      {
                          if(i+k<size){
                              board[i+k][j].player=currentPlayer;

                          if(currentPlayer==MainActivity.BLACK)
                              board[i+k][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i+k][j].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i+k][j].setAround(currentPlayer,board,size);
                      }}
                  }
                  else if(b==7)
                  {
                      for(int k=1;board[i+k][j-k].player!=currentPlayer;k++)
                      {
                          if(i+k<size&&j-k>=0){board[i+k][j-k].player=currentPlayer;
                          if(currentPlayer==MainActivity.BLACK)
                              board[i+k][j-k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonb));
                          else if(currentPlayer==MainActivity.WHITE)
                              board[i+k][j-k].setBackgroundDrawable(getResources().getDrawable(R.drawable.buttonw));
                          board[i+k][j-k].setAround(currentPlayer,board,size);
                      }}
                  }
              }
          }
   }

    private void setAround(int currentPlayer,OButton board[][],int size) {
       if(currentPlayer==MainActivity.BLACK)
       {
           int  i,j;
           for(i=this.x-1;i<this.x+2;i++)
           {
               for (j=this.y-1;j<this.y+2;j++)
               {
                   if(i>=0&&i<size&&j>=0&&j<size&&board[i][j].isEmpty()) {
                       board[i][j].blackstatus++;
                       board[i][j].whitestatus--;
                   }
               }
           }
       }
       else
       {
           int  i,j;
           for(i=this.x-1;i<this.x+2;i++)
           {
               for (j=this.y-1;j<this.y+2;j++)
               {
                   if(i>=0&&i<size&&j>=0&&j<size&&board[i][j].isEmpty()) {
                       board[i][j].blackstatus--;
                       board[i][j].whitestatus++;
                   }
               }
           }
       }

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
              int status,otherPlayer,a=0;
              int dir[]=new int[size];

                board[i][j].setEnabled(false);
              if(currentPlayer==MainActivity.BLACK){
                  status=board[i][j].blackstatus;
                  otherPlayer=MainActivity.WHITE;
              }
              else {
                  status=board[i][j].whitestatus;
                  otherPlayer=MainActivity.BLACK;
              }
              if(status!=0) {
                dir=board[i][j].check(board,size,otherPlayer);
              }
              for(int b=0;b<size;b++)
              {
                  if(dir[b]!=0)
                      a=1;
              }
                if(a==1) {
                    board[i][j].validStatus=true;
                    board[i][j].setEnabled(true);
                    break;
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
                        board[i][j].setText("-");
                    }
                }
                else
                {
                    if(board[i][j].isEmpty())
                    {
                        board[i][j].setText("");
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
