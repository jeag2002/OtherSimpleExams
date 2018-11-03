package com.sqli.run.ninja;

import org.junit.Assert;
import org.junit.Test;

/**
 * A Minefield is made of a set of mines blocks and rivers for example: -o--o--ooo--~~~--oo-
 *
 * A block of one mine is represented by: -o-
 * A block of two mines is represented by: -oo-
 * A block of three mines is represented by: -ooo-
 * and so one
 *
 * A river is represented by: -~~~-
 *
 * A ninja must cross the minefield.
 * Crossing a river is only possible for ninja who knows swimming.
 * Crossing more than 3 mines in a block is impossible.
 *
 * A player is represented by: P
 *
 * A player at the start of a minefield is represented as follows: P-o--o--ooo--~~~--oo-
 * A player in the middle of a minefield is represented as follows: -o--o-P-ooo--~~~--oo-
 */
 
public class MineFieldTest {

    @Test
    public void singleMineField() {
        MineField mineField = new MineFieldBuilder().addMines(1).addMines(1).build();
        Assert.assertEquals("-o--o-", mineField.print());
    }
    
    @Test
    public void mineField() {
        MineField mineField = new MineFieldBuilder().addMines(1).addMines(3).addMines(2).build();
        Assert.assertEquals("-o--ooo--oo-", mineField.print());
    }

    @Test
    public void mineFieldWithRiver() {
        MineField mineField = new MineFieldBuilder().addMines(1).addRiver().addMines(2).build();
        Assert.assertEquals("-o--~~~--oo-", mineField.print());
    }

    @Test
    public void ninjaStartInMineField() {
        MineField mineField = new MineFieldBuilder().addMines(1).addMines(1).addMines(1).build();

        Ninja ninja = new Ninja();
        ninja.startIn(mineField);

        Assert.assertEquals("P-o--o--o-", mineField.print());
    }

    @Test
    public void ninjaCanJumpMines() throws CannotPassException{
        MineField mineField = new MineFieldBuilder().addMines(1).addMines(2).addMines(1).build();

        Ninja ninja = new Ninja();
        ninja.startIn(mineField);
        ninja.cross();

        Assert.assertEquals("-o-P-oo--o-", mineField.print());

        ninja.cross();

        Assert.assertEquals("-o--oo-P-o-", mineField.print());

    }

    @Test
    public void ninjaCanSucceed() throws CannotPassException{
        MineField mineField = new MineFieldBuilder().addMines(3).addMines(2).build();

        Ninja ninja = new Ninja();
        ninja.startIn(mineField);
        ninja.cross();

        Assert.assertEquals("-ooo-P-oo-", mineField.print());

        ninja.cross();

        Assert.assertEquals("Level completed", mineField.print());
    }

    @Test(expected = CannotPassException.class)
    public void ninjaCanJumpThreeMinesAtOneTimeButNoMore() throws CannotPassException{
        MineField mineField = new MineFieldBuilder().addMines(3).addMines(4).build();

        Ninja ninja = new Ninja();
        ninja.startIn(mineField);
        ninja.cross();

        Assert.assertEquals("-ooo-P-oooo-", mineField.print());

        ninja.cross();
    }

    @Test(expected = CannotPassException.class)
    public void ninjaCannotCrossRivers() throws CannotPassException {
        MineField mineField = new MineFieldBuilder().addRiver().addRiver().build();

        Ninja ninja = new Ninja();
        ninja.startIn(mineField);
        ninja.cross();
    }

    @Test
    public void ninjaWhoKnowsSwimmingCanCrossRivers() throws CannotPassException{
        MineField mineField = new MineFieldBuilder().addRiver().addRiver().build();

        Ninja ninja = new Ninja();
        ninja.learnSwimming();
        ninja.startIn(mineField);
        ninja.cross();

        Assert.assertEquals("-~~~-P-~~~-", mineField.print());

        ninja.cross();

        Assert.assertEquals("Level completed", mineField.print());
    }

}

