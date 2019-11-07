
interface PlayerInterface
{
    default boolean setShips()
    {
        for( Integer shipLength: getBoard().getShipsMissing())
        {
            this.getBoard().setShipPosition(shipLength, "0,0","2,0");
        }
        return true;
    }

    String getNextShot();

    String getName();

    Board2 getBoard();

    void wasShotOk(String previousShot, boolean wasOk);

}
