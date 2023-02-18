package org.example;

public class test {

    public static void main(String[] args){
      AStarCore core=new AStarCore();
      while(!core.isEnded()&&!core.getOpenList().getQueue().isEmpty()){
          core.process();
      }
        System.out.println(core.showPath(core.getGrid().getCell(core.getGrid().getDIM()-1,core.getGrid().getDIM()-1)));
    }
    }


