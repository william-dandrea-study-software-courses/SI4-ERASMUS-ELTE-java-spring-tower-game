package gui.controler;

import gui.model.AbstractModel;

/**
 * @author D'Andréa William
 */
public abstract class AbstractController {

    protected AbstractModel abstractModel;

    public AbstractController(AbstractModel abstractModel){
        this.abstractModel = abstractModel;
    }

    abstract void control();



}
