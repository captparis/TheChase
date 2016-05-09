
package decorators;

import models.AbstractUnit;

public abstract class AbstractUnitDecorator{
    private AbstractUnit unit;
    
    public AbstractUnitDecorator(AbstractUnit unit){
        this.unit = unit;
    }
    
    public abstract void setMode();
}
