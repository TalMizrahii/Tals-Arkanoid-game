package GameRelated;

import Collidables.Collidable;
import Collidables.CollisionInfo;
import Geomatry.Line;
import Geomatry.Point;

import java.util.ArrayList;
import java.util.List;

/**
 * GameEnvironment specifies the changes of the collidable objects in the game.
 *
 * @author Tal Mizrahi.
 * @version 1.0
 * @since 16/03/2022
 */
public class GameEnvironment {
    private ArrayList<Collidable> collidables; //storing all the information about the collidable objects.

    /**
     * a constructor for game environment.
     */
    public GameEnvironment() {
        this.collidables = new ArrayList<>();
    }

    /**
     * a getter for the collidables array.
     *
     * @return the collidables array.
     */
    public ArrayList<Collidable> getCollidables() {
        return this.collidables;
    }

    /**
     * adding new collidable to the array.
     *
     * @param c the new collidable value.
     */
    public void addCollidable(Collidable c) {
        //checking if the array is null.
        if (this.collidables == null) {
            this.collidables = new ArrayList<>();
        }
        this.collidables.add(c);
    }

    /**
     * @param trajectory the ball's trajectory for the next step.
     * @return the collision information as an instance if
     */
    public CollisionInfo getClosestCollision(Line trajectory) {

        //if no collidable items exist, return null.
        if (getCollidables().isEmpty()) {
            return null;
        }

        Point hazard;
        List<Point> pointList = new ArrayList<>(); // new list to store the points
        List<Integer> position = new ArrayList<>(); // indexes array

        for (int i = 0; i < getCollidables().size(); i++) {
            //check if the trajectory might intersect with any line of the rectangle
            hazard = trajectory.closestIntersectionToStartOfLine(getCollidables().get(i).getCollisionRectangle());
            //if it is null, don't put it in the list, else add it.
            if (hazard != null) {
                pointList.add(hazard);
                position.add(i); //the index of the collidable object
            }
        }

        //check what is closest point to the start of the array. if no points exist, return null.
        if (pointList.size() != 0) {
            Point min = pointList.get(0);
            int index = position.get(0);
            for (int i = 0; i < pointList.size(); i++) {
                if (min.distance(trajectory.start()) > pointList.get(i).distance(trajectory.start())) {
                    min = pointList.get(i);
                    index = position.get(i);
                }
            }
            return new CollisionInfo(min, getCollidables().get(index));
        }
        return null;
    }
}