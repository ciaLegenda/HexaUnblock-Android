package com.example.simpleclickinteraction2;

import android.content.Context;
import android.graphics.Canvas;
import android.util.Pair;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class CanvasController extends View{
    private GameState originalGameState;
    private Table table;
    private Block []blocks;

    private CanvasClickChecker clickChecker;
    private HexDrawer hexDrawer;
    public enum states{no_block_selected,block_selected,game_complete};
    private states state;
    private int SelectedBlockId;
    private ArrayList<Pair<Integer,Integer>> moves;

    public CanvasController(Context context) {
        super(context);
        clickChecker = new CanvasClickChecker(9);
        Level level = new Level(context);
        level.generate(9,15);
        this.blocks = level.getBlocks();
        this.table = level.getTable();
        this.originalGameState = new GameState(table,blocks);
        this.moves = new ArrayList<Pair<Integer,Integer>>();
        hexDrawer = new HexDrawer(9,context);
        state = states.no_block_selected;
    }
    public void restart(){
        GameState bufState = new GameState(originalGameState);
        this.table = bufState.table;
        this.blocks = bufState.blocks;
        this.state = states.no_block_selected;
        invalidate();
    }

    public void undo(){
        if(!moves.isEmpty()) {
            Pair<Integer, Integer> lastMove = moves.get(moves.size() - 1);
            blocks[lastMove.first].move(-lastMove.second);
            moves.remove(moves.size() - 1);
            invalidate();
        }
    }

    public void hint(){
        Solver solver = new Solver(table,blocks);
        ArrayList<Pair<Integer,Integer>> solution = solver.solve(Solver.MAX_ITERATIONS_HINT);
        if(solution == null) {
            Toast.makeText(this.getContext(), "Cannot generate solution", Toast.LENGTH_SHORT).show();
            return;
        }
        int blockId = solution.get(0).first, direction = solution.get(0).second;
        blocks[blockId].setHighlithedDirection(direction);
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        clickChecker.setViewPx(getHeight(),getWidth());
        hexDrawer.setViewPx(getHeight(),getWidth());
        super.onDraw(canvas);
        table.draw(canvas,hexDrawer);
        for(int i=1;i<blocks.length;i++)
            blocks[i].draw(canvas,hexDrawer);
    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {

        Vect2D clickedCell = clickedCell = clickChecker.clickedCell(event.getX(),event.getY());;

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                if(state == states.no_block_selected)
                    if(table.getCellContent(clickedCell) > 0) {
                        SelectedBlockId = table.getCellContent(clickedCell);
                        blocks[SelectedBlockId].isSelected = true;
                        state = states.block_selected;
                    }
                break;
            case MotionEvent.ACTION_MOVE:
                if(state == states.block_selected)
                    blocks[SelectedBlockId].update(clickedCell,moves);
                break;
            case MotionEvent.ACTION_UP:
                if(state == states.block_selected) {
                    blocks[SelectedBlockId].isSelected = false;
                    state = states.no_block_selected;
                    if (table.isComplete())
                        state = states.game_complete;
                }
                break;
        }

        invalidate();
        return true;
    }



}
